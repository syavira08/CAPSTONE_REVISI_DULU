package controller;

import config.Konfigurasi;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import model.Pelanggan;
import model.Pesanan;
import model.PesananDAO;
import model.Produk;
import model.Promo;

/**
 * Semua alur pesanan:
 *  - Pelanggan : keranjang, checkout, bayar, riwayat, batalkan, detail pesanan, konfirmasi diterima.
 *  - Admin     : daftar pesanan, kirim pesanan (nomor resi dibuat otomatis).
 *
 * Status berubah otomatis: buat pesanan -> Menunggu Pembayaran, bayar -> Diproses,
 * dikirim admin -> Dikirim, diterima pelanggan -> Selesai.
 */
public class PesananController {

    private static Pesanan keranjang;   // keranjang milik pelanggan yang sedang login
    private final PesananDAO dao = new PesananDAO();
    private final ProdukController produkController = new ProdukController();

    // ======================= KERANJANG =======================

    public Pesanan getKeranjang() {
        Pelanggan login = (Pelanggan) AuthController.getPenggunaLogin();
        if (keranjang == null || keranjang.getPelanggan().getId() != login.getId()) {
            keranjang = new Pesanan(login);
        }
        return keranjang;
    }

    /**
     * Flowchart Tambah Produk: ambil stok terbaru -> tambahkan.
     * Jumlah otomatis dibatasi stok. Mengembalikan jumlah yang benar-benar masuk keranjang.
     */
    public int tambahKeKeranjang(int idProduk, int jumlah) {
        Produk terbaru = produkController.cariId(idProduk);
        if (terbaru == null) return 0;
        return getKeranjang().tambahItem(terbaru, jumlah);
    }

    /** Jumlah barang ini yang sudah ada di keranjang. */
    public int jumlahDiKeranjang(int idProduk) {
        return getKeranjang().getItems().stream()
                .filter(i -> i.getProduk().getId() == idProduk)
                .mapToInt(Pesanan.Item::getJumlah).sum();
    }

    /** Flowchart Ubah Produk: jumlah dibatasi 1 sampai stok. */
    public void ubahJumlah(int idProduk, int jumlah) {
        getKeranjang().ubahJumlah(idProduk, jumlah);
    }

    public void hapusDariKeranjang(int idProduk) {
        getKeranjang().hapusItem(idProduk);
    }

    // ======================= CHECKOUT & BAYAR =======================

    /** Voucher yang bisa dipilih pelanggan saat checkout. */
    public List<Promo> voucherTersedia() {
        return new PromoController().promoAktif();
    }

    /** Pasang voucher ke keranjang (null = tanpa voucher). Syarat minimal barang diperiksa. */
    public void pakaiVoucher(Promo promo) {
        getKeranjang().setPromo(null);
        if (promo != null) {
            getKeranjang().setPromo(new PromoController().cekVoucher(promo.getKode(),
                    getKeranjang().getJumlahBarang()));
        }
    }

    /** Flowchart Checkout: keranjang berisi & stok cukup -> simpan pesanan, detail, dan pembayaran. */
    public Pesanan checkout(String metodeBayar) {
        Pesanan p = getKeranjang();
        if (p.getItems().isEmpty()) throw new ValidasiException("Keranjang masih kosong.");
        if (metodeBayar == null) throw new ValidasiException("Pilih metode pembayaran.");
        p.setMetodeBayar(metodeBayar);
        p.setTotalHarga(p.hitungTotal(Konfigurasi.ONGKOS_KIRIM));
        p.setTanggal(LocalDateTime.now());
        jalankan(() -> dao.simpan(p));
        keranjang = null;   // keranjang dikosongkan setelah pesanan tersimpan
        return p;
    }

    /** Pelanggan memasukkan nominal pembayaran -> status otomatis Diproses. */
    public void bayar(Pesanan p, String nominal) {
        BigDecimal nilai = ProdukController.angka(nominal);
        if (nilai == null || nilai.signum() <= 0) {
            throw new ValidasiException("Nominal tidak valid", Map.of("nominal", "Masukkan nominal berupa angka."));
        }
        try {
            p.bayar(nilai);
        } catch (IllegalStateException e) {
            throw new ValidasiException(e.getMessage(), Map.of("nominal", e.getMessage()));
        }
        dao.ubah(p);
    }

    // ======================= RIWAYAT (pelanggan) =======================

    /** Riwayat milik pelanggan yang login. filter: null = semua tahap. */
    public List<Pesanan> riwayat(String kataKunci, Pesanan.Tahap filter) {
        int idSaya = AuthController.getPenggunaLogin().getId();
        return cari(kataKunci, filter).stream()
                .filter(p -> p.getPelanggan().getId() == idSaya)
                .collect(Collectors.toList());
    }

    /** Batalkan pesanan (hanya sebelum dibayar). Stok dikembalikan. */
    public void batalkan(Pesanan p) {
        if (!p.bisaDibatalkan()) throw new ValidasiException("Pesanan yang sudah dibayar tidak dapat dibatalkan.");
        dao.hapus(p.getId());
    }

    /** Barang sudah diterima -> Selesai. */
    public void konfirmasiDiterima(Pesanan p) {
        jalankan(p::konfirmasiDiterima);
        dao.ubah(p);
    }

    public Pesanan muatUlang(int id) {
        return dao.cariId(id);
    }

    // ======================= KELOLA PESANAN (admin) =======================

    public List<Pesanan> cari(String kataKunci, Pesanan.Tahap filter) {
        String k = kataKunci.toLowerCase().trim();
        return dao.semua().stream()
                .filter(p -> filter == null || p.getTahap() == filter)
                .filter(p -> p.getNomor().toLowerCase().contains(k)
                        || p.getPelanggan().getNama().toLowerCase().contains(k)
                        || (p.getNomorResi() != null && p.getNomorResi().toLowerCase().contains(k)))
                .collect(Collectors.toList());
    }

    /** Admin mengirim pesanan Diproses: nomor resi & estimasi dibuat otomatis -> Dikirim. */
    public void kirim(Pesanan p) {
        String resi;
        do {
            resi = buatResi(p);
        } while (dao.resiDipakai(resi, p.getId()));
        String resiFinal = resi;
        jalankan(() -> p.kirim(resiFinal, LocalDate.now().plusDays(Konfigurasi.ESTIMASI_KIRIM_HARI)));
        dao.ubah(p);
    }

    /** Nomor resi panjang, mis. HAUL2026110300016-48213 (tanggal + id pesanan + angka acak). */
    private String buatResi(Pesanan p) {
        return "HAUL" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + String.format("%05d", p.getId()) + "-"
                + String.format("%05d", ThreadLocalRandom.current().nextInt(100000));
    }

    // ======================= bantuan =======================

    /** Ubah pelanggaran aturan di model (IllegalStateException) menjadi pesan untuk View. */
    private void jalankan(Runnable aksi) {
        try {
            aksi.run();
        } catch (IllegalStateException e) {
            throw new ValidasiException(e.getMessage());
        }
    }
}
