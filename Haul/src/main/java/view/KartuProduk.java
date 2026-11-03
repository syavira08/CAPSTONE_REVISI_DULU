package view;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import model.Produk;

/** Kartu satu produk di Beranda: foto, nama, harga, tombol Lihat & Keranjang. */
public class KartuProduk extends javax.swing.JPanel {

    private final Produk produk;
    private final Consumer<Produk> aksiLihat;
    private final BiConsumer<Produk, Integer> aksiTambah;

    public KartuProduk(Produk produk, Consumer<Produk> aksiLihat, BiConsumer<Produk, Integer> aksiTambah) {
        this.produk = produk;
        this.aksiLihat = aksiLihat;
        this.aksiTambah = aksiTambah;
        initComponents();
        lblFoto.setIcon(Gaya.foto(produk.getGambar(), 112, 112));
        lblNama.setText(produk.getNama());
        lblNama.setToolTipText(produk.getNama());
        lblHarga.setText(Gaya.rupiah(produk.getHarga()));
        boolean habis = produk.getStok() <= 0;
        lblKategori.setText(produk.getKategori() + " \u2022 " + (habis ? "Stok habis" : "Stok " + produk.getStok()));
        btnKeranjang.setEnabled(!habis);
        if (habis) btnKeranjang.setBackground(Gaya.GARIS);
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblFoto = new javax.swing.JLabel();
        lblKategori = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnLihat = new javax.swing.JButton();
        lblJarak = new javax.swing.JLabel();
        btnKeranjang = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        setLayout(new java.awt.GridBagLayout());

        lblFoto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(16, 24, 40));
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/foto_kosong.png"))); // NOI18N
        lblFoto.setPreferredSize(new java.awt.Dimension(112, 112));
        lblFoto.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 14);
        add(lblFoto, gridBagConstraints);
        lblKategori.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblKategori.setForeground(new java.awt.Color(102, 112, 133));
        lblKategori.setText("Kategori \u2022 Stok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(lblKategori, gridBagConstraints);
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblNama.setForeground(new java.awt.Color(16, 24, 40));
        lblNama.setText("Nama produk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        add(lblNama, gridBagConstraints);
        lblHarga.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblHarga.setForeground(new java.awt.Color(31, 94, 219));
        lblHarga.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        add(lblHarga, gridBagConstraints);
        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        btnLihat.setBackground(new java.awt.Color(255, 255, 255));
        btnLihat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnLihat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLihat.setFocusPainted(false);
        btnLihat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLihat.setForeground(new java.awt.Color(16, 24, 40));
        btnLihat.setPreferredSize(new java.awt.Dimension(74, 34));
        btnLihat.setText("Lihat");
        btnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatActionPerformed(evt);
            }
        });
        pnlTombol.add(btnLihat);
        lblJarak.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJarak.setForeground(new java.awt.Color(16, 24, 40));
        lblJarak.setPreferredSize(new java.awt.Dimension(8, 8));
        lblJarak.setText("");
        pnlTombol.add(lblJarak);
        btnKeranjang.setBackground(new java.awt.Color(31, 94, 219));
        btnKeranjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeranjang.setFocusPainted(false);
        btnKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKeranjang.setForeground(new java.awt.Color(255, 255, 255));
        btnKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/keranjang_kartu.png"))); // NOI18N
        btnKeranjang.setIconTextGap(6);
        btnKeranjang.setPreferredSize(new java.awt.Dimension(128, 34));
        btnKeranjang.setText("Keranjang");
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });
        pnlTombol.add(btnKeranjang);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        add(pnlTombol, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatActionPerformed
        aksiLihat.accept(produk);
    }//GEN-LAST:event_btnLihatActionPerformed

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        aksiTambah.accept(produk, 1);
    }//GEN-LAST:event_btnKeranjangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnLihat;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJarak;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblNama;
    private javax.swing.JPanel pnlTombol;
    // End of variables declaration//GEN-END:variables
}
