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

    /** @param status "Semua promo", "Aktif", "Terjadwal", atau "Berakhir" */
    public List<Promo> cari(String kataKunci, String status) {
        String k = kataKunci.toUpperCase().trim();
        return dao.semua().stream()
                .filter(p -> p.getKode().toUpperCase().contains(k))
                .filter(p -> status.startsWith("Semua") || p.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    /** Promo aktif pertama untuk banner di katalog (boleh null). */
    public Promo promoAktif() {
        return dao.semua().stream().filter(p -> p.getStatus().equals("Aktif")).findFirst().orElse(null);
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
        BigDecimal nilai = ProdukController.angka(diskon);
        if (nilai == null || nilai.signum() <= 0) salah.put("diskon", "Potongan harus lebih dari 0.");
        else if (jenis == 'P' && nilai.intValue() > 100) salah.put("diskon", "Potongan persen maksimal 100.");
        BigDecimal min = ProdukController.angka(minimal);
        if (min == null || min.intValue() < 1) salah.put("minimal", "Minimal 1 barang.");
        LocalDate tglMulai = tanggal(mulai);
        LocalDate tglSelesai = tanggal(selesai);
        if (tglMulai == null) salah.put("mulai", "Format dd/mm/yyyy.");
        if (tglSelesai == null) salah.put("selesai", "Format dd/mm/yyyy.");
        else if (tglMulai != null && tglSelesai.isBefore(tglMulai)) {
            salah.put("selesai", "Tidak boleh mendahului tanggal mulai.");
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
