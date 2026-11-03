package view;

import controller.AuthController;

/** Menu utama admin: Kelola Produk, Promo, Pesanan, Data Pelanggan, dan Logout (paling bawah). */
public class AdminView extends javax.swing.JFrame {

    private final java.util.Map<String, Halaman> halaman = new java.util.LinkedHashMap<>();
    private final java.util.Map<String, javax.swing.JButton> tombolMenu = new java.util.LinkedHashMap<>();

    public AdminView() {
        initComponents();
        lblNamaAdmin.setText(AuthController.getPenggunaLogin().getNama());   // nama saja, tanpa jabatan
        // logika layout controller: setiap halaman (JPanel Form) dipasang ke CardLayout
        daftarkan("produk", new ProdukPanel(), btnProduk);
        daftarkan("promo", new PromoPanel(), btnPromo);
        daftarkan("pesanan", new PesananAdminPanel(), btnPesanan);
        daftarkan("pelanggan", new DataPelangganPanel(), btnPelanggan);
        buka("produk");
    }

    private void daftarkan(String nama, javax.swing.JPanel panel, javax.swing.JButton tombol) {
        halaman.put(nama, (Halaman) panel);
        tombolMenu.put(nama, tombol);
        pnlHalaman.add(panel, nama);
    }

    /** Buka halaman, tandai menu aktif, lalu muat ulang datanya. */
    private void buka(String nama) {
        ((java.awt.CardLayout) pnlHalaman.getLayout()).show(pnlHalaman, nama);
        tombolMenu.forEach((n, b) -> {
            boolean aktif = n.equals(nama);
            b.setBackground(aktif ? Gaya.BIRU_MUDA : java.awt.Color.WHITE);
            b.setForeground(aktif ? Gaya.BIRU : new java.awt.Color(52, 64, 84));
            b.setIcon(Gaya.ikon(aktif ? n + "_aktif" : n));
        });
        lblLokasi.setText("Administrasi / " + tombolMenu.get(nama).getText());
        halaman.get(nama).muat();
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlSamping = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        btnProduk = new javax.swing.JButton();
        btnPromo = new javax.swing.JButton();
        btnPesanan = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        pnlSisa = new javax.swing.JPanel();
        sepLogout = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        pnlUtama = new javax.swing.JPanel();
        pnlAtas = new javax.swing.JPanel();
        lblLokasi = new javax.swing.JLabel();
        lblNamaAdmin = new javax.swing.JLabel();
        pnlHalaman = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1240, 740));
        setTitle("HAULECTRA - Admin");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlSamping.setBackground(new java.awt.Color(255, 255, 255));
        pnlSamping.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 16, 20, 16)));
        pnlSamping.setPreferredSize(new java.awt.Dimension(248, 600));
        pnlSamping.setLayout(new java.awt.GridBagLayout());
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(16, 24, 40));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/logo.png"))); // NOI18N
        lblLogo.setIconTextGap(10);
        lblLogo.setText("HAULECTRA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 28, 0);
        pnlSamping.add(lblLogo, gridBagConstraints);
        lblMenu.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(102, 112, 133));
        lblMenu.setText("MENU ADMIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 8, 0);
        pnlSamping.add(lblMenu, gridBagConstraints);
        btnProduk.setBackground(new java.awt.Color(255, 255, 255));
        btnProduk.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnProduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduk.setFocusPainted(false);
        btnProduk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProduk.setForeground(new java.awt.Color(52, 64, 84));
        btnProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/produk.png"))); // NOI18N
        btnProduk.setIconTextGap(10);
        btnProduk.setPreferredSize(new java.awt.Dimension(200, 42));
        btnProduk.setText("Kelola Produk");
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlSamping.add(btnProduk, gridBagConstraints);
        btnPromo.setBackground(new java.awt.Color(255, 255, 255));
        btnPromo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnPromo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPromo.setFocusPainted(false);
        btnPromo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPromo.setForeground(new java.awt.Color(52, 64, 84));
        btnPromo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPromo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/promo.png"))); // NOI18N
        btnPromo.setIconTextGap(10);
        btnPromo.setPreferredSize(new java.awt.Dimension(200, 42));
        btnPromo.setText("Kelola Promo");
        btnPromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlSamping.add(btnPromo, gridBagConstraints);
        btnPesanan.setBackground(new java.awt.Color(255, 255, 255));
        btnPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesanan.setFocusPainted(false);
        btnPesanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPesanan.setForeground(new java.awt.Color(52, 64, 84));
        btnPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/pesanan.png"))); // NOI18N
        btnPesanan.setIconTextGap(10);
        btnPesanan.setPreferredSize(new java.awt.Dimension(200, 42));
        btnPesanan.setText("Kelola Pesanan");
        btnPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesananActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlSamping.add(btnPesanan, gridBagConstraints);
        btnPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        btnPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPelanggan.setFocusPainted(false);
        btnPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPelanggan.setForeground(new java.awt.Color(52, 64, 84));
        btnPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/pelanggan.png"))); // NOI18N
        btnPelanggan.setIconTextGap(10);
        btnPelanggan.setPreferredSize(new java.awt.Dimension(200, 42));
        btnPelanggan.setText("Data Pelanggan");
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlSamping.add(btnPelanggan, gridBagConstraints);
        pnlSisa.setBackground(new java.awt.Color(255, 255, 255));
        pnlSisa.setOpaque(false);
        pnlSisa.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlSamping.add(pnlSisa, gridBagConstraints);

        sepLogout.setForeground(new java.awt.Color(228, 231, 236));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlSamping.add(sepLogout, gridBagConstraints);
        btnLogout.setBackground(new java.awt.Color(217, 45, 32));
        btnLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/logout_putih.png"))); // NOI18N
        btnLogout.setIconTextGap(8);
        btnLogout.setPreferredSize(new java.awt.Dimension(120, 42));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlSamping.add(btnLogout, gridBagConstraints);
        getContentPane().add(pnlSamping, java.awt.BorderLayout.WEST);

        pnlUtama.setBackground(new java.awt.Color(245, 247, 251));
        pnlUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlUtama.setLayout(new java.awt.BorderLayout());
        pnlAtas.setBackground(new java.awt.Color(255, 255, 255));
        pnlAtas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 32, 0, 32)));
        pnlAtas.setPreferredSize(new java.awt.Dimension(900, 56));
        pnlAtas.setLayout(new java.awt.BorderLayout());
        lblLokasi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLokasi.setForeground(new java.awt.Color(102, 112, 133));
        lblLokasi.setText("Administrasi / Produk");
        pnlAtas.add(lblLokasi, java.awt.BorderLayout.WEST);
        lblNamaAdmin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNamaAdmin.setForeground(new java.awt.Color(52, 64, 84));
        lblNamaAdmin.setText("Nama Admin");
        pnlAtas.add(lblNamaAdmin, java.awt.BorderLayout.EAST);
        pnlUtama.add(pnlAtas, java.awt.BorderLayout.NORTH);

        pnlHalaman.setBackground(new java.awt.Color(245, 247, 251));
        pnlHalaman.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlHalaman.setLayout(new java.awt.CardLayout());
        pnlUtama.add(pnlHalaman, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlUtama, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        buka("produk");
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromoActionPerformed
        buka("promo");
    }//GEN-LAST:event_btnPromoActionPerformed

    private void btnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesananActionPerformed
        buka("pesanan");
    }//GEN-LAST:event_btnPesananActionPerformed

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
        buka("pelanggan");
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        if (KonfirmasiDialog.tanya(this, "Logout", "Yakin ingin keluar dari akun admin?", "Logout",
                "dialog_logout", true)) {
            new AuthController().logout();
            dispose();
            new LoginView().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPesanan;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnPromo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLokasi;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblNamaAdmin;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlHalaman;
    private javax.swing.JPanel pnlSamping;
    private javax.swing.JPanel pnlSisa;
    private javax.swing.JPanel pnlUtama;
    private javax.swing.JSeparator sepLogout;
    // End of variables declaration//GEN-END:variables
}
