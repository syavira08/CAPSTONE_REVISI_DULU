package view;

import controller.PelangganController;
import controller.ValidasiException;
import java.awt.CardLayout;
import model.Pelanggan;

/**
 * Flowchart Profil:
 *  ambil data identitas (ID pengguna login) -> tampilkan nama, username, alamat, telepon
 *  -> ingin mengubah? -> input -> valid? -> simpan ke database -> "Data berhasil diubah".
 * Dua mode dalam satu form (CardLayout): "lihat" profil dan "edit" profil.
 */
public class ProfilPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final PelangganController controller = new PelangganController();

    public ProfilPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        UI.input(txtEdNama, "Nama lengkap");
        UI.input(txtEdUser, "Username");
        UI.input(txtEdTelp, "08xxxxxxxxxx");
        UI.area(txtEdAlamat, scrEdAlamat);
        UI.primer(btnEdit);
        UI.primer(btnSimpan);
        UI.sekunder(btnBatal);
        UI.kartu(pnlKartuProfil);
        UI.kartu(pnlData);
        UI.kartu(pnlFormEdit);
        UI.kartu(pnlCatatan);
        UI.ikon(lblAvatar, "pengguna", 56, UI.BIRU);
        UI.garis(pnlGaris1, pnlGaris2, pnlGaris3, pnlGaris4);
        bersihkanError();
        UI.sembunyi(pnlPesan);
        UI.lebarTetap(pnlKiriWrap, 260);
        UI.lebarTetap(pnlCatatanWrap, 330);
        UI.tema(this);
    }

    /** Mode lihat profil. */
    @Override
    public void muat() {
        lblJudul.setText("Profil saya");
        lblSubjudul.setText("Informasi akun dan alamat pengiriman Anda.");
        Pelanggan p = controller.profilSaya();
        lblNamaProfil.setText(p.getNama());
        lblUsernameProfil.setText("@" + p.getUsername());
        UI.badge(lblBadgePeran, "Pelanggan");
        lblBergabung.setText("Bergabung sejak " + UI.tanggal(p.getTanggalBergabung()));
        lblNamaV.setText(p.getNama());
        lblUserV.setText(p.getUsername());
        lblTelpV.setText(UI.telepon(p.getNoTelepon()));
        lblAlamatV.setText(p.getAlamat());
        ((CardLayout) pnlMode.getLayout()).show(pnlMode, "lihat");
    }

    /** Mode edit profil. */
    private void modeEdit() {
        Pelanggan p = controller.profilSaya();
        lblJudul.setText("Edit profil");
        lblSubjudul.setText("Perbarui data diri dan alamat pengiriman Anda.");
        txtEdNama.setText(p.getNama());
        txtEdUser.setText(p.getUsername());
        txtEdTelp.setText(p.getNoTelepon());
        txtEdAlamat.setText(p.getAlamat());
        bersihkanError();
        ((CardLayout) pnlMode.getLayout()).show(pnlMode, "edit");
    }

    private void bersihkanError() {
        tampilkanError(null);
    }

    private void tampilkanError(ValidasiException e) {
        UI.tampilkanError(e, "nama", txtEdNama, errEdNama, "username", txtEdUser, errEdUser,
                "telepon", txtEdTelp, errEdTelp, "alamat", scrEdAlamat, errEdAlamat);
    }

    private void simpan() {
        try {
            controller.ubahProfil(txtEdNama.getText(), txtEdUser.getText(), txtEdTelp.getText(), txtEdAlamat.getText());
            jendela.buka(this);          // kembali ke mode lihat
            sukses("Data berhasil diubah");
        } catch (ValidasiException e) {
            galat(e.getMessage());
            tampilkanError(e);
        } catch (RuntimeException e) {
            galat(e.getMessage());
        }
    }

    // ---------- pesan di dalam halaman (tanpa pop-up) ----------
    private void sukses(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.SUKSES, teks); }

    private void galat(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.GALAT, teks); }

    private void info(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.INFO, teks); }

    @Override
    public void bersihkanPesan() { UI.sembunyi(pnlPesan); }

    /** Jalankan aksi; jika gagal, tampilkan pesannya. Mengembalikan true jika berhasil. */
    private boolean coba(Runnable aksi) {
        try {
            aksi.run();
            return true;
        } catch (RuntimeException e) {
            galat(e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlAtas = new javax.swing.JPanel();
        pnlKepala = new javax.swing.JPanel();
        pnlJudul = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSubjudul = new javax.swing.JLabel();
        pnlPesan = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlKonten = new javax.swing.JPanel();
        pnlMode = new javax.swing.JPanel();
        pnlLihat = new javax.swing.JPanel();
        pnlKiriWrap = new javax.swing.JPanel();
        pnlKartuProfil = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        lblNamaProfil = new javax.swing.JLabel();
        lblUsernameProfil = new javax.swing.JLabel();
        pnlBadgeWrap = new javax.swing.JPanel();
        lblBadgePeran = new javax.swing.JLabel();
        lblBergabung = new javax.swing.JLabel();
        pnlKananLihat = new javax.swing.JPanel();
        pnlData = new javax.swing.JPanel();
        lblDataJudul = new javax.swing.JLabel();
        pnlBarisNama = new javax.swing.JPanel();
        lblNamaL = new javax.swing.JLabel();
        lblNamaV = new javax.swing.JLabel();
        pnlGaris1 = new javax.swing.JPanel();
        pnlBarisUser = new javax.swing.JPanel();
        lblUserL = new javax.swing.JLabel();
        lblUserV = new javax.swing.JLabel();
        pnlGaris2 = new javax.swing.JPanel();
        pnlBarisTelp = new javax.swing.JPanel();
        lblTelpL = new javax.swing.JLabel();
        lblTelpV = new javax.swing.JLabel();
        pnlGaris3 = new javax.swing.JPanel();
        pnlBarisAlamat = new javax.swing.JPanel();
        lblAlamatL = new javax.swing.JLabel();
        lblAlamatV = new javax.swing.JLabel();
        pnlGaris4 = new javax.swing.JPanel();
        pnlEditWrap = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        lblCatatanAlamat = new javax.swing.JLabel();
        pnlEdit = new javax.swing.JPanel();
        pnlFormEdit = new javax.swing.JPanel();
        lblInfoPribadi = new javax.swing.JLabel();
        pnlEdNamaUser = new javax.swing.JPanel();
        pnlEdNama = new javax.swing.JPanel();
        lblEdNama = new javax.swing.JLabel();
        txtEdNama = new javax.swing.JTextField();
        errEdNama = new javax.swing.JLabel();
        pnlEdUser = new javax.swing.JPanel();
        lblEdUser = new javax.swing.JLabel();
        txtEdUser = new javax.swing.JTextField();
        errEdUser = new javax.swing.JLabel();
        pnlEdTelp = new javax.swing.JPanel();
        lblEdTelp = new javax.swing.JLabel();
        txtEdTelp = new javax.swing.JTextField();
        errEdTelp = new javax.swing.JLabel();
        pnlEdAlamat = new javax.swing.JPanel();
        lblEdAlamat = new javax.swing.JLabel();
        scrEdAlamat = new javax.swing.JScrollPane();
        txtEdAlamat = new javax.swing.JTextArea();
        errEdAlamat = new javax.swing.JLabel();
        lblWajib = new javax.swing.JLabel();
        pnlTombolEdit = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        pnlCatatanWrap = new javax.swing.JPanel();
        pnlCatatan = new javax.swing.JPanel();
        lblPastikanJudul = new javax.swing.JLabel();
        lblPastikanIsi = new javax.swing.JLabel();

        setBackground(new java.awt.Color(243, 246, 250));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 32, 32, 32));
        setLayout(new java.awt.BorderLayout());

        pnlAtas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnlAtas.setOpaque(false);
        pnlAtas.setLayout(new java.awt.BorderLayout());
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.BorderLayout(12, 0));
        pnlJudul.setOpaque(false);
        pnlJudul.setLayout(new java.awt.BorderLayout(0, 6));
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Profil saya");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Informasi akun dan alamat pengiriman Anda.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);
        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);
        pnlAtas.add(pnlKepala, java.awt.BorderLayout.NORTH);
        pnlPesan.setBackground(new java.awt.Color(234, 246, 236));
        pnlPesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesan.setLayout(new java.awt.BorderLayout());
        lblPesan.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPesan.setForeground(new java.awt.Color(46, 125, 50));
        lblPesan.setText("Pesan");
        pnlPesan.add(lblPesan, java.awt.BorderLayout.CENTER);
        pnlAtas.add(pnlPesan, java.awt.BorderLayout.CENTER);
        add(pnlAtas, java.awt.BorderLayout.NORTH);

        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.BorderLayout());
        pnlKonten.setOpaque(false);
        pnlKonten.setLayout(new java.awt.BorderLayout());
        pnlMode.setOpaque(false);
        pnlMode.setLayout(new java.awt.CardLayout());
        pnlLihat.setOpaque(false);
        pnlLihat.setLayout(new java.awt.BorderLayout(20, 0));
        pnlKiriWrap.setPreferredSize(new java.awt.Dimension(260, 10));
        pnlKiriWrap.setOpaque(false);
        pnlKiriWrap.setLayout(new java.awt.BorderLayout());
        pnlKartuProfil.setBackground(new java.awt.Color(255, 255, 255));
        pnlKartuProfil.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlKartuProfil.setLayout(new java.awt.GridBagLayout());
        lblAvatar.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblAvatar.setForeground(new java.awt.Color(23, 43, 77));
        lblAvatar.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlKartuProfil.add(lblAvatar, gridBagConstraints);
        lblNamaProfil.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblNamaProfil.setForeground(new java.awt.Color(23, 43, 77));
        lblNamaProfil.setText("Alif Anugrah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKartuProfil.add(lblNamaProfil, gridBagConstraints);
        lblUsernameProfil.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblUsernameProfil.setForeground(new java.awt.Color(94, 108, 132));
        lblUsernameProfil.setText("@aliframadhan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKartuProfil.add(lblUsernameProfil, gridBagConstraints);
        pnlBadgeWrap.setOpaque(false);
        pnlBadgeWrap.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        lblBadgePeran.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblBadgePeran.setForeground(new java.awt.Color(33, 97, 213));
        lblBadgePeran.setText("Pelanggan");
        pnlBadgeWrap.add(lblBadgePeran);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKartuProfil.add(pnlBadgeWrap, gridBagConstraints);
        lblBergabung.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblBergabung.setForeground(new java.awt.Color(94, 108, 132));
        lblBergabung.setText("Bergabung sejak 12 Mar 2026");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKartuProfil.add(lblBergabung, gridBagConstraints);
        pnlKiriWrap.add(pnlKartuProfil, java.awt.BorderLayout.NORTH);
        pnlLihat.add(pnlKiriWrap, java.awt.BorderLayout.WEST);
        pnlKananLihat.setOpaque(false);
        pnlKananLihat.setLayout(new java.awt.GridBagLayout());
        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlData.setLayout(new java.awt.GridBagLayout());
        lblDataJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblDataJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblDataJudul.setText("Data diri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlData.add(lblDataJudul, gridBagConstraints);
        pnlBarisNama.setOpaque(false);
        pnlBarisNama.setLayout(new java.awt.BorderLayout());
        lblNamaL.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblNamaL.setForeground(new java.awt.Color(94, 108, 132));
        lblNamaL.setText("Nama lengkap");
        lblNamaL.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlBarisNama.add(lblNamaL, java.awt.BorderLayout.WEST);
        lblNamaV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblNamaV.setForeground(new java.awt.Color(23, 43, 77));
        lblNamaV.setText("Alif Anugrah");
        pnlBarisNama.add(lblNamaV, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlBarisNama, gridBagConstraints);
        pnlGaris1.setBackground(new java.awt.Color(220, 227, 237));
        pnlGaris1.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGaris1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlGaris1, gridBagConstraints);
        pnlBarisUser.setOpaque(false);
        pnlBarisUser.setLayout(new java.awt.BorderLayout());
        lblUserL.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblUserL.setForeground(new java.awt.Color(94, 108, 132));
        lblUserL.setText("Username");
        lblUserL.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlBarisUser.add(lblUserL, java.awt.BorderLayout.WEST);
        lblUserV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblUserV.setForeground(new java.awt.Color(23, 43, 77));
        lblUserV.setText("aliframadhan");
        pnlBarisUser.add(lblUserV, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlBarisUser, gridBagConstraints);
        pnlGaris2.setBackground(new java.awt.Color(220, 227, 237));
        pnlGaris2.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGaris2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlGaris2, gridBagConstraints);
        pnlBarisTelp.setOpaque(false);
        pnlBarisTelp.setLayout(new java.awt.BorderLayout());
        lblTelpL.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblTelpL.setForeground(new java.awt.Color(94, 108, 132));
        lblTelpL.setText("No. telepon");
        lblTelpL.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlBarisTelp.add(lblTelpL, java.awt.BorderLayout.WEST);
        lblTelpV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTelpV.setForeground(new java.awt.Color(23, 43, 77));
        lblTelpV.setText("0812 3456 7801");
        pnlBarisTelp.add(lblTelpV, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlBarisTelp, gridBagConstraints);
        pnlGaris3.setBackground(new java.awt.Color(220, 227, 237));
        pnlGaris3.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGaris3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlGaris3, gridBagConstraints);
        pnlBarisAlamat.setOpaque(false);
        pnlBarisAlamat.setLayout(new java.awt.BorderLayout());
        lblAlamatL.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblAlamatL.setForeground(new java.awt.Color(94, 108, 132));
        lblAlamatL.setText("Alamat");
        lblAlamatL.setPreferredSize(new java.awt.Dimension(150, 20));
        pnlBarisAlamat.add(lblAlamatL, java.awt.BorderLayout.WEST);
        lblAlamatV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblAlamatV.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatV.setText("Jl. Pangeran Antasari No. 10");
        pnlBarisAlamat.add(lblAlamatV, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlBarisAlamat, gridBagConstraints);
        pnlGaris4.setBackground(new java.awt.Color(220, 227, 237));
        pnlGaris4.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGaris4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlGaris4, gridBagConstraints);
        pnlEditWrap.setOpaque(false);
        pnlEditWrap.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));
        btnEdit.setBackground(new java.awt.Color(33, 97, 213));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Profil");
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlEditWrap.add(btnEdit);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlData.add(pnlEditWrap, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKananLihat.add(pnlData, gridBagConstraints);
        lblCatatanAlamat.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblCatatanAlamat.setForeground(new java.awt.Color(94, 108, 132));
        lblCatatanAlamat.setText("Alamat dan nomor telepon digunakan untuk pengiriman pesanan Anda.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlKananLihat.add(lblCatatanAlamat, gridBagConstraints);
        pnlLihat.add(pnlKananLihat, java.awt.BorderLayout.CENTER);
        pnlMode.add(pnlLihat, "lihat");
        pnlEdit.setOpaque(false);
        pnlEdit.setLayout(new java.awt.BorderLayout(20, 0));
        pnlFormEdit.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormEdit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlFormEdit.setLayout(new java.awt.GridBagLayout());
        lblInfoPribadi.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblInfoPribadi.setForeground(new java.awt.Color(23, 43, 77));
        lblInfoPribadi.setText("Informasi pribadi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormEdit.add(lblInfoPribadi, gridBagConstraints);
        pnlEdNamaUser.setOpaque(false);
        pnlEdNamaUser.setLayout(new java.awt.GridLayout(1, 2, 14, 0));
        pnlEdNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlEdNama.setOpaque(false);
        pnlEdNama.setLayout(new java.awt.GridBagLayout());
        lblEdNama.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEdNama.setForeground(new java.awt.Color(23, 43, 77));
        lblEdNama.setText("Nama lengkap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEdNama.add(lblEdNama, gridBagConstraints);
        txtEdNama.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtEdNama.setForeground(new java.awt.Color(23, 43, 77));
        txtEdNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtEdNama.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlEdNama.add(txtEdNama, gridBagConstraints);
        errEdNama.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errEdNama.setForeground(new java.awt.Color(192, 52, 63));
        errEdNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEdNama.add(errEdNama, gridBagConstraints);
        pnlEdNamaUser.add(pnlEdNama);
        pnlEdUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlEdUser.setOpaque(false);
        pnlEdUser.setLayout(new java.awt.GridBagLayout());
        lblEdUser.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEdUser.setForeground(new java.awt.Color(23, 43, 77));
        lblEdUser.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEdUser.add(lblEdUser, gridBagConstraints);
        txtEdUser.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtEdUser.setForeground(new java.awt.Color(23, 43, 77));
        txtEdUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtEdUser.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlEdUser.add(txtEdUser, gridBagConstraints);
        errEdUser.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errEdUser.setForeground(new java.awt.Color(192, 52, 63));
        errEdUser.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEdUser.add(errEdUser, gridBagConstraints);
        pnlEdNamaUser.add(pnlEdUser);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlFormEdit.add(pnlEdNamaUser, gridBagConstraints);
        pnlEdTelp.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlEdTelp.setOpaque(false);
        pnlEdTelp.setLayout(new java.awt.GridBagLayout());
        lblEdTelp.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEdTelp.setForeground(new java.awt.Color(23, 43, 77));
        lblEdTelp.setText("No. telepon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEdTelp.add(lblEdTelp, gridBagConstraints);
        txtEdTelp.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtEdTelp.setForeground(new java.awt.Color(23, 43, 77));
        txtEdTelp.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtEdTelp.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlEdTelp.add(txtEdTelp, gridBagConstraints);
        errEdTelp.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errEdTelp.setForeground(new java.awt.Color(192, 52, 63));
        errEdTelp.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEdTelp.add(errEdTelp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlFormEdit.add(pnlEdTelp, gridBagConstraints);
        pnlEdAlamat.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlEdAlamat.setOpaque(false);
        pnlEdAlamat.setLayout(new java.awt.GridBagLayout());
        lblEdAlamat.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEdAlamat.setForeground(new java.awt.Color(23, 43, 77));
        lblEdAlamat.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEdAlamat.add(lblEdAlamat, gridBagConstraints);
        scrEdAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        scrEdAlamat.setPreferredSize(new java.awt.Dimension(160, 70));
        txtEdAlamat.setColumns(20);
        txtEdAlamat.setRows(3);
        txtEdAlamat.setLineWrap(true);
        txtEdAlamat.setWrapStyleWord(true);
        txtEdAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtEdAlamat.setForeground(new java.awt.Color(23, 43, 77));
        scrEdAlamat.setViewportView(txtEdAlamat);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlEdAlamat.add(scrEdAlamat, gridBagConstraints);
        errEdAlamat.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errEdAlamat.setForeground(new java.awt.Color(192, 52, 63));
        errEdAlamat.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEdAlamat.add(errEdAlamat, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlFormEdit.add(pnlEdAlamat, gridBagConstraints);
        lblWajib.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblWajib.setForeground(new java.awt.Color(94, 108, 132));
        lblWajib.setText("Semua field wajib diisi. Nomor telepon berisi 10\u201313 digit.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlFormEdit.add(lblWajib, gridBagConstraints);
        pnlTombolEdit.setOpaque(false);
        pnlTombolEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnBatal.setBackground(new java.awt.Color(255, 255, 255));
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnBatal.setForeground(new java.awt.Color(23, 43, 77));
        btnBatal.setText("Batal");
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlTombolEdit.add(btnBatal);
        btnSimpan.setBackground(new java.awt.Color(33, 97, 213));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan Perubahan");
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlTombolEdit.add(btnSimpan);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlFormEdit.add(pnlTombolEdit, gridBagConstraints);
        pnlEdit.add(pnlFormEdit, java.awt.BorderLayout.CENTER);
        pnlCatatanWrap.setPreferredSize(new java.awt.Dimension(330, 10));
        pnlCatatanWrap.setOpaque(false);
        pnlCatatanWrap.setLayout(new java.awt.BorderLayout());
        pnlCatatan.setBackground(new java.awt.Color(255, 255, 255));
        pnlCatatan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlCatatan.setLayout(new java.awt.GridBagLayout());
        lblPastikanJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblPastikanJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblPastikanJudul.setText("Pastikan data sudah benar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlCatatan.add(lblPastikanJudul, gridBagConstraints);
        lblPastikanIsi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPastikanIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblPastikanIsi.setText("<html><div width=250>Gunakan nama penerima dan nomor telepon yang aktif agar kurir dapat menghubungi Anda.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlCatatan.add(lblPastikanIsi, gridBagConstraints);
        pnlCatatanWrap.add(pnlCatatan, java.awt.BorderLayout.NORTH);
        pnlEdit.add(pnlCatatanWrap, java.awt.BorderLayout.EAST);
        pnlMode.add(pnlEdit, "edit");
        pnlKonten.add(pnlMode, java.awt.BorderLayout.CENTER);
        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);
        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        coba(this::modeEdit);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        muat();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel errEdAlamat;
    private javax.swing.JLabel errEdNama;
    private javax.swing.JLabel errEdTelp;
    private javax.swing.JLabel errEdUser;
    private javax.swing.JLabel lblAlamatL;
    private javax.swing.JLabel lblAlamatV;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBadgePeran;
    private javax.swing.JLabel lblBergabung;
    private javax.swing.JLabel lblCatatanAlamat;
    private javax.swing.JLabel lblDataJudul;
    private javax.swing.JLabel lblEdAlamat;
    private javax.swing.JLabel lblEdNama;
    private javax.swing.JLabel lblEdTelp;
    private javax.swing.JLabel lblEdUser;
    private javax.swing.JLabel lblInfoPribadi;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblNamaL;
    private javax.swing.JLabel lblNamaProfil;
    private javax.swing.JLabel lblNamaV;
    private javax.swing.JLabel lblPastikanIsi;
    private javax.swing.JLabel lblPastikanJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JLabel lblTelpL;
    private javax.swing.JLabel lblTelpV;
    private javax.swing.JLabel lblUserL;
    private javax.swing.JLabel lblUsernameProfil;
    private javax.swing.JLabel lblUserV;
    private javax.swing.JLabel lblWajib;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlBadgeWrap;
    private javax.swing.JPanel pnlBarisAlamat;
    private javax.swing.JPanel pnlBarisNama;
    private javax.swing.JPanel pnlBarisTelp;
    private javax.swing.JPanel pnlBarisUser;
    private javax.swing.JPanel pnlCatatan;
    private javax.swing.JPanel pnlCatatanWrap;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlEdAlamat;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlEditWrap;
    private javax.swing.JPanel pnlEdNama;
    private javax.swing.JPanel pnlEdNamaUser;
    private javax.swing.JPanel pnlEdTelp;
    private javax.swing.JPanel pnlEdUser;
    private javax.swing.JPanel pnlFormEdit;
    private javax.swing.JPanel pnlGaris1;
    private javax.swing.JPanel pnlGaris2;
    private javax.swing.JPanel pnlGaris3;
    private javax.swing.JPanel pnlGaris4;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKananLihat;
    private javax.swing.JPanel pnlKartuProfil;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKiriWrap;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlLihat;
    private javax.swing.JPanel pnlMode;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlTombolEdit;
    private javax.swing.JScrollPane scrEdAlamat;
    private javax.swing.JTextArea txtEdAlamat;
    private javax.swing.JTextField txtEdNama;
    private javax.swing.JTextField txtEdTelp;
    private javax.swing.JTextField txtEdUser;
    // End of variables declaration//GEN-END:variables
}
