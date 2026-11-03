package view;

import controller.AuthController;
import controller.ValidasiException;
import model.Admin;
import model.Pelanggan;
import model.Pengguna;

/**
 * Halaman Login & Daftar (flowchart Login dan Sign Up).
 * Pesan kesalahan tampil langsung di bawah input yang salah.
 */
public class LoginView extends javax.swing.JFrame {

    private final AuthController auth = new AuthController();

    public LoginView() {
        initComponents();
        tampilTab(true);
    }

    /** Pindah tab Login / Daftar (CardLayout). */
    private void tampilTab(boolean login) {
        ((java.awt.CardLayout) pnlIsi.getLayout()).show(pnlIsi, login ? "login" : "daftar");
        warnaiTab(btnTabLogin, login);
        warnaiTab(btnTabDaftar, !login);
        getRootPane().setDefaultButton(login ? btnLogin : btnDaftar);
    }

    private void warnaiTab(javax.swing.JButton tab, boolean aktif) {
        tab.setBackground(aktif ? Gaya.BIRU : java.awt.Color.WHITE);
        tab.setForeground(aktif ? java.awt.Color.WHITE : Gaya.REDUP);
        tab.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    private void login() {
        Gaya.error(lblErrUsername, null);
        Gaya.error(lblErrPassword, null);
        try {
            Pengguna p = auth.login(txtUsername.getText(), new String(txtPassword.getPassword()));
            dispose();
            if (p instanceof Admin) {
                new AdminView().setVisible(true);
            } else {
                new PelangganView().setVisible(true);
            }
        } catch (ValidasiException e) {
            Gaya.error(lblErrUsername, e.getPesan("username"));
            Gaya.error(lblErrPassword, e.getPesan("password"));
        } catch (RuntimeException e) {
            KonfirmasiDialog.gagal(this, "Tidak dapat terhubung ke database. Periksa pengaturan di Konfigurasi.");
        }
    }

    private void daftar() {
        javax.swing.JLabel[] semua = {lblErrNama, lblErrUsernameBaru, lblErrTelepon, lblErrAlamat,
            lblErrPasswordBaru, lblErrKonfirmasi};
        for (javax.swing.JLabel l : semua) Gaya.error(l, null);
        try {
            Pelanggan baru = auth.registrasi(txtNama.getText(), txtUsernameBaru.getText(), txtTelepon.getText(),
                    txtAlamat.getText(), new String(txtPasswordBaru.getPassword()), new String(txtKonfirmasi.getPassword()));
            KonfirmasiDialog.info(this, "Akun berhasil dibuat", "Silakan login dengan username " + baru.getUsername() + ".");
            txtUsername.setText(baru.getUsername());
            txtPassword.setText("");
            for (javax.swing.text.JTextComponent t : new javax.swing.text.JTextComponent[]{txtNama, txtUsernameBaru,
                txtTelepon, txtAlamat, txtPasswordBaru, txtKonfirmasi}) t.setText("");
            tampilTab(true);
            txtPassword.requestFocusInWindow();
        } catch (ValidasiException e) {
            Gaya.error(lblErrNama, e.getPesan("nama"));
            Gaya.error(lblErrUsernameBaru, e.getPesan("username"));
            Gaya.error(lblErrTelepon, e.getPesan("telepon"));
            Gaya.error(lblErrAlamat, e.getPesan("alamat"));
            Gaya.error(lblErrPasswordBaru, e.getPesan("password"));
            Gaya.error(lblErrKonfirmasi, e.getPesan("konfirmasi"));
        } catch (RuntimeException e) {
            KonfirmasiDialog.gagal(this, "Akun gagal disimpan. " + e.getMessage());
        }
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlLatar = new javax.swing.JPanel();
        pnlKiri = new javax.swing.JPanel();
        pnlSampul = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        lblSlogan = new javax.swing.JLabel();
        lblDeskripsi = new javax.swing.JLabel();
        lblSampul = new javax.swing.JLabel();
        pnlKanan = new javax.swing.JPanel();
        pnlKartu = new javax.swing.JPanel();
        pnlTab = new javax.swing.JPanel();
        btnTabLogin = new javax.swing.JButton();
        btnTabDaftar = new javax.swing.JButton();
        pnlIsi = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        lblMasuk = new javax.swing.JLabel();
        lblMasukSub = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblErrUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblErrPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblBelumPunya = new javax.swing.JLabel();
        pnlIsiLogin = new javax.swing.JPanel();
        pnlDaftar = new javax.swing.JPanel();
        lblBuatAkun = new javax.swing.JLabel();
        lblBuatAkunSub = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblErrNama = new javax.swing.JLabel();
        lblUsernameBaru = new javax.swing.JLabel();
        txtUsernameBaru = new javax.swing.JTextField();
        lblErrUsernameBaru = new javax.swing.JLabel();
        lblTelepon = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        lblErrTelepon = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        lblErrAlamat = new javax.swing.JLabel();
        lblPasswordBaru = new javax.swing.JLabel();
        txtPasswordBaru = new javax.swing.JPasswordField();
        lblErrPasswordBaru = new javax.swing.JLabel();
        lblKonfirmasi = new javax.swing.JLabel();
        txtKonfirmasi = new javax.swing.JPasswordField();
        lblErrKonfirmasi = new javax.swing.JLabel();
        btnDaftar = new javax.swing.JButton();
        pnlIsiDaftar = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HAULECTRA - Login");
        setMinimumSize(new java.awt.Dimension(1000, 660));

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlLatar.setPreferredSize(new java.awt.Dimension(1180, 720));
        pnlLatar.setLayout(new java.awt.GridLayout(1, 2, 0, 0));

        pnlKiri.setBackground(new java.awt.Color(255, 255, 255));
        pnlKiri.setBorder(javax.swing.BorderFactory.createEmptyBorder(32, 48, 32, 48));
        pnlKiri.setLayout(new java.awt.GridBagLayout());

        pnlSampul.setBackground(new java.awt.Color(255, 255, 255));
        pnlSampul.setOpaque(false);
        pnlSampul.setLayout(new java.awt.GridBagLayout());

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(16, 24, 40));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/logo_besar.png"))); // NOI18N
        lblLogo.setText("HAULECTRA");
        lblLogo.setIconTextGap(12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        pnlSampul.add(lblLogo, gridBagConstraints);

        lblTagline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(31, 94, 219));
        lblTagline.setText("TOKO ELEKTRONIK TERPERCAYA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlSampul.add(lblTagline, gridBagConstraints);

        lblSlogan.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        lblSlogan.setForeground(new java.awt.Color(16, 24, 40));
        lblSlogan.setText("<html>Teknologi pilihan.<br>Belanja lebih nyaman.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        pnlSampul.add(lblSlogan, gridBagConstraints);

        lblDeskripsi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDeskripsi.setForeground(new java.awt.Color(102, 112, 133));
        lblDeskripsi.setText("<html><div width=380>Laptop, smartphone, audio, dan aksesori dengan pengiriman terlacak sampai ke rumah Anda.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 28, 0);
        pnlSampul.add(lblDeskripsi, gridBagConstraints);

        lblSampul.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSampul.setForeground(new java.awt.Color(16, 24, 40));
        lblSampul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/sampul_login.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSampul.add(lblSampul, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlKiri.add(pnlSampul, gridBagConstraints);

        pnlLatar.add(pnlKiri);

        pnlKanan.setBackground(new java.awt.Color(245, 247, 251));
        pnlKanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(32, 48, 32, 48));
        pnlKanan.setLayout(new java.awt.GridBagLayout());

        pnlKartu.setBackground(new java.awt.Color(255, 255, 255));
        pnlKartu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(28, 32, 28, 32)));
        pnlKartu.setPreferredSize(new java.awt.Dimension(440, 560));
        pnlKartu.setLayout(new java.awt.GridBagLayout());

        pnlTab.setBackground(new java.awt.Color(255, 255, 255));
        pnlTab.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        pnlTab.setLayout(new java.awt.GridLayout(1, 2, 0, 0));

        btnTabLogin.setBackground(new java.awt.Color(31, 94, 219));
        btnTabLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTabLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnTabLogin.setText("Login");
        btnTabLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnTabLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTabLogin.setFocusPainted(false);
        btnTabLogin.setPreferredSize(new java.awt.Dimension(120, 40));
        btnTabLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabLoginActionPerformed(evt);
            }
        });
        pnlTab.add(btnTabLogin);

        btnTabDaftar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTabDaftar.setForeground(new java.awt.Color(16, 24, 40));
        btnTabDaftar.setText("Daftar");
        btnTabDaftar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnTabDaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTabDaftar.setFocusPainted(false);
        btnTabDaftar.setPreferredSize(new java.awt.Dimension(120, 40));
        btnTabDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabDaftarActionPerformed(evt);
            }
        });
        pnlTab.add(btnTabDaftar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 24, 0);
        pnlKartu.add(pnlTab, gridBagConstraints);

        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.CardLayout());

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));
        pnlLogin.setOpaque(false);
        pnlLogin.setLayout(new java.awt.GridBagLayout());

        lblMasuk.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblMasuk.setForeground(new java.awt.Color(16, 24, 40));
        lblMasuk.setText("Selamat datang kembali");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlLogin.add(lblMasuk, gridBagConstraints);

        lblMasukSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMasukSub.setForeground(new java.awt.Color(102, 112, 133));
        lblMasukSub.setText("Masuk untuk melanjutkan belanja Anda.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        pnlLogin.add(lblMasukSub, gridBagConstraints);

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(52, 64, 84));
        lblUsername.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlLogin.add(lblUsername, gridBagConstraints);

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(16, 24, 40));
        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtUsername.setPreferredSize(new java.awt.Dimension(340, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlLogin.add(txtUsername, gridBagConstraints);

        lblErrUsername.setForeground(new java.awt.Color(217, 45, 32));
        lblErrUsername.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrUsername.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 6, 0);
        pnlLogin.add(lblErrUsername, gridBagConstraints);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(52, 64, 84));
        lblPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlLogin.add(lblPassword, gridBagConstraints);

        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(16, 24, 40));
        txtPassword.setPreferredSize(new java.awt.Dimension(340, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlLogin.add(txtPassword, gridBagConstraints);

        lblErrPassword.setForeground(new java.awt.Color(217, 45, 32));
        lblErrPassword.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrPassword.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 6, 0);
        pnlLogin.add(lblErrPassword, gridBagConstraints);

        btnLogin.setBackground(new java.awt.Color(31, 94, 219));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new java.awt.Dimension(120, 42));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlLogin.add(btnLogin, gridBagConstraints);

        lblBelumPunya.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBelumPunya.setForeground(new java.awt.Color(102, 112, 133));
        lblBelumPunya.setText("Belum memiliki akun? Pilih tab Daftar.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlLogin.add(lblBelumPunya, gridBagConstraints);

        pnlIsiLogin.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsiLogin.setOpaque(false);
        pnlIsiLogin.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlLogin.add(pnlIsiLogin, gridBagConstraints);

        pnlIsi.add(pnlLogin, "login");

        pnlDaftar.setBackground(new java.awt.Color(255, 255, 255));
        pnlDaftar.setOpaque(false);
        pnlDaftar.setLayout(new java.awt.GridBagLayout());

        lblBuatAkun.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblBuatAkun.setForeground(new java.awt.Color(16, 24, 40));
        lblBuatAkun.setText("Buat akun pelanggan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlDaftar.add(lblBuatAkun, gridBagConstraints);

        lblBuatAkunSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBuatAkunSub.setForeground(new java.awt.Color(102, 112, 133));
        lblBuatAkunSub.setText("Isi data diri untuk mulai berbelanja.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlDaftar.add(lblBuatAkunSub, gridBagConstraints);

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNama.setForeground(new java.awt.Color(52, 64, 84));
        lblNama.setText("Nama lengkap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlDaftar.add(lblNama, gridBagConstraints);

        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(16, 24, 40));
        txtNama.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDaftar.add(txtNama, gridBagConstraints);

        lblErrNama.setForeground(new java.awt.Color(217, 45, 32));
        lblErrNama.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 4, 0);
        pnlDaftar.add(lblErrNama, gridBagConstraints);

        lblUsernameBaru.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsernameBaru.setForeground(new java.awt.Color(52, 64, 84));
        lblUsernameBaru.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        pnlDaftar.add(lblUsernameBaru, gridBagConstraints);

        txtUsernameBaru.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtUsernameBaru.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsernameBaru.setForeground(new java.awt.Color(16, 24, 40));
        txtUsernameBaru.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 16, 0, 0);
        pnlDaftar.add(txtUsernameBaru, gridBagConstraints);

        lblErrUsernameBaru.setForeground(new java.awt.Color(217, 45, 32));
        lblErrUsernameBaru.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrUsernameBaru.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 16, 4, 0);
        pnlDaftar.add(lblErrUsernameBaru, gridBagConstraints);

        lblTelepon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelepon.setForeground(new java.awt.Color(52, 64, 84));
        lblTelepon.setText("No. telepon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlDaftar.add(lblTelepon, gridBagConstraints);

        txtTelepon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelepon.setForeground(new java.awt.Color(16, 24, 40));
        txtTelepon.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDaftar.add(txtTelepon, gridBagConstraints);

        lblErrTelepon.setForeground(new java.awt.Color(217, 45, 32));
        lblErrTelepon.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrTelepon.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 4, 0);
        pnlDaftar.add(lblErrTelepon, gridBagConstraints);

        lblAlamat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(52, 64, 84));
        lblAlamat.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        pnlDaftar.add(lblAlamat, gridBagConstraints);

        txtAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(16, 24, 40));
        txtAlamat.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 16, 0, 0);
        pnlDaftar.add(txtAlamat, gridBagConstraints);

        lblErrAlamat.setForeground(new java.awt.Color(217, 45, 32));
        lblErrAlamat.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrAlamat.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 16, 4, 0);
        pnlDaftar.add(lblErrAlamat, gridBagConstraints);

        lblPasswordBaru.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPasswordBaru.setForeground(new java.awt.Color(52, 64, 84));
        lblPasswordBaru.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlDaftar.add(lblPasswordBaru, gridBagConstraints);

        txtPasswordBaru.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtPasswordBaru.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPasswordBaru.setForeground(new java.awt.Color(16, 24, 40));
        txtPasswordBaru.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDaftar.add(txtPasswordBaru, gridBagConstraints);

        lblErrPasswordBaru.setForeground(new java.awt.Color(217, 45, 32));
        lblErrPasswordBaru.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrPasswordBaru.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 4, 0);
        pnlDaftar.add(lblErrPasswordBaru, gridBagConstraints);

        lblKonfirmasi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKonfirmasi.setForeground(new java.awt.Color(52, 64, 84));
        lblKonfirmasi.setText("Ulangi password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        pnlDaftar.add(lblKonfirmasi, gridBagConstraints);

        txtKonfirmasi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtKonfirmasi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtKonfirmasi.setForeground(new java.awt.Color(16, 24, 40));
        txtKonfirmasi.setPreferredSize(new java.awt.Dimension(170, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 16, 0, 0);
        pnlDaftar.add(txtKonfirmasi, gridBagConstraints);

        lblErrKonfirmasi.setForeground(new java.awt.Color(217, 45, 32));
        lblErrKonfirmasi.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrKonfirmasi.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 16, 4, 0);
        pnlDaftar.add(lblErrKonfirmasi, gridBagConstraints);

        btnDaftar.setBackground(new java.awt.Color(31, 94, 219));
        btnDaftar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDaftar.setForeground(new java.awt.Color(255, 255, 255));
        btnDaftar.setText("Daftar");
        btnDaftar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnDaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDaftar.setFocusPainted(false);
        btnDaftar.setPreferredSize(new java.awt.Dimension(120, 42));
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlDaftar.add(btnDaftar, gridBagConstraints);

        pnlIsiDaftar.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsiDaftar.setOpaque(false);
        pnlIsiDaftar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlDaftar.add(pnlIsiDaftar, gridBagConstraints);

        pnlIsi.add(pnlDaftar, "daftar");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlKartu.add(pnlIsi, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlKanan.add(pnlKartu, gridBagConstraints);

        pnlLatar.add(pnlKanan);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTabLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabLoginActionPerformed
        tampilTab(true);
    }//GEN-LAST:event_btnTabLoginActionPerformed

    private void btnTabDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabDaftarActionPerformed
        tampilTab(false);
    }//GEN-LAST:event_btnTabDaftarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        daftar();
    }//GEN-LAST:event_btnDaftarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnTabDaftar;
    private javax.swing.JButton btnTabLogin;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBelumPunya;
    private javax.swing.JLabel lblBuatAkun;
    private javax.swing.JLabel lblBuatAkunSub;
    private javax.swing.JLabel lblDeskripsi;
    private javax.swing.JLabel lblErrAlamat;
    private javax.swing.JLabel lblErrKonfirmasi;
    private javax.swing.JLabel lblErrNama;
    private javax.swing.JLabel lblErrPassword;
    private javax.swing.JLabel lblErrPasswordBaru;
    private javax.swing.JLabel lblErrTelepon;
    private javax.swing.JLabel lblErrUsername;
    private javax.swing.JLabel lblErrUsernameBaru;
    private javax.swing.JLabel lblKonfirmasi;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMasuk;
    private javax.swing.JLabel lblMasukSub;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordBaru;
    private javax.swing.JLabel lblSampul;
    private javax.swing.JLabel lblSlogan;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblTelepon;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameBaru;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiDaftar;
    private javax.swing.JPanel pnlIsiLogin;
    private javax.swing.JPanel pnlKanan;
    private javax.swing.JPanel pnlKartu;
    private javax.swing.JPanel pnlKiri;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlSampul;
    private javax.swing.JPanel pnlTab;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JPasswordField txtKonfirmasi;
    private javax.swing.JTextField txtNama;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordBaru;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsernameBaru;
    // End of variables declaration//GEN-END:variables
}
