package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Koneksi database dengan 2 teknik:
 *  1. SINGLETON       : hanya ada satu objek Koneksi (lihat getInstance()).
 *  2. CONNECTION POOL : koneksi disimpan di antrean lalu dipakai ulang, tidak dibuat baru terus.
 *
 * Cara pakai di DAO:
 *   Koneksi.getInstance().jalankan(conn -> { ...query... });     // baca/tulis biasa
 *   Koneksi.getInstance().transaksi(conn -> { ...beberapa query... }); // semua berhasil atau batal semua
 */
public class Koneksi {

    /** Pekerjaan SQL yang menerima koneksi dan mengembalikan hasil. */
    public interface KerjaSql<T> {
        T kerjakan(Connection conn) throws SQLException;
    }

    private static Koneksi instance;
    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(Konfigurasi.UKURAN_POOL);

    private Koneksi() { }   // private: tidak bisa di-new dari luar

    public static synchronized Koneksi getInstance() {
        if (instance == null) {
            instance = new Koneksi();
        }
        return instance;
    }

    /** Ambil koneksi dari pool; jika pool kosong, buat koneksi baru. */
    private Connection pinjam() throws SQLException {
        Connection c = pool.poll();
        if (c == null || !c.isValid(2)) {
            try {
                c = DriverManager.getConnection(Konfigurasi.URL, Konfigurasi.USER, Konfigurasi.PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Tidak dapat terhubung ke database 'haul'. "
                        + "Pastikan MySQL menyala dan database sudah diimpor.", e);
            }
        }
        return c;
    }

    /** Kembalikan koneksi ke pool (ditutup jika pool sudah penuh). */
    private void kembalikan(Connection c) {
        try {
            c.setAutoCommit(true);
            if (!pool.offer(c)) c.close();
        } catch (SQLException e) {
            // koneksi rusak, abaikan
        }
    }

    /** Jalankan query biasa. */
    public <T> T jalankan(KerjaSql<T> kerja) {
        Connection c = null;
        try {
            c = pinjam();
            return kerja.kerjakan(c);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (c != null) kembalikan(c);
        }
    }

    /** Jalankan beberapa query sebagai satu transaksi (commit / rollback). */
    public <T> T transaksi(KerjaSql<T> kerja) {
        return jalankan(c -> {
            c.setAutoCommit(false);
            try {
                T hasil = kerja.kerjakan(c);
                c.commit();
                return hasil;
            } catch (SQLException | RuntimeException e) {
                c.rollback();
                throw e;
            }
        });
    }
}
