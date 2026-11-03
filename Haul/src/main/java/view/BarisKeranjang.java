package view;

import model.Pesanan;

/** Satu baris barang di keranjang. */
public class BarisKeranjang extends javax.swing.JPanel {

    private final Pesanan.Item item;
    private final KeranjangPanel induk;

    public BarisKeranjang(Pesanan.Item item, KeranjangPanel induk) {
        this.item = item;
        this.induk = induk;
        initComponents();
        lblFoto.setIcon(Gaya.foto(item.getProduk().getGambar(), 72, 72));
        lblNama.setText(item.getProduk().getNama());
        lblHarga.setText(Gaya.rupiah(item.getProduk().getHarga()) + " / barang \u2022 stok " + item.getProduk().getStok());
        lblJumlah.setText(String.valueOf(item.getJumlah()));
        lblSubtotal.setText(Gaya.rupiah(item.getSubtotal()));
        btnTambah.setEnabled(item.getJumlah() < item.getProduk().getStok());   // tidak bisa melebihi stok
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblFoto = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        pnlJumlah = new javax.swing.JPanel();
        btnKurang = new javax.swing.JButton();
        lblJumlah = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        lblSubtotal = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 16)));
        setLayout(new java.awt.GridBagLayout());

        lblFoto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(16, 24, 40));
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/foto_kosong.png"))); // NOI18N
        lblFoto.setPreferredSize(new java.awt.Dimension(72, 72));
        lblFoto.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 14);
        add(lblFoto, gridBagConstraints);
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblNama.setForeground(new java.awt.Color(16, 24, 40));
        lblNama.setText("Nama produk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(lblNama, gridBagConstraints);
        lblHarga.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblHarga.setForeground(new java.awt.Color(102, 112, 133));
        lblHarga.setText("Rp0 / barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        add(lblHarga, gridBagConstraints);
        pnlJumlah.setBackground(new java.awt.Color(255, 255, 255));
        pnlJumlah.setOpaque(false);
        pnlJumlah.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        btnKurang.setBackground(new java.awt.Color(255, 255, 255));
        btnKurang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        btnKurang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKurang.setFocusPainted(false);
        btnKurang.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnKurang.setForeground(new java.awt.Color(16, 24, 40));
        btnKurang.setPreferredSize(new java.awt.Dimension(34, 34));
        btnKurang.setText("\u2212");
        btnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurangActionPerformed(evt);
            }
        });
        pnlJumlah.add(btnKurang);
        lblJumlah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(16, 24, 40));
        lblJumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJumlah.setPreferredSize(new java.awt.Dimension(44, 34));
        lblJumlah.setText("1");
        pnlJumlah.add(lblJumlah);
        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setFocusPainted(false);
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(16, 24, 40));
        btnTambah.setPreferredSize(new java.awt.Dimension(34, 34));
        btnTambah.setText("+");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        pnlJumlah.add(btnTambah);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        add(pnlJumlah, gridBagConstraints);

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(16, 24, 40));
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotal.setPreferredSize(new java.awt.Dimension(130, 24));
        lblSubtotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        add(lblSubtotal, gridBagConstraints);
        btnHapus.setBackground(new java.awt.Color(253, 236, 236));
        btnHapus.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setFocusPainted(false);
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(217, 45, 32));
        btnHapus.setPreferredSize(new java.awt.Dimension(80, 34));
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        add(btnHapus, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurangActionPerformed
        induk.ubahJumlah(item, item.getJumlah() - 1);
    }//GEN-LAST:event_btnKurangActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        induk.ubahJumlah(item, item.getJumlah() + 1);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        induk.hapus(item);
    }//GEN-LAST:event_btnHapusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKurang;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JPanel pnlJumlah;
    // End of variables declaration//GEN-END:variables
}
