package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pesanan beserta isi, pembayaran, dan pengirimannya
 * (gabungan tabel pesanan, detail_pesanan, pembayaran, pengiriman).
 *
 * Objek yang sama juga dipakai sebagai KERANJANG: selama belum checkout, id-nya masih 0.
 */
public class Pesanan {

    /**
     * Tahap pesanan yang tampil di layar. Berubah otomatis:
     * buat pesanan -> Menunggu Pembayaran, pelanggan bayar -> Diproses,
     * admin kirim -> Dikirim, pelanggan terima -> Selesai.
     */
    public enum Tahap {
        MENUNGGU_PEMBAYARAN("Menunggu Pembayaran"), DIPROSES("Diproses"), DIKIRIM("Dikirim"), SELESAI("Selesai");

        private final String label;
        Tahap(String label) { this.label = label; }
        @Override public String toString() { return label; }
    }

    /** Satu baris barang (detail_pesanan). */
    public static class Item {
        private final Produk produk;
        private int jumlah;

        public Item(Produk produk, int jumlah) {
            this.produk = produk;
            this.jumlah = jumlah;
        }

        public Produk getProduk() { return produk; }
        public int getJumlah() { return jumlah; }
        public BigDecimal getSubtotal() { return produk.getHarga().multiply(BigDecimal.valueOf(jumlah)); }
    }

    // ----- tabel pesanan -----
    private int id;
    private LocalDateTime tanggal = LocalDateTime.now();
    private Pelanggan pelanggan;
    private Promo promo;
    private String status = "MENUNGGU_PEMBAYARAN";      // MENUNGGU_PEMBAYARAN / DIKIRIM / SELESAI
    private BigDecimal totalHarga = BigDecimal.ZERO;
    private final List<Item> items = new ArrayList<>();
    // ----- tabel pembayaran -----
    private String metodeBayar;                        // TRANSFER / E_WALLET
    private boolean lunas;
    // ----- tabel pengiriman -----
    private String nomorResi;
    private LocalDate estimasiTiba;
    private boolean diterima;
    private LocalDateTime tanggalDiterima;

    public Pesanan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    // =============== aturan keranjang ===============

    /**
     * Tambah barang; jika sudah ada, jumlahnya ditambah.
     * Jumlah otomatis dibatasi sesuai stok. Mengembalikan jumlah yang benar-benar ditambahkan.
     */
    public int tambahItem(Produk produk, int jumlah) {
        Item ada = cariItem(produk.getId());
        int sekarang = ada == null ? 0 : ada.jumlah;
        int baru = Math.min(sekarang + jumlah, produk.getStok());
        if (baru <= sekarang) return 0;
        if (ada == null) items.add(new Item(produk, baru));
        else ada.jumlah = baru;
        return baru - sekarang;
    }

    /** Ubah jumlah barang: dibatasi antara 1 dan stok. */
    public void ubahJumlah(int idProduk, int jumlahBaru) {
        Item item = cariItem(idProduk);
        if (item != null) item.jumlah = Math.max(1, Math.min(jumlahBaru, item.produk.getStok()));
    }

    public void hapusItem(int idProduk) {
        items.removeIf(i -> i.produk.getId() == idProduk);
    }

    private Item cariItem(int idProduk) {
        return items.stream().filter(i -> i.produk.getId() == idProduk).findFirst().orElse(null);
    }

    public BigDecimal getSubtotal() {
        return items.stream().map(Item::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getJumlahBarang() {
        return items.stream().mapToInt(Item::getJumlah).sum();
    }

    /** Potongan voucher dihitung oleh objek Promo (POLYMORPHISM: Persen / Nominal). */
    public BigDecimal getPotongan() {
        return promo == null ? BigDecimal.ZERO : promo.hitungPotongan(getSubtotal());
    }

    /** Total = subtotal + ongkir - potongan. Dipakai saat checkout lalu disimpan ke total_harga. */
    public BigDecimal hitungTotal(BigDecimal ongkir) {
        return getSubtotal().add(ongkir).subtract(getPotongan()).max(BigDecimal.ZERO);
    }

    // =============== aturan status ===============

    public Tahap getTahap() {
        if (status.equals("SELESAI")) return Tahap.SELESAI;
        if (status.equals("DIKIRIM")) return Tahap.DIKIRIM;
        return lunas ? Tahap.DIPROSES : Tahap.MENUNGGU_PEMBAYARAN;
    }

    /** Pelanggan membayar: nominal harus sama dengan total tagihan -> otomatis Diproses. */
    public void bayar(BigDecimal nominal) {
        if (getTahap() != Tahap.MENUNGGU_PEMBAYARAN) throw new IllegalStateException("Pesanan ini sudah dibayar.");
        if (nominal == null || nominal.compareTo(totalHarga) != 0) {
            throw new IllegalStateException("Nominal harus sama dengan total tagihan.");
        }
        lunas = true;
    }

    /** Admin mengirim pesanan yang sudah dibayar -> otomatis Dikirim. */
    public void kirim(String resi, LocalDate estimasi) {
        if (getTahap() != Tahap.DIPROSES) throw new IllegalStateException("Hanya pesanan Diproses yang bisa dikirim.");
        nomorResi = resi;
        estimasiTiba = estimasi;
        status = "DIKIRIM";
    }

    /** Pelanggan menerima barang -> otomatis Selesai. */
    public void konfirmasiDiterima() {
        if (getTahap() != Tahap.DIKIRIM) throw new IllegalStateException("Pesanan belum dikirim.");
        diterima = true;
        tanggalDiterima = LocalDateTime.now();
        status = "SELESAI";
    }

    public boolean bisaDibatalkan() {
        return getTahap() == Tahap.MENUNGGU_PEMBAYARAN;
    }

    // =============== tampilan ===============

    /** Nomor pesanan, mis. HLC-261031-041 (tanggal + id). */
    public String getNomor() {
        return "HLC-" + tanggal.format(DateTimeFormatter.ofPattern("yyMMdd")) + "-" + String.format("%03d", id);
    }

    public String getNamaProduk() {
        return items.stream().map(i -> i.produk.getNama()).collect(Collectors.joining(" + "));
    }

    // =============== getter & setter (dipakai DAO) ===============

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Promo getPromo() { return promo; }
    public void setPromo(Promo promo) { this.promo = promo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalHarga() { return totalHarga; }
    public void setTotalHarga(BigDecimal totalHarga) { this.totalHarga = totalHarga; }
    public List<Item> getItems() { return items; }
    public String getMetodeBayar() { return metodeBayar; }
    public void setMetodeBayar(String metodeBayar) { this.metodeBayar = metodeBayar; }
    public boolean isLunas() { return lunas; }
    public void setLunas(boolean lunas) { this.lunas = lunas; }
    public String getNomorResi() { return nomorResi; }
    public void setNomorResi(String nomorResi) { this.nomorResi = nomorResi; }
    public LocalDate getEstimasiTiba() { return estimasiTiba; }
    public void setEstimasiTiba(LocalDate estimasiTiba) { this.estimasiTiba = estimasiTiba; }
    public boolean isDiterima() { return diterima; }
    public void setDiterima(boolean diterima) { this.diterima = diterima; }
    public LocalDateTime getTanggalDiterima() { return tanggalDiterima; }
    public void setTanggalDiterima(LocalDateTime t) { this.tanggalDiterima = t; }
}
