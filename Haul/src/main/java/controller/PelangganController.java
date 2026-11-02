package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Pelanggan;
import model.PenggunaDAO;

/** Use case: Melihat & mengubah profil (pelanggan), Melihat data pelanggan (admin). */
public class PelangganController {

    private final PenggunaDAO dao = new PenggunaDAO();

    /** Flowchart Profil: ambil data identitas berdasarkan ID pengguna yang login. */
    public Pelanggan profilSaya() {
        return (Pelanggan) dao.cariId(AuthController.getPenggunaLogin().getId());
    }

    /** Flowchart Profil: input data -> valid? -> simpan ke database. */
    public void ubahProfil(String nama, String username, String telepon, String alamat) {
        Pelanggan p = profilSaya();
        Map<String, String> salah = AuthController.periksaData(nama, username, telepon, alamat, p.getId());
        ValidasiException.cek(salah);
        p.setNama(nama.trim());
        p.setUsername(username.trim());
        p.setNoTelepon(telepon.trim());
        p.setAlamat(alamat.trim());
        dao.ubah(p);
        AuthController.getPenggunaLogin().setNama(p.getNama());   // nama di pojok kanan atas ikut berubah
        AuthController.getPenggunaLogin().setUsername(p.getUsername());
    }

    /** Admin: daftar pelanggan (hanya-baca) dengan pencarian nama/username. */
    public List<Pelanggan> daftar(String kataKunci) {
        String k = kataKunci.toLowerCase().trim();
        return dao.semuaPelanggan().stream()
                .filter(p -> p.getNama().toLowerCase().contains(k) || p.getUsername().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }
}
