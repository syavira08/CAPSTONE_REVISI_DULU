package controller;

import config.Konfigurasi;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import model.DAO;
import model.GambarProdukDAO;
import model.Produk;
import model.ProdukDAOJdbc;
import model.ProdukDAOJpa;

/** Use case: Kelola produk (admin) dan Melihat/mencari katalog (pelanggan). */
public class ProdukController {

    // POLYMORPHISM: variabel bertipe DAO<Produk> bisa diisi versi JDBC atau JPA
    private final DAO<Produk> dao = Konfigurasi.PRODUK_PAKAI_JPA ? new ProdukDAOJpa() : new ProdukDAOJdbc();
    private final GambarProdukDAO gambarDAO = new GambarProdukDAO();

    /** Cari berdasarkan nama/kode dan kategori ("Semua kategori" = tanpa filter). */
    public List<Produk> cari(String kataKunci, String kategori) {
        String k = kataKunci.toLowerCase().trim();
        Map<Integer, byte[]> gambar = gambarDAO.semua();
        List<Produk> hasil = dao.semua().stream()
                .filter(p -> p.getNama().toLowerCase().contains(k) || p.getKode().toLowerCase().contains(k))
                .filter(p -> kategori == null || kategori.startsWith("Semua") || p.getKategori().equals(kategori))
                .collect(Collectors.toList());
        hasil.forEach(p -> p.setGambar(gambar.get(p.getId())));
        return hasil;
    }

    /** Kategori tetap dari Konfigurasi (admin hanya memilih). */
    public List<String> daftarKategori() {
        return Arrays.asList(Konfigurasi.KATEGORI);
    }

    public Produk cariId(int id) {
        Produk p = dao.cariId(id);
        if (p != null) p.setGambar(gambarDAO.cari(id));
        return p;
    }

    /** Tambah (id = 0) atau ubah produk. Sesuai flowchart: input -> validasi -> simpan. */
    public void simpan(int id, String nama, String kategori, String harga, String stok,
                       byte[] gambar, String namaFile) {
        Map<String, String> salah = new LinkedHashMap<>();
        if (nama.isBlank()) salah.put("nama", "Nama produk wajib diisi.");
        else if (nama.trim().length() > 100) salah.put("nama", "Nama produk maksimal 100 karakter.");
        if (kategori == null || !daftarKategori().contains(kategori)) salah.put("kategori", "Pilih kategori produk.");
        BigDecimal nilaiHarga = angka(harga);
        if (nilaiHarga == null || nilaiHarga.signum() <= 0) salah.put("harga", "Harga harus berupa angka lebih dari 0.");
        BigDecimal nilaiStok = angka(stok);
        if (nilaiStok == null || nilaiStok.signum() < 0 || nilaiStok.scale() > 0) {
            salah.put("stok", "Stok harus berupa angka 0 atau lebih.");
        }
        if (gambar != null && gambar.length > Konfigurasi.UKURAN_GAMBAR_MAKS) salah.put("gambar", "Ukuran gambar maksimal 2 MB.");
        ValidasiException.cek(salah);

        Produk p = new Produk(id == 0 ? null : id, nama.trim(), kategori.trim(), nilaiHarga, nilaiStok.intValue());
        if (id == 0) dao.simpan(p);
        else dao.ubah(p);
        if (gambar != null) gambarDAO.simpan(p.getId(), gambar, namaFile);   // foto baru (opsional)
    }

    public void hapus(int id) {
        try {
            dao.hapus(id);
        } catch (RuntimeException e) {
            if (e.getMessage() == null || !e.getMessage().toLowerCase().contains("foreign key")) throw e;
            throw new ValidasiException("Produk sudah pernah dipesan sehingga tidak dapat dihapus. "
                    + "Ubah stoknya menjadi 0 jika tidak dijual lagi.");
        }
    }

    /** "1.499.000" -> 1499000; null jika bukan angka. */
    static BigDecimal angka(String teks) {
        try {
            return new BigDecimal(teks.replace("Rp", "").replace(".", "").trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
