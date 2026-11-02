package view;

import controller.AuthController;
import controller.ValidasiException;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import model.Admin;
import model.Pelanggan;
import model.Pengguna;

/**
 * Flowchart Login & Sign Up:
 *  Registrasi? -> input -> valid? -> simpan akun -> "Registrasi Berhasil"
 *  Login?      -> input -> cocokkan akun -> role Admin? -> Menu Admin : Menu Pelanggan
 * Tampilan dibuat dengan GUI Form Builder (LoginView.form): tiga kartu Login / Daftar / Sukses (CardLayout).
 */
public class LoginView extends javax.swing.JFrame {

    private final AuthController auth = new AuthController();

    public LoginView() {
        initComponents();
        UI.logo(lblLogo);
        lblGambarLaptop.setIcon(new UI.GambarProduk("laptop", 240, 160));
        lblGambarHeadphone.setIcon(new UI.GambarProduk("headphone", 160, 160));
        UI.kartu(pnlKartu, 18, 20, 18, 20);
        UI.tombol(btnTabLogin, UI.Jenis.PRIMER);
        UI.tombol(btnTabDaftar, UI.Jenis.SEKUNDER);
        UI.primer(btnLogin);
        UI.primer(btnDaftar);
        UI.primer(btnKeLogin);
        UI.input(txtUser, "Masukkan username");
        UI.input(pwdPass, null);
        UI.input(txtDNama, "Nama lengkap");
        UI.input(txtDUser, "Username");
        UI.input(txtDTelp, "08xxxxxxxxxx");
        UI.area(txtDAlamat, scrDAlamat);
        UI.input(pwdDPass, null);
        UI.input(pwdDKonf, null);
        UI.sorotan(pnlInfoLogin, 10, 14, 10, 14);
        UI.info(pnlInfoLogin, lblInfoLogin, "Pelanggan masuk ke Katalog. Admin masuk ke Kelola Produk.");
        UI.pesan(pnlSuksesBanner, lblSuksesBanner, UI.Pesan.SUKSES, "Registrasi Berhasil");
        UI.sembunyi(pnlPesanLogin);
        UI.sembunyi(pnlPesanDaftar);
        bersihkanErrorDaftar();
        UI.setError(pwdPass, errPass, null);
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(null);
        pilihTab(true);
    }

    /** Tab aktif berwarna biru, lalu tampilkan form yang sesuai. */
    private void pilihTab(boolean login) {
        UI.tombol(btnTabLogin, login ? UI.Jenis.PRIMER : UI.Jenis.SEKUNDER);
        UI.tombol(btnTabDaftar, login ? UI.Jenis.SEKUNDER : UI.Jenis.PRIMER);
        tampilkanKartu(login ? "login" : "daftar", login ? pnlLogin : pnlDaftar);
    }

    /** Tampilkan satu kartu; tinggi kartu form mengikuti isi kartu yang aktif. */
    private void tampilkanKartu(String nama, JPanel aktif) {
        ((CardLayout) pnlWadah.getLayout()).show(pnlWadah, nama);
        pnlWadah.setPreferredSize(new Dimension(400, aktif.getPreferredSize().height + 18));
        pnlKartuForm.setPreferredSize(new Dimension(440, pnlKartu.getPreferredSize().height));
        pnlKartuForm.revalidate();
        pnlTengah.revalidate();
        pnlTengah.repaint();
    }

    // ===================== LOGIN =====================

    private void login() {
        try {
            Pengguna p = auth.login(txtUser.getText(), new String(pwdPass.getPassword()));
            dispose();
            // POLYMORPHISM: menu yang dibuka tergantung jenis objek pengguna (role)
            if (p instanceof Admin) new AdminView().setVisible(true);
            else new PelangganView().setVisible(true);
        } catch (ValidasiException e) {
            UI.pesan(pnlPesanLogin, lblPesanLogin, UI.Pesan.GALAT, e.getMessage());   // input tetap tersimpan
            UI.setError(pwdPass, errPass, e.getPesan("password"));
            tampilkanKartu("login", pnlLogin);
        } catch (RuntimeException e) {
            UI.pesan(pnlPesanLogin, lblPesanLogin, UI.Pesan.GALAT, e.getMessage());
            tampilkanKartu("login", pnlLogin);
        }
    }

    // ===================== DAFTAR =====================

    private void bersihkanErrorDaftar() {
        tampilkanErrorDaftar(null);
    }

    private void tampilkanErrorDaftar(ValidasiException e) {
        UI.tampilkanError(e, "nama", txtDNama, errDNama, "username", txtDUser, errDUser, "telepon", txtDTelp, errDTelp,
                "alamat", scrDAlamat, errDAlamat, "password", pwdDPass, errDPass, "konfirmasi", pwdDKonf, errDKonf);
    }

    private void daftar() {
        try {
            Pelanggan p = auth.registrasi(txtDNama.getText(), txtDUser.getText(), txtDTelp.getText(), txtDAlamat.getText(),
                    new String(pwdDPass.getPassword()), new String(pwdDKonf.getPassword()));
            bersihkanErrorDaftar();
            UI.sembunyi(pnlPesanDaftar);
            lblSuksesTeks.setText(UI.bungkus("Selamat bergabung, <b>" + p.getNama().split(" ")[0]
                    + "</b>! Akun pelanggan dengan username <b>" + p.getUsername()
                    + "</b> telah dibuat. Silakan login untuk mulai berbelanja.", 360));
            txtUser.setText(p.getUsername());
            tampilkanKartu("sukses", pnlSukses);
        } catch (ValidasiException e) {
            UI.pesan(pnlPesanDaftar, lblPesanDaftar, UI.Pesan.GALAT, e.getMessage());
            tampilkanErrorDaftar(e);
            tampilkanKartu("daftar", pnlDaftar);
        } catch (RuntimeException e) {
            UI.pesan(pnlPesanDaftar, lblPesanDaftar, UI.Pesan.GALAT, e.getMessage());
            tampilkanKartu("daftar", pnlDaftar);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlBilah = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        pnlTengah = new javax.swing.JPanel();
        pnlHero = new javax.swing.JPanel();
        lblTag = new javax.swing.JLabel();
        lblHeroJudul = new javax.swing.JLabel();
        lblHeroDeskripsi = new javax.swing.JLabel();
        pnlGambar = new javax.swing.JPanel();
        lblGambarLaptop = new javax.swing.JLabel();
        lblGambarHeadphone = new javax.swing.JLabel();
        lblJaminan = new javax.swing.JLabel();
        pnlKartuForm = new javax.swing.JPanel();
        pnlKartu = new javax.swing.JPanel();
        pnlTab = new javax.swing.JPanel();
        btnTabLogin = new javax.swing.JButton();
        btnTabDaftar = new javax.swing.JButton();
        pnlWadah = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        lblSelamat = new javax.swing.JLabel();
        lblSelamatSub = new javax.swing.JLabel();
        pnlPesanLogin = new javax.swing.JPanel();
        lblPesanLogin = new javax.swing.JLabel();
        pnlUser = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        errUser = new javax.swing.JLabel();
        pnlPass = new javax.swing.JPanel();
        lblPass = new javax.swing.JLabel();
        pwdPass = new javax.swing.JPasswordField();
        errPass = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        pnlInfoLogin = new javax.swing.JPanel();
        lblInfoLogin = new javax.swing.JLabel();
        lblBelum = new javax.swing.JLabel();
        pnlDaftar = new javax.swing.JPanel();
        lblDaftarJudul = new javax.swing.JLabel();
        lblDaftarSub = new javax.swing.JLabel();
        pnlPesanDaftar = new javax.swing.JPanel();
        lblPesanDaftar = new javax.swing.JLabel();
        pnlNamaUser = new javax.swing.JPanel();
        pnlDNama = new javax.swing.JPanel();
        lblDNama = new javax.swing.JLabel();
        txtDNama = new javax.swing.JTextField();
        errDNama = new javax.swing.JLabel();
        pnlDUser = new javax.swing.JPanel();
        lblDUser = new javax.swing.JLabel();
        txtDUser = new javax.swing.JTextField();
        errDUser = new javax.swing.JLabel();
        pnlDTelp = new javax.swing.JPanel();
        lblDTelp = new javax.swing.JLabel();
        txtDTelp = new javax.swing.JTextField();
        errDTelp = new javax.swing.JLabel();
        pnlDAlamat = new javax.swing.JPanel();
        lblDAlamat = new javax.swing.JLabel();
        scrDAlamat = new javax.swing.JScrollPane();
        txtDAlamat = new javax.swing.JTextArea();
        errDAlamat = new javax.swing.JLabel();
        pnlSandi = new javax.swing.JPanel();
        pnlDPass = new javax.swing.JPanel();
        lblDPass = new javax.swing.JLabel();
        pwdDPass = new javax.swing.JPasswordField();
        errDPass = new javax.swing.JLabel();
        pnlDKonf = new javax.swing.JPanel();
        lblDKonf = new javax.swing.JLabel();
        pwdDKonf = new javax.swing.JPasswordField();
        errDKonf = new javax.swing.JLabel();
        lblMinimal = new javax.swing.JLabel();
        btnDaftar = new javax.swing.JButton();
        pnlSukses = new javax.swing.JPanel();
        lblSuksesJudul = new javax.swing.JLabel();
        pnlSuksesBanner = new javax.swing.JPanel();
        lblSuksesBanner = new javax.swing.JLabel();
        lblSuksesTeks = new javax.swing.JLabel();
        btnKeLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HAULECTRA • Login");
        setPreferredSize(new java.awt.Dimension(1280, 820));

        pnlBilah.setBackground(new java.awt.Color(255, 255, 255));
        pnlBilah.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(16, 40, 16, 40)));
        pnlBilah.setLayout(new java.awt.BorderLayout());

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(23, 43, 77));
        lblLogo.setText("HAULECTRA");
        pnlBilah.add(lblLogo, java.awt.BorderLayout.WEST);

        lblTagline.setForeground(new java.awt.Color(94, 108, 132));
        lblTagline.setText("Aplikasi toko elektronik");
        pnlBilah.add(lblTagline, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlBilah, java.awt.BorderLayout.NORTH);

        pnlTengah.setBackground(new java.awt.Color(243, 246, 250));
        pnlTengah.setLayout(new java.awt.GridBagLayout());

        pnlHero.setPreferredSize(new java.awt.Dimension(480, 430));
        pnlHero.setOpaque(false);
        pnlHero.setLayout(new java.awt.GridBagLayout());

        lblTag.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTag.setForeground(new java.awt.Color(33, 97, 213));
        lblTag.setText("PILIHAN ELEKTRONIK TERPERCAYA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlHero.add(lblTag, gridBagConstraints);

        lblHeroJudul.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        lblHeroJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblHeroJudul.setText("<html>Teknologi pilihan.<br>Belanja lebih nyaman.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlHero.add(lblHeroJudul, gridBagConstraints);

        lblHeroDeskripsi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHeroDeskripsi.setForeground(new java.awt.Color(94, 108, 132));
        lblHeroDeskripsi.setText("<html><div width=420>Temukan laptop, smartphone, audio, dan aksesori untuk melengkapi aktivitas Anda.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlHero.add(lblHeroDeskripsi, gridBagConstraints);

        pnlGambar.setOpaque(false);
        pnlGambar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 0));

        lblGambarLaptop.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblGambarLaptop.setForeground(new java.awt.Color(23, 43, 77));
        lblGambarLaptop.setPreferredSize(new java.awt.Dimension(240, 160));
        pnlGambar.add(lblGambarLaptop);

        lblGambarHeadphone.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblGambarHeadphone.setForeground(new java.awt.Color(23, 43, 77));
        lblGambarHeadphone.setPreferredSize(new java.awt.Dimension(160, 160));
        pnlGambar.add(lblGambarHeadphone);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlHero.add(pnlGambar, gridBagConstraints);

        lblJaminan.setForeground(new java.awt.Color(94, 108, 132));
        lblJaminan.setText("✓ Produk original     ✓ Transaksi aman     ✓ Pengiriman terlacak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlHero.add(lblJaminan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 60);
        pnlTengah.add(pnlHero, gridBagConstraints);

        pnlKartuForm.setPreferredSize(new java.awt.Dimension(440, 520));
        pnlKartuForm.setOpaque(false);
        pnlKartuForm.setLayout(new java.awt.BorderLayout());

        pnlKartu.setBackground(new java.awt.Color(255, 255, 255));
        pnlKartu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlKartu.setLayout(new java.awt.BorderLayout());

        pnlTab.setOpaque(false);
        pnlTab.setLayout(new java.awt.GridLayout(1, 2, 8, 0));

        btnTabLogin.setBackground(new java.awt.Color(33, 97, 213));
        btnTabLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTabLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnTabLogin.setText("Login");
        btnTabLogin.setFocusPainted(false);
        btnTabLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabLoginActionPerformed(evt);
            }
        });
        pnlTab.add(btnTabLogin);

        btnTabDaftar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTabDaftar.setForeground(new java.awt.Color(23, 43, 77));
        btnTabDaftar.setText("Daftar");
        btnTabDaftar.setFocusPainted(false);
        btnTabDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabDaftarActionPerformed(evt);
            }
        });
        pnlTab.add(btnTabDaftar);

        pnlKartu.add(pnlTab, java.awt.BorderLayout.NORTH);

        pnlWadah.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 0, 0, 0));
        pnlWadah.setOpaque(false);
        pnlWadah.setLayout(new java.awt.CardLayout());

        pnlLogin.setOpaque(false);
        pnlLogin.setLayout(new java.awt.GridBagLayout());

        lblSelamat.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSelamat.setForeground(new java.awt.Color(23, 43, 77));
        lblSelamat.setText("Selamat datang kembali");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlLogin.add(lblSelamat, gridBagConstraints);

        lblSelamatSub.setForeground(new java.awt.Color(94, 108, 132));
        lblSelamatSub.setText("Masuk untuk melanjutkan aktivitas Anda.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(lblSelamatSub, gridBagConstraints);

        pnlPesanLogin.setBackground(new java.awt.Color(253, 236, 236));
        pnlPesanLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesanLogin.setLayout(new java.awt.BorderLayout());

        lblPesanLogin.setForeground(new java.awt.Color(192, 52, 63));
        lblPesanLogin.setText("Pesan");
        pnlPesanLogin.add(lblPesanLogin, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(pnlPesanLogin, gridBagConstraints);

        pnlUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlUser.setOpaque(false);
        pnlUser.setLayout(new java.awt.GridBagLayout());

        lblUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUser.setForeground(new java.awt.Color(23, 43, 77));
        lblUser.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlUser.add(lblUser, gridBagConstraints);

        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUser.setForeground(new java.awt.Color(23, 43, 77));
        txtUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtUser.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlUser.add(txtUser, gridBagConstraints);

        errUser.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errUser.setForeground(new java.awt.Color(192, 52, 63));
        errUser.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlUser.add(errUser, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(pnlUser, gridBagConstraints);

        pnlPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlPass.setOpaque(false);
        pnlPass.setLayout(new java.awt.GridBagLayout());

        lblPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPass.setForeground(new java.awt.Color(23, 43, 77));
        lblPass.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlPass.add(lblPass, gridBagConstraints);

        pwdPass.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pwdPass.setForeground(new java.awt.Color(23, 43, 77));
        pwdPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        pwdPass.setPreferredSize(new java.awt.Dimension(160, 38));
        pwdPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdPassActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlPass.add(pwdPass, gridBagConstraints);

        errPass.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errPass.setForeground(new java.awt.Color(192, 52, 63));
        errPass.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlPass.add(errPass, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlLogin.add(pnlPass, gridBagConstraints);

        btnLogin.setBackground(new java.awt.Color(33, 97, 213));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlLogin.add(btnLogin, gridBagConstraints);

        pnlInfoLogin.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfoLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfoLogin.setLayout(new java.awt.BorderLayout());

        lblInfoLogin.setForeground(new java.awt.Color(33, 97, 213));
        lblInfoLogin.setText("Pelanggan masuk ke Katalog. Admin masuk ke Kelola Produk.");
        pnlInfoLogin.add(lblInfoLogin, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(pnlInfoLogin, gridBagConstraints);

        lblBelum.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblBelum.setForeground(new java.awt.Color(94, 108, 132));
        lblBelum.setText("Belum memiliki akun? Pilih tab Daftar untuk membuat akun pelanggan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(lblBelum, gridBagConstraints);

        pnlWadah.add(pnlLogin, "login");

        pnlDaftar.setOpaque(false);
        pnlDaftar.setLayout(new java.awt.GridBagLayout());

        lblDaftarJudul.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDaftarJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblDaftarJudul.setText("Buat akun pelanggan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDaftar.add(lblDaftarJudul, gridBagConstraints);

        lblDaftarSub.setForeground(new java.awt.Color(94, 108, 132));
        lblDaftarSub.setText("Lengkapi data diri Anda. Semua field wajib diisi.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlDaftar.add(lblDaftarSub, gridBagConstraints);

        pnlPesanDaftar.setBackground(new java.awt.Color(253, 236, 236));
        pnlPesanDaftar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesanDaftar.setLayout(new java.awt.BorderLayout());

        lblPesanDaftar.setForeground(new java.awt.Color(192, 52, 63));
        lblPesanDaftar.setText("Pesan");
        pnlPesanDaftar.add(lblPesanDaftar, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlDaftar.add(pnlPesanDaftar, gridBagConstraints);

        pnlNamaUser.setOpaque(false);
        pnlNamaUser.setLayout(new java.awt.GridLayout(1, 2, 14, 0));

        pnlDNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDNama.setOpaque(false);
        pnlDNama.setLayout(new java.awt.GridBagLayout());

        lblDNama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDNama.setForeground(new java.awt.Color(23, 43, 77));
        lblDNama.setText("Nama lengkap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDNama.add(lblDNama, gridBagConstraints);

        txtDNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDNama.setForeground(new java.awt.Color(23, 43, 77));
        txtDNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtDNama.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDNama.add(txtDNama, gridBagConstraints);

        errDNama.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDNama.setForeground(new java.awt.Color(192, 52, 63));
        errDNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDNama.add(errDNama, gridBagConstraints);

        pnlNamaUser.add(pnlDNama);

        pnlDUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDUser.setOpaque(false);
        pnlDUser.setLayout(new java.awt.GridBagLayout());

        lblDUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDUser.setForeground(new java.awt.Color(23, 43, 77));
        lblDUser.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDUser.add(lblDUser, gridBagConstraints);

        txtDUser.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDUser.setForeground(new java.awt.Color(23, 43, 77));
        txtDUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtDUser.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDUser.add(txtDUser, gridBagConstraints);

        errDUser.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDUser.setForeground(new java.awt.Color(192, 52, 63));
        errDUser.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDUser.add(errDUser, gridBagConstraints);

        pnlNamaUser.add(pnlDUser);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlDaftar.add(pnlNamaUser, gridBagConstraints);

        pnlDTelp.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDTelp.setOpaque(false);
        pnlDTelp.setLayout(new java.awt.GridBagLayout());

        lblDTelp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDTelp.setForeground(new java.awt.Color(23, 43, 77));
        lblDTelp.setText("No. telepon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDTelp.add(lblDTelp, gridBagConstraints);

        txtDTelp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDTelp.setForeground(new java.awt.Color(23, 43, 77));
        txtDTelp.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtDTelp.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDTelp.add(txtDTelp, gridBagConstraints);

        errDTelp.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDTelp.setForeground(new java.awt.Color(192, 52, 63));
        errDTelp.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDTelp.add(errDTelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlDaftar.add(pnlDTelp, gridBagConstraints);

        pnlDAlamat.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDAlamat.setOpaque(false);
        pnlDAlamat.setLayout(new java.awt.GridBagLayout());

        lblDAlamat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDAlamat.setForeground(new java.awt.Color(23, 43, 77));
        lblDAlamat.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDAlamat.add(lblDAlamat, gridBagConstraints);

        scrDAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        scrDAlamat.setPreferredSize(new java.awt.Dimension(160, 70));

        txtDAlamat.setColumns(20);
        txtDAlamat.setRows(2);
        txtDAlamat.setLineWrap(true);
        txtDAlamat.setWrapStyleWord(true);
        txtDAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDAlamat.setForeground(new java.awt.Color(23, 43, 77));
        scrDAlamat.setViewportView(txtDAlamat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDAlamat.add(scrDAlamat, gridBagConstraints);

        errDAlamat.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDAlamat.setForeground(new java.awt.Color(192, 52, 63));
        errDAlamat.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDAlamat.add(errDAlamat, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlDaftar.add(pnlDAlamat, gridBagConstraints);

        pnlSandi.setOpaque(false);
        pnlSandi.setLayout(new java.awt.GridLayout(1, 2, 14, 0));

        pnlDPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDPass.setOpaque(false);
        pnlDPass.setLayout(new java.awt.GridBagLayout());

        lblDPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDPass.setForeground(new java.awt.Color(23, 43, 77));
        lblDPass.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDPass.add(lblDPass, gridBagConstraints);

        pwdDPass.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pwdDPass.setForeground(new java.awt.Color(23, 43, 77));
        pwdDPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        pwdDPass.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDPass.add(pwdDPass, gridBagConstraints);

        errDPass.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDPass.setForeground(new java.awt.Color(192, 52, 63));
        errDPass.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDPass.add(errDPass, gridBagConstraints);

        pnlSandi.add(pnlDPass);

        pnlDKonf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDKonf.setOpaque(false);
        pnlDKonf.setLayout(new java.awt.GridBagLayout());

        lblDKonf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDKonf.setForeground(new java.awt.Color(23, 43, 77));
        lblDKonf.setText("Konfirmasi password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDKonf.add(lblDKonf, gridBagConstraints);

        pwdDKonf.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pwdDKonf.setForeground(new java.awt.Color(23, 43, 77));
        pwdDKonf.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        pwdDKonf.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDKonf.add(pwdDKonf, gridBagConstraints);

        errDKonf.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDKonf.setForeground(new java.awt.Color(192, 52, 63));
        errDKonf.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDKonf.add(errDKonf, gridBagConstraints);

        pnlSandi.add(pnlDKonf);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlDaftar.add(pnlSandi, gridBagConstraints);

        lblMinimal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMinimal.setForeground(new java.awt.Color(94, 108, 132));
        lblMinimal.setText("Gunakan minimal 8 karakter untuk password Anda.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlDaftar.add(lblMinimal, gridBagConstraints);

        btnDaftar.setBackground(new java.awt.Color(33, 97, 213));
        btnDaftar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDaftar.setForeground(new java.awt.Color(255, 255, 255));
        btnDaftar.setText("Daftar");
        btnDaftar.setFocusPainted(false);
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlDaftar.add(btnDaftar, gridBagConstraints);

        pnlWadah.add(pnlDaftar, "daftar");

        pnlSukses.setOpaque(false);
        pnlSukses.setLayout(new java.awt.GridBagLayout());

        lblSuksesJudul.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSuksesJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblSuksesJudul.setText("Akun siap digunakan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSukses.add(lblSuksesJudul, gridBagConstraints);

        pnlSuksesBanner.setBackground(new java.awt.Color(234, 246, 236));
        pnlSuksesBanner.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlSuksesBanner.setLayout(new java.awt.BorderLayout());

        lblSuksesBanner.setForeground(new java.awt.Color(46, 125, 50));
        lblSuksesBanner.setText("Registrasi Berhasil");
        pnlSuksesBanner.add(lblSuksesBanner, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlSukses.add(pnlSuksesBanner, gridBagConstraints);

        lblSuksesTeks.setForeground(new java.awt.Color(94, 108, 132));
        lblSuksesTeks.setText("Silakan login untuk mulai berbelanja.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlSukses.add(lblSuksesTeks, gridBagConstraints);

        btnKeLogin.setBackground(new java.awt.Color(33, 97, 213));
        btnKeLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKeLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnKeLogin.setText("Ke Login");
        btnKeLogin.setFocusPainted(false);
        btnKeLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlSukses.add(btnKeLogin, gridBagConstraints);

        pnlWadah.add(pnlSukses, "sukses");

        pnlKartu.add(pnlWadah, java.awt.BorderLayout.CENTER);

        pnlKartuForm.add(pnlKartu, java.awt.BorderLayout.NORTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlTengah.add(pnlKartuForm, gridBagConstraints);

        getContentPane().add(pnlTengah, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTabLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabLoginActionPerformed
        pilihTab(true);
    }//GEN-LAST:event_btnTabLoginActionPerformed

    private void btnTabDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabDaftarActionPerformed
        pilihTab(false);
    }//GEN-LAST:event_btnTabDaftarActionPerformed

    private void pwdPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdPassActionPerformed
        login();
    }//GEN-LAST:event_pwdPassActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        daftar();
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void btnKeLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeLoginActionPerformed
        pilihTab(true);
    }//GEN-LAST:event_btnKeLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftar;
    private javax.swing.JButton btnKeLogin;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnTabDaftar;
    private javax.swing.JButton btnTabLogin;
    private javax.swing.JLabel errDAlamat;
    private javax.swing.JLabel errDKonf;
    private javax.swing.JLabel errDNama;
    private javax.swing.JLabel errDPass;
    private javax.swing.JLabel errDTelp;
    private javax.swing.JLabel errDUser;
    private javax.swing.JLabel errPass;
    private javax.swing.JLabel errUser;
    private javax.swing.JLabel lblBelum;
    private javax.swing.JLabel lblDAlamat;
    private javax.swing.JLabel lblDKonf;
    private javax.swing.JLabel lblDNama;
    private javax.swing.JLabel lblDPass;
    private javax.swing.JLabel lblDTelp;
    private javax.swing.JLabel lblDUser;
    private javax.swing.JLabel lblDaftarJudul;
    private javax.swing.JLabel lblDaftarSub;
    private javax.swing.JLabel lblGambarHeadphone;
    private javax.swing.JLabel lblGambarLaptop;
    private javax.swing.JLabel lblHeroDeskripsi;
    private javax.swing.JLabel lblHeroJudul;
    private javax.swing.JLabel lblInfoLogin;
    private javax.swing.JLabel lblJaminan;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMinimal;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPesanDaftar;
    private javax.swing.JLabel lblPesanLogin;
    private javax.swing.JLabel lblSelamat;
    private javax.swing.JLabel lblSelamatSub;
    private javax.swing.JLabel lblSuksesBanner;
    private javax.swing.JLabel lblSuksesJudul;
    private javax.swing.JLabel lblSuksesTeks;
    private javax.swing.JLabel lblTag;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlBilah;
    private javax.swing.JPanel pnlDAlamat;
    private javax.swing.JPanel pnlDKonf;
    private javax.swing.JPanel pnlDNama;
    private javax.swing.JPanel pnlDPass;
    private javax.swing.JPanel pnlDTelp;
    private javax.swing.JPanel pnlDUser;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlGambar;
    private javax.swing.JPanel pnlHero;
    private javax.swing.JPanel pnlInfoLogin;
    private javax.swing.JPanel pnlKartu;
    private javax.swing.JPanel pnlKartuForm;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlNamaUser;
    private javax.swing.JPanel pnlPass;
    private javax.swing.JPanel pnlPesanDaftar;
    private javax.swing.JPanel pnlPesanLogin;
    private javax.swing.JPanel pnlSandi;
    private javax.swing.JPanel pnlSukses;
    private javax.swing.JPanel pnlSuksesBanner;
    private javax.swing.JPanel pnlTab;
    private javax.swing.JPanel pnlTengah;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JPanel pnlWadah;
    private javax.swing.JPasswordField pwdDKonf;
    private javax.swing.JPasswordField pwdDPass;
    private javax.swing.JPasswordField pwdPass;
    private javax.swing.JScrollPane scrDAlamat;
    private javax.swing.JTextArea txtDAlamat;
    private javax.swing.JTextField txtDNama;
    private javax.swing.JTextField txtDTelp;
    private javax.swing.JTextField txtDUser;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
