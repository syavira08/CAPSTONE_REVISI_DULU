package view;

import controller.AuthController;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Flowchart Menu Admin: Kelola Produk, Promo, Pengiriman, Pesanan, Data Pelanggan, Logout (sidebar kiri).
 * Tampilan dibuat dengan GUI Form Builder (AdminView.form); halaman ditukar memakai CardLayout.
 */
public class AdminView extends javax.swing.JFrame {

    private final Map<String, Halaman> halaman = new LinkedHashMap<>();
    private final Map<String, JLabel> menu = new LinkedHashMap<>();

    public AdminView() {
        initComponents();
        UI.logo(lblLogo);
        lblAdmin.setText(AuthController.getPenggunaLogin().getKeterangan());
        tambahMenu("Produk", "produk", lblMenuProduk, new ProdukPanel());
        tambahMenu("Promo", "promo", lblMenuPromo, new PromoPanel());
        tambahMenu("Pengiriman", "truk", lblMenuPengiriman, new PengirimanPanel());
        tambahMenu("Pesanan", "pesanan", lblMenuPesanan, new PesananAdminPanel());
        tambahMenu("Data Pelanggan", "pelanggan", lblMenuPelanggan, new DataPelangganPanel());
        UI.ikon(lblMenuLogout, "keluar", 18, UI.PUDAR);
        lblMenuLogout.setIconTextGap(10);
        lblMenuLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(null);
        buka("Produk");
    }

    private void tambahMenu(String nama, String ikon, JLabel item, JComponent h) {
        halaman.put(nama, (Halaman) h);
        pnlWadah.add(UI.gulir(h), nama);
        UI.ikon(item, ikon, 18, UI.PUDAR);
        item.setIconTextGap(10);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menu.put(nama, item);
    }

    /** Buka halaman menu, muat datanya, dan sorot menu di sidebar. */
    private void buka(String nama) {
        Halaman h = halaman.get(nama);
        h.bersihkanPesan();
        h.muat();
        ((CardLayout) pnlWadah.getLayout()).show(pnlWadah, nama);
        lblJejak.setText("Administrasi / " + nama);
        menu.forEach((n, l) -> {
            l.setBackground(n.equals(nama) ? UI.BIRU_MUDA : UI.PUTIH);
            l.setForeground(n.equals(nama) ? UI.BIRU : UI.TEKS);
        });
    }

    private void logout() {
        new AuthController().logout();
        dispose();
        new LoginView().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblRuang = new javax.swing.JLabel();
        lblMenuProduk = new javax.swing.JLabel();
        lblMenuPromo = new javax.swing.JLabel();
        lblMenuPengiriman = new javax.swing.JLabel();
        lblMenuPesanan = new javax.swing.JLabel();
        lblMenuPelanggan = new javax.swing.JLabel();
        lblMenuLogout = new javax.swing.JLabel();
        pnlPenutup = new javax.swing.JPanel();
        lblVersi = new javax.swing.JLabel();
        pnlKanan = new javax.swing.JPanel();
        pnlTopbar = new javax.swing.JPanel();
        lblJejak = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        pnlWadah = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HAULECTRA • Admin");
        setPreferredSize(new java.awt.Dimension(1280, 820));

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 12)));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(210, 10));
        pnlSidebar.setLayout(new java.awt.GridBagLayout());

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(23, 43, 77));
        lblLogo.setText("HAULECTRA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSidebar.add(lblLogo, gridBagConstraints);

        lblRuang.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRuang.setForeground(new java.awt.Color(94, 108, 132));
        lblRuang.setText("RUANG ADMINISTRASI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        pnlSidebar.add(lblRuang, gridBagConstraints);

        lblMenuProduk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuProduk.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuProduk.setText("Produk");
        lblMenuProduk.setOpaque(true);
        lblMenuProduk.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuProduk.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuProdukMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlSidebar.add(lblMenuProduk, gridBagConstraints);

        lblMenuPromo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuPromo.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuPromo.setText("Promo");
        lblMenuPromo.setOpaque(true);
        lblMenuPromo.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuPromo.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuPromo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPromoMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlSidebar.add(lblMenuPromo, gridBagConstraints);

        lblMenuPengiriman.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuPengiriman.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuPengiriman.setText("Pengiriman");
        lblMenuPengiriman.setOpaque(true);
        lblMenuPengiriman.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuPengiriman.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuPengiriman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPengirimanMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlSidebar.add(lblMenuPengiriman, gridBagConstraints);

        lblMenuPesanan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuPesanan.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuPesanan.setText("Pesanan");
        lblMenuPesanan.setOpaque(true);
        lblMenuPesanan.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPesananMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlSidebar.add(lblMenuPesanan, gridBagConstraints);

        lblMenuPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuPelanggan.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuPelanggan.setText("Data Pelanggan");
        lblMenuPelanggan.setOpaque(true);
        lblMenuPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPelangganMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlSidebar.add(lblMenuPelanggan, gridBagConstraints);

        lblMenuLogout.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMenuLogout.setForeground(new java.awt.Color(23, 43, 77));
        lblMenuLogout.setText("Logout");
        lblMenuLogout.setOpaque(true);
        lblMenuLogout.setBackground(new java.awt.Color(255, 255, 255));
        lblMenuLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 10, 9, 10));
        lblMenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuLogoutMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlSidebar.add(lblMenuLogout, gridBagConstraints);

        pnlPenutup.setPreferredSize(new java.awt.Dimension(1, 1));
        pnlPenutup.setOpaque(false);
        pnlPenutup.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlSidebar.add(pnlPenutup, gridBagConstraints);

        lblVersi.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblVersi.setForeground(new java.awt.Color(94, 108, 132));
        lblVersi.setText("<html>HAULECTRA Desktop<br>Versi 1.0 • Admin</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlSidebar.add(lblVersi, gridBagConstraints);

        getContentPane().add(pnlSidebar, java.awt.BorderLayout.WEST);

        pnlKanan.setOpaque(false);
        pnlKanan.setLayout(new java.awt.BorderLayout());

        pnlTopbar.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopbar.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(14, 32, 14, 32)));
        pnlTopbar.setLayout(new java.awt.BorderLayout());

        lblJejak.setForeground(new java.awt.Color(94, 108, 132));
        lblJejak.setText("Administrasi / Produk");
        pnlTopbar.add(lblJejak, java.awt.BorderLayout.WEST);

        lblAdmin.setForeground(new java.awt.Color(23, 43, 77));
        lblAdmin.setText("Admin Haul 3 • Administrator");
        pnlTopbar.add(lblAdmin, java.awt.BorderLayout.EAST);

        pnlKanan.add(pnlTopbar, java.awt.BorderLayout.NORTH);

        pnlWadah.setBackground(new java.awt.Color(243, 246, 250));
        pnlWadah.setLayout(new java.awt.CardLayout());
        pnlKanan.add(pnlWadah, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlKanan, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMenuProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuProdukMouseClicked
        buka("Produk");
    }//GEN-LAST:event_lblMenuProdukMouseClicked

    private void lblMenuPromoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuPromoMouseClicked
        buka("Promo");
    }//GEN-LAST:event_lblMenuPromoMouseClicked

    private void lblMenuPengirimanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuPengirimanMouseClicked
        buka("Pengiriman");
    }//GEN-LAST:event_lblMenuPengirimanMouseClicked

    private void lblMenuPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuPesananMouseClicked
        buka("Pesanan");
    }//GEN-LAST:event_lblMenuPesananMouseClicked

    private void lblMenuPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuPelangganMouseClicked
        buka("Data Pelanggan");
    }//GEN-LAST:event_lblMenuPelangganMouseClicked

    private void lblMenuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuLogoutMouseClicked
        logout();
    }//GEN-LAST:event_lblMenuLogoutMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblJejak;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuLogout;
    private javax.swing.JLabel lblMenuPelanggan;
    private javax.swing.JLabel lblMenuPengiriman;
    private javax.swing.JLabel lblMenuPesanan;
    private javax.swing.JLabel lblMenuProduk;
    private javax.swing.JLabel lblMenuPromo;
    private javax.swing.JLabel lblRuang;
    private javax.swing.JLabel lblVersi;
    private javax.swing.JPanel pnlKanan;
    private javax.swing.JPanel pnlPenutup;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopbar;
    private javax.swing.JPanel pnlWadah;
    // End of variables declaration//GEN-END:variables
}
