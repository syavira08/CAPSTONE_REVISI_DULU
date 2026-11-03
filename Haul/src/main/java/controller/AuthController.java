package controller;

import java.util.LinkedHashMap;
import java.util.Map;
import model.Pelanggan;
import model.Pengguna;
import model.PenggunaDAO;

/** Use case: Login, Registrasi akun (pelanggan), Logout. Sesuai flowchart Login & Sign Up. */
public class AuthController {

    private static Pengguna penggunaLogin;   // pengguna yang sedang login
    private final PenggunaDAO dao = new PenggunaDAO();

    public static Pengguna getPenggunaLogin() {
        return penggunaLogin;
    }

    /** Login lalu kembalikan pengguna (Admin / Pelanggan) untuk menentukan menu yang dibuka. */
    public Pengguna login(String username, String password) {
        Map<String, String> salah = new LinkedHashMap<>();
        if (username.isBlank()) salah.put("username", "Username wajib diisi.");
        if (password.isEmpty()) salah.put("password", "Password wajib diisi.");
        ValidasiException.cek(salah);
        Pengguna p = dao.login(username.trim(), password);
        if (p == null) {
            throw new ValidasiException("Username atau password salah.",
                    Map.of("password", "Username atau password salah."));
        }
        penggunaLogin = p;
        return p;
    }

    /** Registrasi khusus pelanggan: validasi -> simpan akun ke database. */
    public Pelanggan registrasi(String nama, String username, String telepon, String alamat,
                                String password, String konfirmasi) {
        Map<String, String> salah = periksaData(nama, username, telepon, alamat, 0);
        if (password.length() < 8) salah.put("password", "Minimal 8 karakter.");
        if (!password.equals(konfirmasi)) salah.put("konfirmasi", "Password tidak sama.");
        ValidasiException.cek(salah);

        Pelanggan baru = new Pelanggan(0, nama.trim(), username.trim(), password, alamat.trim(), telepon.trim(), null);
        dao.simpan(baru);
        return baru;
    }

    public void logout() {
        penggunaLogin = null;
    }

    /** Aturan data diri (dipakai registrasi & ubah profil). idSendiri = 0 untuk akun baru. */
    static Map<String, String> periksaData(String nama, String username, String telepon, String alamat, int idSendiri) {
        Map<String, String> salah = new LinkedHashMap<>();
        if (nama.isBlank()) salah.put("nama", "Nama lengkap wajib diisi.");
        if (!username.trim().matches("[A-Za-z0-9_.]{4,50}")) {
            salah.put("username", "4-50 huruf/angka tanpa spasi.");
        } else {
            Pengguna lain = new PenggunaDAO().cariUsername(username.trim());
            if (lain != null && lain.getId() != idSendiri) salah.put("username", "Username sudah digunakan.");
        }
        if (!telepon.trim().matches("\\d{10,13}")) salah.put("telepon", "Masukkan 10-13 digit angka.");
        if (alamat.isBlank()) salah.put("alamat", "Alamat wajib diisi.");
        return salah;
    }
}
