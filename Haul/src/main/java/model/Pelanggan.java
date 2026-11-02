package model;

import java.time.LocalDateTime;

/** INHERITANCE: Pelanggan adalah Pengguna dengan data tambahan dari tabel pelanggan. */
public class Pelanggan extends Pengguna {

    private String alamat;
    private String noTelepon;
    private LocalDateTime tanggalBergabung;

    public Pelanggan(int id, String nama, String username, String password,
                     String alamat, String noTelepon, LocalDateTime tanggalBergabung) {
        super(id, nama, username, password);
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.tanggalBergabung = tanggalBergabung;
    }

    @Override
    public String getPeran() { return "PELANGGAN"; }

    @Override
    public String getKeterangan() { return getNama(); }

    /** Kode tampilan, mis. PLG-005. */
    public String getKode() { return String.format("PLG-%03d", getId()); }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
    public LocalDateTime getTanggalBergabung() { return tanggalBergabung; }
}
