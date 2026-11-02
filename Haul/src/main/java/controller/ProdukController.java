package controller;

import config.Konfigurasi;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.DAO;
import model.Produk;
import model.ProdukDAOJdbc;
import model.ProdukDAOJpa;

/** Use case: Kelola produk (admin) dan Melihat/mencari katalog (pelanggan). */
public class ProdukController {

    // POLYMORPHISM: variabel bertipe DAO<Produk> bisa diisi versi JDBC atau JPA
    private final DAO<Produk> dao = Konfigurasi.PRODUK_PAKAI_JPA ? new ProdukDAOJpa() : new ProdukDAOJdbc();

    /** Cari berdasarkan nama/kode dan kategori ("Semua kategori" = tanpa filter). */
    public List<Produk> cari(String kataKunci, String kategori) {
        String k = kataKunci.toLowerCase().trim();
        return dao.semua().stream()
                .filter(p -> p.getNama().toLowerCase().contains(k) || p.getKode().toLowerCase().contains(k))
                .filter(p -> kategori == null || kategori.startsWith("Semua") || p.getKategori().equals(kategori))
                .collect(Collectors.toList());
    }

    public List<String> daftarKategori() {
        return dao.semua().stream().map(Produk::getKategori).distinct().sorted().collect(Collectors.toList());
    }

    public Produk cariId(int id) {
        return dao.cariId(id);
    }

    /** Tambah (id = 0) atau ubah produk. Sesuai flowchart: input -> validasi -> simpan. */
    public void simpan(int id, String nama, String kategori, String harga, String stok) {
        Map<String, String> salah = new LinkedHashMap<>();
        if (nama.isBlank()) salah.put("nama", "Nama produk wajib diisi.");
        if (kategori.isBlank()) salah.put("kategori", "Kategori wajib diisi.");
        BigDecimal nilaiHarga = angka(harga);
        if (nilaiHarga == null || nilaiHarga.signum() <= 0) salah.put("harga", "Harga harus lebih dari Rp0.");
        BigDecimal nilaiStok = angka(stok);
        if (nilaiStok == null || nilaiStok.signum() < 0) salah.put("stok", "Stok tidak boleh negatif.");
        ValidasiException.cek(salah);

        Produk p = new Produk(id == 0 ? null : id, nama.trim(), kategori.trim(), nilaiHarga, nilaiStok.intValue());
        if (id == 0) dao.simpan(p);
        else dao.ubah(p);
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
