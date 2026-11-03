package model;

import config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO untuk tabel tambahan gambar_produk (foto produk yang diunggah admin).
 * Dipisah dari tabel produk supaya struktur tabel lama tidak berubah.
 */
public class GambarProdukDAO {

    private final Koneksi db = Koneksi.getInstance();

    /** Semua foto: id_produk -> isi file gambar. */
    public Map<Integer, byte[]> semua() {
        return db.jalankan(c -> {
            Map<Integer, byte[]> hasil = new HashMap<>();
            ResultSet rs = c.createStatement().executeQuery("SELECT id_produk, data FROM gambar_produk");
            while (rs.next()) hasil.put(rs.getInt("id_produk"), rs.getBytes("data"));
            return hasil;
        });
    }

    public byte[] cari(int idProduk) {
        return db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement("SELECT data FROM gambar_produk WHERE id_produk = ?");
            ps.setInt(1, idProduk);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getBytes("data") : null;
        });
    }

    /** Simpan atau ganti foto produk. */
    public void simpan(int idProduk, byte[] data, String namaFile) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO gambar_produk (id_produk, data, nama_file) VALUES (?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE data = VALUES(data), nama_file = VALUES(nama_file)");
            ps.setInt(1, idProduk);
            ps.setBytes(2, data);
            ps.setString(3, namaFile);
            return ps.executeUpdate();
        });
    }
}
