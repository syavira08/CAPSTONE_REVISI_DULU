package view;

import model.Produk;

/** Satu kartu produk pada katalog: gambar, kategori & stok, nama, harga, tombol "+ Keranjang". */
public class KartuProduk extends javax.swing.JPanel {

    private final Runnable tambah;

    public KartuProduk(Produk p, Runnable tambah) {
        this.tambah = tambah;
        initComponents();
        UI.kartu(this, 10, 10, 10, 10);
        lblGambar.setIcon(new UI.GambarProduk(p.getNama(), 100, 120));
        lblKategori.setText(p.getKategori() + " • Stok " + p.getStok());
        lblNama.setText(p.getNama());
        lblHarga.setText(UI.rupiah(p.getHarga()));
        boolean ada = p.getStok() > 0;
        btnKeranjang.setText(ada ? "+ Keranjang" : "Stok habis");
        UI.primer(btnKeranjang);
        btnKeranjang.setEnabled(ada);
        UI.tema(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblGambar = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblKategori = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        btnKeranjang = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setLayout(new java.awt.BorderLayout(14, 0));

        lblGambar.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblGambar.setForeground(new java.awt.Color(23, 43, 77));
        lblGambar.setText("");
        lblGambar.setPreferredSize(new java.awt.Dimension(100, 120));
        add(lblGambar, java.awt.BorderLayout.WEST);

        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new java.awt.GridBagLayout());
        lblKategori.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblKategori.setForeground(new java.awt.Color(94, 108, 132));
        lblKategori.setText("Aksesoris \u2022 Stok 18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlInfo.add(lblKategori, gridBagConstraints);
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblNama.setForeground(new java.awt.Color(23, 43, 77));
        lblNama.setText("Charger USB-C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlInfo.add(lblNama, gridBagConstraints);
        lblHarga.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblHarga.setForeground(new java.awt.Color(33, 97, 213));
        lblHarga.setText("Rp125.000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlInfo.add(lblHarga, gridBagConstraints);
        btnKeranjang.setBackground(new java.awt.Color(33, 97, 213));
        btnKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnKeranjang.setForeground(new java.awt.Color(255, 255, 255));
        btnKeranjang.setText("+ Keranjang");
        btnKeranjang.setFocusPainted(false);
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlInfo.add(btnKeranjang, gridBagConstraints);
        add(pnlInfo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        tambah.run();
    }//GEN-LAST:event_btnKeranjangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblNama;
    private javax.swing.JPanel pnlInfo;
    // End of variables declaration//GEN-END:variables
}
