package model;

import config.Koneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** DAO untuk tabel pesanan, detail_pesanan, pembayaran, dan pengiriman. */
public class PesananDAO implements DAO<Pesanan> {

    private final Koneksi db = Koneksi.getInstance();

    /** Ambil semua pesanan lengkap dengan pelanggan, promo, barang, pembayaran, dan pengiriman. */
    @Override
    public List<Pesanan> semua() {
        Map<Integer, Promo> promo = new HashMap<>();
        for (Promo p : new PromoDAO().semua()) promo.put(p.getId(), p);

        return db.jalankan(c -> {
            // 1) data utama pesanan
            Map<Integer, Pesanan> peta = new HashMap<>();
            List<Pesanan> hasil = new ArrayList<>();
            ResultSet rs = c.createStatement().executeQuery(
                    "SELECT ps.*, pg.nama, pg.username, pl.alamat, pl.no_telepon, pl.tanggal_bergabung, "
                    + "pb.metode, pb.status AS status_bayar, k.nomor_resi, k.tanggal_estimasi, "
                    + "k.status AS status_kirim, k.tanggal_konfirmasi "
                    + "FROM pesanan ps "
                    + "JOIN pengguna pg ON pg.id_pengguna = ps.id_pengguna "
                    + "JOIN pelanggan pl ON pl.id_pengguna = ps.id_pengguna "
                    + "LEFT JOIN pembayaran pb ON pb.id_pesanan = ps.id_pesanan "
                    + "LEFT JOIN pengiriman k ON k.id_pesanan = ps.id_pesanan "
                    + "ORDER BY ps.id_pesanan DESC");
            while (rs.next()) {
                Timestamp gabung = rs.getTimestamp("tanggal_bergabung");
                Pelanggan pl = new Pelanggan(rs.getInt("id_pengguna"), rs.getString("nama"), rs.getString("username"),
                        "", rs.getString("alamat"), rs.getString("no_telepon"),
                        gabung == null ? null : gabung.toLocalDateTime());
                Pesanan p = new Pesanan(pl);
                p.setId(rs.getInt("id_pesanan"));
                p.setTanggal(rs.getTimestamp("tanggal_pesanan").toLocalDateTime());
                p.setTotalHarga(rs.getBigDecimal("total_harga"));
                p.setStatus(rs.getString("status"));
                p.setPromo(promo.get(rs.getInt("id_promo")));
                p.setMetodeBayar(rs.getString("metode"));
                p.setLunas("LUNAS".equals(rs.getString("status_bayar")));
                p.setNomorResi(rs.getString("nomor_resi"));
                Date estimasi = rs.getDate("tanggal_estimasi");
                p.setEstimasiTiba(estimasi == null ? null : estimasi.toLocalDate());
                p.setDiterima("DITERIMA".equals(rs.getString("status_kirim")));
                Timestamp konfirmasi = rs.getTimestamp("tanggal_konfirmasi");
                p.setTanggalDiterima(konfirmasi == null ? null : konfirmasi.toLocalDateTime());
                peta.put(p.getId(), p);
                hasil.add(p);
            }
            // 2) barang di setiap pesanan
            ResultSet d = c.createStatement().executeQuery(
                    "SELECT d.id_pesanan, d.jumlah, d.harga_satuan, pr.* FROM detail_pesanan d "
                    + "JOIN produk pr ON pr.id_produk = d.id_produk");
            while (d.next()) {
                Produk produk = ProdukDAOJdbc.baca(d);
                produk.setHarga(d.getBigDecimal("harga_satuan"));   // harga saat dibeli
                Pesanan p = peta.get(d.getInt("id_pesanan"));
                if (p != null) p.getItems().add(new Pesanan.Item(produk, d.getInt("jumlah")));
            }
            return hasil;
        });
    }

    @Override
    public Pesanan cariId(int id) {
        return semua().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * CHECKOUT (satu transaksi): kurangi stok, simpan pesanan, detail pesanan, dan pembayaran.
     * Jika satu langkah gagal (mis. stok habis), semua dibatalkan.
     */
    @Override
    public void simpan(Pesanan p) {
        db.transaksi(c -> {
            for (Pesanan.Item item : p.getItems()) {
                PreparedStatement stok = c.prepareStatement(
                        "UPDATE produk SET stok = stok - ? WHERE id_produk = ? AND stok >= ?");
                stok.setInt(1, item.getJumlah());
                stok.setInt(2, item.getProduk().getId());
                stok.setInt(3, item.getJumlah());
                if (stok.executeUpdate() == 0) {
                    throw new IllegalStateException("Stok " + item.getProduk().getNama() + " tidak mencukupi.");
                }
            }

            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO pesanan (tanggal_pesanan, total_harga, status, id_pengguna, id_promo) "
                    + "VALUES (?, ?, 'MENUNGGU_PEMBAYARAN', ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(p.getTanggal()));
            ps.setBigDecimal(2, p.getTotalHarga());
            ps.setInt(3, p.getPelanggan().getId());
            ps.setObject(4, p.getPromo() == null ? null : p.getPromo().getId());
            ps.executeUpdate();
            ResultSet key = ps.getGeneratedKeys();
            key.next();
            p.setId(key.getInt(1));

            for (Pesanan.Item item : p.getItems()) {
                PreparedStatement detail = c.prepareStatement(
                        "INSERT INTO detail_pesanan (jumlah, sub_total, id_pesanan, id_produk, harga_satuan) "
                        + "VALUES (?, ?, ?, ?, ?)");
                detail.setInt(1, item.getJumlah());
                detail.setBigDecimal(2, item.getSubtotal());
                detail.setInt(3, p.getId());
                detail.setInt(4, item.getProduk().getId());
                detail.setBigDecimal(5, item.getProduk().getHarga());
                detail.executeUpdate();
            }

            PreparedStatement bayar = c.prepareStatement(
                    "INSERT INTO pembayaran (metode, status, tanggal_pembayaran, id_pesanan) VALUES (?, 'BELUM_BAYAR', NOW(), ?)");
            bayar.setString(1, p.getMetodeBayar());
            bayar.setInt(2, p.getId());
            bayar.executeUpdate();
            return null;
        });
    }

    /** Simpan perubahan status pesanan, pembayaran, dan pengiriman. */
    @Override
    public void ubah(Pesanan p) {
        db.transaksi(c -> {
            perbarui(c, "UPDATE pesanan SET status = ? WHERE id_pesanan = ?", p.getStatus(), p.getId());

            if (p.isLunas()) {
                int baris = perbarui(c, "UPDATE pembayaran SET status = 'LUNAS', tanggal_pembayaran = NOW() "
                        + "WHERE id_pesanan = ? AND status <> 'LUNAS'", p.getId());
                if (baris == 0 && hitung(c, "SELECT COUNT(*) FROM pembayaran WHERE id_pesanan = ?", p.getId()) == 0) {
                    perbarui(c, "INSERT INTO pembayaran (metode, status, tanggal_pembayaran, id_pesanan) "
                            + "VALUES ('TRANSFER', 'LUNAS', NOW(), ?)", p.getId());
                }
            }

            if (p.getNomorResi() != null) {
                Date estimasi = Date.valueOf(p.getEstimasiTiba());
                String status = p.isDiterima() ? "DITERIMA" : "DIKIRIM";
                Timestamp konfirmasi = p.getTanggalDiterima() == null ? null : Timestamp.valueOf(p.getTanggalDiterima());
                if (hitung(c, "SELECT COUNT(*) FROM pengiriman WHERE id_pesanan = ?", p.getId()) == 0) {
                    perbarui(c, "INSERT INTO pengiriman (nomor_resi, tanggal_estimasi, status, tanggal_konfirmasi, id_pesanan) "
                            + "VALUES (?, ?, ?, ?, ?)", p.getNomorResi(), estimasi, status, konfirmasi, p.getId());
                } else {
                    perbarui(c, "UPDATE pengiriman SET nomor_resi = ?, tanggal_estimasi = ?, status = ?, "
                            + "tanggal_konfirmasi = ? WHERE id_pesanan = ?", p.getNomorResi(), estimasi, status,
                            konfirmasi, p.getId());
                }
            }
            return null;
        });
    }

    /** BATALKAN pesanan: kembalikan stok lalu hapus data pesanan beserta turunannya. */
    @Override
    public void hapus(int id) {
        db.transaksi(c -> {
            perbarui(c, "UPDATE produk pr JOIN detail_pesanan d ON d.id_produk = pr.id_produk "
                    + "SET pr.stok = pr.stok + d.jumlah WHERE d.id_pesanan = ?", id);
            for (String tabel : new String[]{"detail_pesanan", "pembayaran", "pengiriman", "pesanan"}) {
                perbarui(c, "DELETE FROM " + tabel + " WHERE id_pesanan = ?", id);
            }
            return null;
        });
    }

    /** Nomor resi tidak boleh dipakai pesanan lain. */
    public boolean resiDipakai(String resi, int idPesanan) {
        return db.jalankan(c -> hitung(c,
                "SELECT COUNT(*) FROM pengiriman WHERE nomor_resi = ? AND id_pesanan <> ?", resi, idPesanan) > 0);
    }

    // ---------- bantuan ----------

    private int perbarui(Connection c, String sql, Object... nilai) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        for (int i = 0; i < nilai.length; i++) ps.setObject(i + 1, nilai[i]);
        return ps.executeUpdate();
    }

    private int hitung(Connection c, String sql, Object... nilai) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        for (int i = 0; i < nilai.length; i++) ps.setObject(i + 1, nilai[i]);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
