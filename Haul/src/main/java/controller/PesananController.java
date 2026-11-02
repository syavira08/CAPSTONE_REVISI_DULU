package controller;

import config.Konfigurasi;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Pelanggan;
import model.Pesanan;
import model.PesananDAO;
import model.Produk;
import model.ProdukDAOJdbc;

/**
 * Semua alur pesanan:
 *  - Pelanggan : keranjang (tambah/ubah/hapus), checkout, riwayat, batalkan, lacak, konfirmasi diterima.
 *  - Admin     : ubah status pesanan dan kelola pengiriman (nomor resi, estimasi, status).
 */
public class PesananController {

    private static Pesanan keranjang;   // keranjang milik pelanggan yang sedang login
    private final PesananDAO dao = new PesananDAO();
    private final ProdukDAOJdbc produkDAO = new ProdukDAOJdbc();

    // ======================= KERANJANG =======================

    public Pesanan getKeranjang() {
        Pelanggan login = (Pelanggan) AuthController.getPenggunaLogin();
        if (keranjang == null || keranjang.getPelanggan().getId() != login.getId()) {
            keranjang = new Pesanan(login);
        }
        return keranjang;
    }

    /** Flowchart Tambah Produk: periksa stok terbaru di database -> tambahkan. */
    public void tambahKeKeranjang(int idProduk) {
        Produk terbaru = produkDAO.cariId(idProduk);
        jalankan(() -> getKeranjang().tambahItem(terbaru, 1));
    }

    /** Flowchart Ubah Produk: jumlah harus > 0 dan stok cukup. */
    public void ubahJumlah(int idProduk, int jumlah) {
        jalankan(() -> getKeranjang().ubahJumlah(idProduk, jumlah));
    }

    public void hapusDariKeranjang(int idProduk) {
        getKeranjang().hapusItem(idProduk);
    }

    // ======================= CHECKOUT =======================

    /** Flowchart Checkout: keranjang berisi & stok cukup? */
    public void periksaSebelumCheckout() {
        if (getKeranjang().getItems().isEmpty()) throw new ValidasiException("Keranjang masih kosong.");
        for (Pesanan.Item item : getKeranjang().getItems()) {
            Produk terbaru = produkDAO.cariId(item.getProduk().getId());
            if (terbaru == null || item.getJumlah() > terbaru.getStok()) {
                throw new ValidasiException("Stok " + item.getProduk().getNama() + " tidak mencukupi.");
            }
        }
    }

    /** Pasang voucher ke keranjang (kode kosong = lepas voucher). Potongan dihitung oleh Promo. */
    public void pakaiVoucher(String kode) {
        getKeranjang().setPromo(null);
        getKeranjang().setPromo(new PromoController().cekVoucher(kode, getKeranjang().getJumlahBarang()));
    }

    /** Flowchart Checkout: konfirmasi -> simpan pesanan, detail pesanan, dan pembayaran. */
    public Pesanan checkout(String metodeBayar) {
        periksaSebelumCheckout();
        Pesanan p = getKeranjang();
        p.setMetodeBayar(metodeBayar);
        p.setTotalHarga(p.hitungTotal(Konfigurasi.ONGKOS_KIRIM));
        p.setTanggal(java.time.LocalDateTime.now());
        jalankan(() -> dao.simpan(p));
        keranjang = null;   // keranjang dikosongkan setelah pesanan tersimpan
        return p;
    }

    // ======================= RIWAYAT (pelanggan) =======================

    /** Riwayat milik pelanggan yang login. filter: null = semua tahap. */
    public List<Pesanan> riwayat(String kataKunci, Pesanan.Tahap filter) {
        int idSaya = AuthController.getPenggunaLogin().getId();
        return cari(kataKunci, filter).stream()
                .filter(p -> p.getPelanggan().getId() == idSaya)
                .collect(Collectors.toList());
    }

    /** Flowchart Riwayat: batalkan pesanan (hanya sebelum diproses). Stok dikembalikan. */
    public void batalkan(Pesanan p) {
        if (!p.bisaDibatalkan()) throw new ValidasiException("Pesanan hanya dapat dibatalkan sebelum diproses.");
        dao.hapus(p.getId());
    }

    /** Flowchart Lacak: barang sudah diterima -> status Diterima / Selesai. */
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
                        || p.getPelanggan().getNama().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    /** Admin mengubah status: Menunggu Pembayaran -> Diproses -> Dikirim -> Selesai, atau Dibatalkan. */
    public void ubahStatus(Pesanan p, Pesanan.Tahap tujuan) {
        if (tujuan == p.getTahap()) throw new ValidasiException("Pilih status yang berbeda dari status saat ini.");
        switch (tujuan) {
            case DIPROSES -> jalankan(p::verifikasiPembayaran);
            case DIKIRIM -> throw new ValidasiException("Isi nomor resi di menu Pengiriman untuk mengirim pesanan.");
            case SELESAI -> jalankan(p::konfirmasiDiterima);
            case DIBATALKAN -> {
                batalkan(p);
                return;
            }
            default -> throw new ValidasiException("Status tidak dapat dikembalikan ke Menunggu Pembayaran.");
        }
        dao.ubah(p);
    }

    // ======================= KELOLA PENGIRIMAN (admin) =======================

    /** Data pengiriman = pesanan yang pembayarannya sudah diverifikasi. filter: null = semua. */
    public List<Pesanan> daftarPengiriman(String kataKunci, Pesanan.TahapKirim filter) {
        String k = kataKunci.toLowerCase().trim();
        return dao.semua().stream()
                .filter(p -> p.getTahap() != Pesanan.Tahap.MENUNGGU_PEMBAYARAN)
                .filter(p -> filter == null || p.getTahapKirim() == filter)
                .filter(p -> p.getNomor().toLowerCase().contains(k)
                        || (p.getNomorResi() != null && p.getNomorResi().toLowerCase().contains(k)))
                .collect(Collectors.toList());
    }

    /** Simpan nomor resi, estimasi tiba, dan status pengiriman. */
    public void simpanPengiriman(Pesanan p, String resi, String estimasi, Pesanan.TahapKirim status) {
        Map<String, String> salah = new LinkedHashMap<>();
        resi = resi.trim().toUpperCase();
        LocalDate tanggal = null;
        if (!resi.matches("[A-Z0-9-]{3,50}")) salah.put("resi", "Nomor resi 3-50 huruf/angka.");
        else if (dao.resiDipakai(resi, p.getId())) salah.put("resi", "Nomor resi sudah digunakan.");
        try {
            tanggal = LocalDate.parse(estimasi.trim(), PromoController.FORMAT_TANGGAL);
        } catch (DateTimeParseException e) {
            salah.put("estimasi", "Format dd/mm/yyyy.");
        }
        if (status == Pesanan.TahapKirim.SIAP_DIKIRIM) salah.put("status", "Pilih Dalam Pengiriman atau Diterima.");
        ValidasiException.cek(salah);

        String resiFinal = resi;
        LocalDate tanggalFinal = tanggal;
        jalankan(() -> {
            if (!p.isDiterima()) p.kirim(resiFinal, tanggalFinal);
            if (status == Pesanan.TahapKirim.DITERIMA && !p.isDiterima()) p.konfirmasiDiterima();
        });
        dao.ubah(p);
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
