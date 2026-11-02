package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * ABSTRACTION + POLYMORPHISM
 * Voucher promo (tabel promo). Cara menghitung potongan berbeda per jenis:
 *   Promo.Persen  (jenis_promo = 'P') -> diskon dalam persen
 *   Promo.Nominal (jenis_promo = 'N') -> diskon dalam rupiah
 */
public abstract class Promo {

    private int id;
    private String kode;
    private BigDecimal diskon;
    private int minimalBarang;
    private LocalDate mulai;
    private LocalDate selesai;
    private int idAdmin;

    /** Hitung besar potongan untuk subtotal belanja. */
    public abstract BigDecimal hitungPotongan(BigDecimal subtotal);

    /** 'P' atau 'N' sesuai kolom jenis_promo. */
    public abstract char getJenis();

    /** Teks diskon untuk tampilan, mis. "10%" atau "Rp20.000". */
    public abstract String getTeksDiskon();

    /** Buat objek promo sesuai jenisnya. */
    public static Promo buat(char jenis) {
        return jenis == 'P' ? new Persen() : new Nominal();
    }

    /** Status dihitung dari periode: Aktif, Terjadwal, atau Berakhir. */
    public String getStatus() {
        LocalDate hariIni = LocalDate.now();
        if (hariIni.isBefore(mulai)) return "Terjadwal";
        if (hariIni.isAfter(selesai)) return "Berakhir";
        return "Aktif";
    }

    public String getLabelJenis() { return getJenis() == 'P' ? "Persen (%)" : "Nominal (Rp)"; }

    // ---------- turunan ----------

    public static class Persen extends Promo {
        @Override
        public BigDecimal hitungPotongan(BigDecimal subtotal) {
            return subtotal.multiply(getDiskon()).divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
        }
        @Override public char getJenis() { return 'P'; }
        @Override public String getTeksDiskon() { return getDiskon().stripTrailingZeros().toPlainString() + "%"; }
    }

    public static class Nominal extends Promo {
        @Override
        public BigDecimal hitungPotongan(BigDecimal subtotal) {
            return getDiskon().min(subtotal);   // potongan tidak melebihi belanja
        }
        @Override public char getJenis() { return 'N'; }
        @Override public String getTeksDiskon() { return "Rp" + String.format("%,d", getDiskon().longValue()).replace(',', '.'); }
    }

    // ---------- getter & setter ----------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }
    public BigDecimal getDiskon() { return diskon; }
    public void setDiskon(BigDecimal diskon) { this.diskon = diskon; }
    public int getMinimalBarang() { return minimalBarang; }
    public void setMinimalBarang(int minimalBarang) { this.minimalBarang = minimalBarang; }
    public LocalDate getMulai() { return mulai; }
    public void setMulai(LocalDate mulai) { this.mulai = mulai; }
    public LocalDate getSelesai() { return selesai; }
    public void setSelesai(LocalDate selesai) { this.selesai = selesai; }
    public int getIdAdmin() { return idAdmin; }
    public void setIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }
}
