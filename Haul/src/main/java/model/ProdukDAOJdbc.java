package model;

import config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** DAO Produk versi JDBC (query SQL ditulis langsung). */
public class ProdukDAOJdbc implements DAO<Produk> {

    private final Koneksi db = Koneksi.getInstance();

    @Override
    public List<Produk> semua() {
        return db.jalankan(c -> {
            List<Produk> hasil = new ArrayList<>();
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM produk ORDER BY id_produk");
            while (rs.next()) hasil.add(baca(rs));
            return hasil;
        });
    }

    @Override
    public Produk cariId(int id) {
        return db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM produk WHERE id_produk = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? baca(rs) : null;
        });
    }

    @Override
    public void simpan(Produk p) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO produk (nama_produk, kategori, harga, stok) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNama());
            ps.setString(2, p.getKategori());
            ps.setBigDecimal(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.executeUpdate();
            ResultSet key = ps.getGeneratedKeys();
            if (key.next()) p.setId(key.getInt(1));
            return null;
        });
    }

    @Override
    public void ubah(Produk p) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE produk SET nama_produk = ?, kategori = ?, harga = ?, stok = ? WHERE id_produk = ?");
            ps.setString(1, p.getNama());
            ps.setString(2, p.getKategori());
            ps.setBigDecimal(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.setInt(5, p.getId());
            return ps.executeUpdate();
        });
    }

    @Override
    public void hapus(int id) {
        db.jalankan(c -> {
            PreparedStatement ps = c.prepareStatement("DELETE FROM produk WHERE id_produk = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        });
    }

    static Produk baca(ResultSet rs) throws SQLException {
        return new Produk(rs.getInt("id_produk"), rs.getString("nama_produk"), rs.getString("kategori"),
                rs.getBigDecimal("harga"), rs.getInt("stok"));
    }
}
