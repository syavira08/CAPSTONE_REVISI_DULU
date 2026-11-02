package model;

/** INHERITANCE: Admin adalah Pengguna dengan data tambahan dari tabel admin. */
public class Admin extends Pengguna {

    private String jabatan;
    private String noPegawai;

    public Admin(int id, String nama, String username, String password, String jabatan, String noPegawai) {
        super(id, nama, username, password);
        this.jabatan = jabatan;
        this.noPegawai = noPegawai;
    }

    @Override
    public String getPeran() { return "ADMIN"; }

    @Override
    public String getKeterangan() { return getNama() + " • " + jabatan; }

    public String getJabatan() { return jabatan; }
    public String getNoPegawai() { return noPegawai; }
}
