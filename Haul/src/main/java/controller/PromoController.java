package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Promo;
import model.PromoDAO;

/** Use case: Kelola promo (admin) dan Memasukkan kode voucher (pelanggan). */
public class PromoController {

    public static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final PromoDAO dao = new PromoDAO();

    /** Cari promo berdasarkan kode voucher. */
    public List<Promo> cari(String kataKunci) {
        String k = kataKunci.toUpperCase().trim();
        return dao.semua().stream()
                .filter(p -> p.getKode().toUpperCase().contains(k))
                .collect(Collectors.toList());
    }

    /** Promo yang sedang berlaku: ditampilkan di Beranda dan bisa dipilih saat checkout. */
    public List<Promo> promoAktif() {
        return dao.semua().stream().filter(p -> p.getStatus().equals("Aktif")).collect(Collectors.toList());
    }

    /** Tambah (id = 0) atau ubah promo. jenis: 'P' persen / 'N' nominal. */
    public void simpan(int id, String kode, char jenis, String diskon, String minimal, String mulai, String selesai) {
        Map<String, String> salah = new LinkedHashMap<>();
        kode = kode.trim().toUpperCase();
        if (!kode.matches("[A-Z0-9]{3,30}")) salah.put("kode", "Kode 3-30 huruf/angka tanpa spasi.");
        else {
            Promo lain = dao.cariKode(kode);
            if (lain != null && lain.getId() != id) salah.put("kode", "Kode voucher sudah digunakan.");
        }
        if (jenis != 'P' && jenis != 'N') salah.put("jenis", "Pilih jenis potongan.");
        BigDecimal nilai = ProdukController.angka(diskon);
        if (nilai == null || nilai.signum() <= 0) salah.put("diskon", "Potongan harus berupa angka lebih dari 0.");
        else if (jenis == 'P' && nilai.compareTo(BigDecimal.valueOf(90)) > 0) salah.put("diskon", "Potongan persen maksimal 90%.");
        else if (jenis == 'N' && nilai.compareTo(BigDecimal.valueOf(1000)) < 0) salah.put("diskon", "Potongan nominal minimal Rp1.000.");
        else if (jenis == 'N' && nilai.compareTo(BigDecimal.valueOf(5_000_000)) > 0) salah.put("diskon", "Potongan nominal maksimal Rp5.000.000.");
        BigDecimal min = ProdukController.angka(minimal);
        if (min == null || min.intValue() < 1 || min.intValue() > 100) salah.put("minimal", "Isi 1 sampai 100 barang.");
        LocalDate tglMulai = tanggal(mulai);
        LocalDate tglSelesai = tanggal(selesai);
        if (tglMulai == null) salah.put("mulai", "Format tanggal dd/mm/yyyy.");
        if (tglSelesai == null) salah.put("selesai", "Format tanggal dd/mm/yyyy.");
        else if (tglMulai != null && tglSelesai.isBefore(tglMulai)) {
            salah.put("selesai", "Tidak boleh sebelum tanggal mulai.");
        } else if (id == 0 && tglSelesai.isBefore(LocalDate.now())) {
            salah.put("selesai", "Promo baru tidak boleh sudah berakhir.");
        }
        ValidasiException.cek(salah);

        Promo p = Promo.buat(jenis);   // POLYMORPHISM: Persen atau Nominal
        p.setId(id);
        p.setKode(kode);
        p.setDiskon(nilai);
        p.setMinimalBarang(min.intValue());
        p.setMulai(tglMulai);
        p.setSelesai(tglSelesai);
        p.setIdAdmin(AuthController.getPenggunaLogin().getId());
        if (id == 0) dao.simpan(p);
        else dao.ubah(p);
    }

    public void hapus(int id) {
        if (dao.dipakaiPesanan(id)) {
            throw new ValidasiException("Voucher sudah dipakai pada pesanan sehingga tidak dapat dihapus.");
        }
        dao.hapus(id);
    }

    /** Validasi voucher saat checkout. Kode kosong = tanpa voucher (null). */
    public Promo cekVoucher(String kode, int jumlahBarang) {
        if (kode == null || kode.isBlank()) return null;
        Promo p = dao.cariKode(kode);
        if (p == null) throw new ValidasiException("Kode voucher tidak ditemukan.");
        if (!p.getStatus().equals("Aktif")) throw new ValidasiException("Voucher " + p.getKode() + " sedang tidak berlaku.");
        if (jumlahBarang < p.getMinimalBarang()) {
            throw new ValidasiException("Voucher " + p.getKode() + " butuh minimal " + p.getMinimalBarang() + " barang.");
        }
        return p;
    }

    private static LocalDate tanggal(String teks) {
        try {
            return LocalDate.parse(teks.trim(), FORMAT_TANGGAL);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
