package model;

import config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** DAO untuk tabel pengguna + admin + pelanggan. */
public class PenggunaDAO implements DAO<Pengguna> {

    private static final String SELECT =
            "SELECT p.*, a.jabatan, a.no_pegawai, pl.alamat, pl.no_telepon, pl.tanggal_bergabung "
            + "FROM pengguna p "
            + "LEFT JOIN admin a ON a.id_pengguna = p.id_pengguna "
            + "LEFT JOIN pelanggan pl ON pl.id_pengguna = p.id_pengguna ";

    private final Koneksi db = Koneksi.getInstance();

    @Override
    public List<Pengguna> semua() {
        return cari(SELECT + "ORDER BY p.id_pengguna", null);
    }

    @Override
    public Pengguna cariId(int id) {
        List<Pengguna> hasil = cari(SELECT + "WHERE p.id_pengguna = ?", id);
        return hasil.isEmpty() ? null : hasil.get(0);
    }

    public Pengguna cariUsername(String username) {
        List<Pengguna> hasil = cari(SELECT + "WHERE p.username = ?", username);
        return hasil.isEmpty() ? null : hasil.get(0);
    }

    /** Login: cari username lalu cocokkan password. Hasilnya Admin atau Pelanggan. */
    public Pengguna login(String username, String password) {
        Pengguna p = cariUsername(username);
        return (p != null && p.cocokPassword(password)) ? p : null;
    }

    public List<Pelanggan> semuaPelanggan() {
        List<Pelanggan> hasil = new ArrayList<>();
        for (Pengguna p : semua()) {
            if (p instanceof Pelanggan pl) hasil.add(pl);
        }
        return hasil;
    }

    /** Registrasi: simpan ke tabel pengguna lalu ke tabel pelanggan/admin (satu transaksi). */
    @Override
    public void simpan(Pengguna p) {
        db.transaksi(c -> {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO pengguna (nama, username, password, peran) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNama());
            ps.setString(2, p.getUsername());
            ps.setString(3, p.getPassword());
            ps.setString(4, p.getPeran());
            ps.executeUpdate();
            ResultSet key = ps.getGeneratedKeys();
            key.next();
            p.setId(key.getInt(1));

            if (p instanceof Pelanggan pl) {
                PreparedStatement ps2 = c.prepareStatement(
                        "INSERT INTO pelanggan (id_pengguna, alamat, no_telepon) VALUES (?, ?, ?)");
                ps2.setInt(1, pl.getId());
                ps2.setString(2, pl.getAlamat());
                ps2.setString(3, pl.getNoTelepon());
                ps2.executeUpdate();
            } else if (p instanceof Admin a) {
                PreparedStatement ps2 = c.prepareStatement(
                        "INSERT INTO admin (id_pengguna, jabatan, no_pegawai) VALUES (?, ?, ?)");
                ps2.setInt(1, a.getId());
                ps2.setString(2, a.getJabatan());
                ps2.setString(3, a.getNoPegawai());
                ps2.executeUpdate();
            }
            return null;
        });
    }

    /** Ubah profil (nama, username, dan data pelanggan). */
    @Override
    public void ubah(Pengguna p) {
        db.transaksi(c -> {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE pengguna SET nama = ?, username = ? WHERE id_pengguna = ?");
            ps.setString(1, p.getNama());
            ps.setString(2, p.getUsername());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            if (p instanceof Pelanggan pl) {
                PreparedStatement ps2 = c.prepareStatement(
                        "UPDATE pelanggan SET alamat = ?, no_telepon = ? WHERE id_pengguna = ?");
                ps2.setString(1, pl.getAlamat());
                ps2.setString(2, pl.getNoTelepon());
                ps2.setInt(3, pl.getId());
                ps2.executeUpdate();
            }
            return null;
        });
    }

    @Override
    public void hapus(int id) {
        db.transaksi(c -> {
            for (String tabel : new String[]{"admin", "pelanggan", "pengguna"}) {
                PreparedStatement ps = c.prepareStatement("DELETE FROM " + tabel + " WHERE id_pengguna = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            return null;
        });
    }

    // ---------- bantuan ----------

    private List<Pengguna> cari(String sql, Object parameter) {
        return db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement(sql);
            if (parameter != null) ps.setObject(1, parameter);
            ResultSet rs = ps.executeQuery();
            List<Pengguna> hasil = new ArrayList<>();
            while (rs.next()) hasil.add(baca(rs));
            return hasil;
        });
    }

    /** POLYMORPHISM: objek yang dibuat tergantung kolom peran. */
    private Pengguna baca(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_pengguna");
        String nama = rs.getString("nama");
        String username = rs.getString("username");
        String password = rs.getString("password");
        if ("ADMIN".equals(rs.getString("peran"))) {
            return new Admin(id, nama, username, password, rs.getString("jabatan"), rs.getString("no_pegawai"));
        }
        Timestamp gabung = rs.getTimestamp("tanggal_bergabung");
        return new Pelanggan(id, nama, username, password, rs.getString("alamat"), rs.getString("no_telepon"),
                gabung == null ? null : gabung.toLocalDateTime());
    }
}
