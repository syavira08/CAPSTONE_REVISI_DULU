package model;

/**
 * ABSTRACTION + ENCAPSULATION
 * Kelas induk abstrak untuk semua pengguna (tabel pengguna).
 * Tidak bisa dibuat langsung; harus lewat turunannya: Admin atau Pelanggan.
 */
public abstract class Pengguna {

    private int id;
    private String nama;
    private String username;
    private String password;

    protected Pengguna(int id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    /** POLYMORPHISM: tiap turunan mengisi sendiri. Nilainya = kolom pengguna.peran. */
    public abstract String getPeran();

    /** POLYMORPHISM: keterangan singkat yang tampil di pojok kanan atas aplikasi. */
    public abstract String getKeterangan();

    public boolean cocokPassword(String input) {
        return password.equals(input);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    /** Password hanya dibaca DAO (package yang sama), tidak dibuka ke View. */
    String getPassword() { return password; }
}
