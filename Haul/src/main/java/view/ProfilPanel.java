package view;

import controller.PelangganController;
import controller.ValidasiException;
import model.Pelanggan;

/** Profil pelanggan: lihat dan ubah data diri (flowchart Profil). */
public class ProfilPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView induk;
    private final PelangganController controller = new PelangganController();

    public ProfilPanel(PelangganView induk) {
        this.induk = induk;
        initComponents();
    }

    @Override
    public void muat() {
        Pelanggan p = controller.profilSaya();
        lblKode.setText(p.getKode() + " \u2022 " + p.getUsername());
        txtNama.setText(p.getNama());
        txtUsername.setText(p.getUsername());
        txtTelepon.setText(p.getNoTelepon());
        txtAlamat.setText(p.getAlamat());
        txtBergabung.setText(p.getTanggalBergabung() == null ? "-" : Gaya.tanggal(p.getTanggalBergabung().toLocalDate()));
        modeUbah(false);
    }

    /** Mode lihat (input terkunci) atau mode ubah. */
    private void modeUbah(boolean ubah) {
        for (javax.swing.JTextField t : new javax.swing.JTextField[]{txtNama, txtUsername, txtTelepon, txtAlamat}) {
            t.setEditable(ubah);
            t.setBackground(ubah ? java.awt.Color.WHITE : Gaya.LATAR);
        }
        btnUbah.setVisible(!ubah);
        btnSimpan.setVisible(ubah);
        btnBatal.setVisible(ubah);
        for (javax.swing.JLabel l : new javax.swing.JLabel[]{lblErrNama, lblErrUsername, lblErrTelepon, lblErrAlamat}) {
            Gaya.error(l, null);
        }
        if (ubah) txtNama.requestFocusInWindow();
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlTengah = new javax.swing.JPanel();
        pnlData = new javax.swing.JPanel();
        lblDataJudul = new javax.swing.JLabel();
        lblKode = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblErrNama = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblErrUsername = new javax.swing.JLabel();
        lblTelepon = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        lblErrTelepon = new javax.swing.JLabel();
        lblBergabung = new javax.swing.JLabel();
        txtBergabung = new javax.swing.JTextField();
        lblErrBergabung = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        lblErrAlamat = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        pnlSisa = new javax.swing.JPanel();

        setBackground(new java.awt.Color(245, 247, 251));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 32, 24, 32));
        setLayout(new java.awt.BorderLayout());

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Profil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Data diri dipakai sebagai alamat pengiriman pesanan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);
        add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlTengah.setBackground(new java.awt.Color(255, 255, 255));
        pnlTengah.setOpaque(false);
        pnlTengah.setLayout(new java.awt.GridBagLayout());
        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(24, 24, 20, 24)));
        pnlData.setPreferredSize(new java.awt.Dimension(700, 420));
        pnlData.setLayout(new java.awt.GridBagLayout());
        lblDataJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblDataJudul.setText("Data diri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlData.add(lblDataJudul, gridBagConstraints);
        lblKode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblKode.setForeground(new java.awt.Color(102, 112, 133));
        lblKode.setText("Kode pelanggan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 18, 0);
        pnlData.add(lblKode, gridBagConstraints);
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNama.setForeground(new java.awt.Color(52, 64, 84));
        lblNama.setText("Nama lengkap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblNama, gridBagConstraints);
        txtNama.setBackground(new java.awt.Color(245, 247, 251));
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtNama.setEditable(false);
        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(16, 24, 40));
        txtNama.setPreferredSize(new java.awt.Dimension(280, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtNama, gridBagConstraints);
        lblErrNama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrNama.setForeground(new java.awt.Color(217, 45, 32));
        lblErrNama.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlData.add(lblErrNama, gridBagConstraints);
        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(52, 64, 84));
        lblUsername.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlData.add(lblUsername, gridBagConstraints);
        txtUsername.setBackground(new java.awt.Color(245, 247, 251));
        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtUsername.setEditable(false);
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(16, 24, 40));
        txtUsername.setPreferredSize(new java.awt.Dimension(280, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 0);
        pnlData.add(txtUsername, gridBagConstraints);
        lblErrUsername.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrUsername.setForeground(new java.awt.Color(217, 45, 32));
        lblErrUsername.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrUsername.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 0, 0);
        pnlData.add(lblErrUsername, gridBagConstraints);
        lblTelepon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelepon.setForeground(new java.awt.Color(52, 64, 84));
        lblTelepon.setText("No. telepon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlData.add(lblTelepon, gridBagConstraints);
        txtTelepon.setBackground(new java.awt.Color(245, 247, 251));
        txtTelepon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtTelepon.setEditable(false);
        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelepon.setForeground(new java.awt.Color(16, 24, 40));
        txtTelepon.setPreferredSize(new java.awt.Dimension(280, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtTelepon, gridBagConstraints);
        lblErrTelepon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrTelepon.setForeground(new java.awt.Color(217, 45, 32));
        lblErrTelepon.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrTelepon.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlData.add(lblErrTelepon, gridBagConstraints);
        lblBergabung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBergabung.setForeground(new java.awt.Color(52, 64, 84));
        lblBergabung.setText("Bergabung sejak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 20, 0, 0);
        pnlData.add(lblBergabung, gridBagConstraints);
        txtBergabung.setBackground(new java.awt.Color(245, 247, 251));
        txtBergabung.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtBergabung.setEditable(false);
        txtBergabung.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBergabung.setForeground(new java.awt.Color(16, 24, 40));
        txtBergabung.setPreferredSize(new java.awt.Dimension(280, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 0);
        pnlData.add(txtBergabung, gridBagConstraints);
        lblErrBergabung.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrBergabung.setForeground(new java.awt.Color(217, 45, 32));
        lblErrBergabung.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrBergabung.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 0, 0);
        pnlData.add(lblErrBergabung, gridBagConstraints);
        lblAlamat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(52, 64, 84));
        lblAlamat.setText("Alamat pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlData.add(lblAlamat, gridBagConstraints);
        txtAlamat.setBackground(new java.awt.Color(245, 247, 251));
        txtAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtAlamat.setEditable(false);
        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(16, 24, 40));
        txtAlamat.setPreferredSize(new java.awt.Dimension(280, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtAlamat, gridBagConstraints);
        lblErrAlamat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrAlamat.setForeground(new java.awt.Color(217, 45, 32));
        lblErrAlamat.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrAlamat.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlData.add(lblErrAlamat, gridBagConstraints);
        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnBatal.setBackground(new java.awt.Color(255, 255, 255));
        btnBatal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(16, 24, 40));
        btnBatal.setPreferredSize(new java.awt.Dimension(100, 36));
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBatal);
        btnUbah.setBackground(new java.awt.Color(31, 94, 219));
        btnUbah.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.setFocusPainted(false);
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setPreferredSize(new java.awt.Dimension(130, 36));
        btnUbah.setText("Ubah Data");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        pnlTombol.add(btnUbah);
        btnSimpan.setBackground(new java.awt.Color(31, 94, 219));
        btnSimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setPreferredSize(new java.awt.Dimension(130, 36));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlTombol.add(btnSimpan);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlData.add(pnlTombol, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlTengah.add(pnlData, gridBagConstraints);

        pnlSisa.setBackground(new java.awt.Color(255, 255, 255));
        pnlSisa.setOpaque(false);
        pnlSisa.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTengah.add(pnlSisa, gridBagConstraints);

        add(pnlTengah, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        muat();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        modeUbah(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (!KonfirmasiDialog.tanya(this, "Simpan data diri", "Pastikan data diri sudah benar. Simpan perubahan?",
                "Simpan", "dialog_tanya", false)) {
            return;
        }
        try {
            controller.ubahProfil(txtNama.getText(), txtUsername.getText(), txtTelepon.getText(), txtAlamat.getText());
            induk.perbaruiNama();
            muat();
            NotifikasiDialog.tampil(this, "Data diri berhasil disimpan", null);
        } catch (ValidasiException e) {
            Gaya.error(lblErrNama, e.getPesan("nama"));
            Gaya.error(lblErrUsername, e.getPesan("username"));
            Gaya.error(lblErrTelepon, e.getPesan("telepon"));
            Gaya.error(lblErrAlamat, e.getPesan("alamat"));
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBergabung;
    private javax.swing.JLabel lblDataJudul;
    private javax.swing.JLabel lblErrAlamat;
    private javax.swing.JLabel lblErrBergabung;
    private javax.swing.JLabel lblErrNama;
    private javax.swing.JLabel lblErrTelepon;
    private javax.swing.JLabel lblErrUsername;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTelepon;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlSisa;
    private javax.swing.JPanel pnlTengah;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtBergabung;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
