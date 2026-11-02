package model;

import config.Koneksi;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** DAO untuk tabel promo. */
public class PromoDAO implements DAO<Promo> {

    private final Koneksi db = Koneksi.getInstance();

    @Override
    public List<Promo> semua() {
        return db.jalankan(c -> {
            List<Promo> hasil = new ArrayList<>();
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM promo ORDER BY id_promo");
            while (rs.next()) hasil.add(baca(rs));
            return hasil;
        });
    }

    @Override
    public Promo cariId(int id) {
        return semua().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Promo cariKode(String kode) {
        return semua().stream().filter(p -> p.getKode().equalsIgnoreCase(kode.trim())).findFirst().orElse(null);
    }

    /** Promo yang sudah dipakai pesanan tidak boleh dihapus. */
    public boolean dipakaiPesanan(int idPromo) {
        return db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM pesanan WHERE id_promo = ?");
            ps.setInt(1, idPromo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        });
    }

    @Override
    public void simpan(Promo p) {
        tulis("INSERT INTO promo (kode_voucher, jenis_promo, diskon, minimal_jumlah_barang, tanggal_mulai, "
                + "tanggal_berakhir, id_pengguna_admin) VALUES (?, ?, ?, ?, ?, ?, ?)", p);
    }

    @Override
    public void ubah(Promo p) {
        tulis("UPDATE promo SET kode_voucher = ?, jenis_promo = ?, diskon = ?, minimal_jumlah_barang = ?, "
                + "tanggal_mulai = ?, tanggal_berakhir = ?, id_pengguna_admin = ? WHERE id_promo = ?", p);
    }

    @Override
    public void hapus(int id) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement("DELETE FROM promo WHERE id_promo = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        });
    }

    private void tulis(String sql, Promo p) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, p.getKode());
            ps.setString(2, String.valueOf(p.getJenis()));
            ps.setBigDecimal(3, p.getDiskon());
            ps.setInt(4, p.getMinimalBarang());
            ps.setDate(5, Date.valueOf(p.getMulai()));
            ps.setDate(6, Date.valueOf(p.getSelesai()));
            ps.setInt(7, p.getIdAdmin());
            if (sql.startsWith("UPDATE")) ps.setInt(8, p.getId());
            return ps.executeUpdate();
        });
    }

    static Promo baca(ResultSet rs) throws SQLException {
        Promo p = Promo.buat(rs.getString("jenis_promo").charAt(0));   // Persen atau Nominal
        p.setId(rs.getInt("id_promo"));
        p.setKode(rs.getString("kode_voucher"));
        p.setDiskon(rs.getBigDecimal("diskon"));
        p.setMinimalBarang(rs.getInt("minimal_jumlah_barang"));
        p.setMulai(rs.getDate("tanggal_mulai").toLocalDate());
        p.setSelesai(rs.getDate("tanggal_berakhir").toLocalDate());
        p.setIdAdmin(rs.getInt("id_pengguna_admin"));
        return p;
    }
}
