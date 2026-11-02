package view;

import java.util.function.IntConsumer;
import model.Pesanan;

/** Satu baris barang di keranjang: gambar, nama, atur jumlah (− +), hapus, dan subtotal. */
public class BarisKeranjang extends javax.swing.JPanel {

    private final Pesanan.Item item;
    private final IntConsumer ubahJumlah;
    private final Runnable hapus;

    public BarisKeranjang(Pesanan.Item item, IntConsumer ubahJumlah, Runnable hapus) {
        this.item = item;
        this.ubahJumlah = ubahJumlah;
        this.hapus = hapus;
        initComponents();
        lblGambar.setIcon(new UI.GambarProduk(item.getProduk().getNama(), 90, 70));
        lblNama.setText(item.getProduk().getNama());
        lblMeta.setText(item.getProduk().getKategori() + " • Stok tersedia: " + item.getProduk().getStok());
        lblJumlah.setText(String.valueOf(item.getJumlah()));
        lblSubNilai.setText(UI.rupiah(item.getSubtotal()));
        UI.tombol(btnKurang, UI.Jenis.SEKUNDER, true);
        UI.tombol(btnTambah, UI.Jenis.SEKUNDER, true);
        UI.tombol(btnHapus, UI.Jenis.SEKUNDER, true);
        UI.tema(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblGambar = new javax.swing.JLabel();
        pnlTengah = new javax.swing.JPanel();
        lblNama = new javax.swing.JLabel();
        lblMeta = new javax.swing.JLabel();
        pnlAksi = new javax.swing.JPanel();
        btnKurang = new javax.swing.JButton();
        lblJumlah = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        pnlSubtotal = new javax.swing.JPanel();
        lblSubJudul = new javax.swing.JLabel();
        lblSubNilai = new javax.swing.JLabel();

        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout(new java.awt.BorderLayout(16, 0));

        lblGambar.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblGambar.setForeground(new java.awt.Color(23, 43, 77));
        lblGambar.setText("");
        lblGambar.setPreferredSize(new java.awt.Dimension(90, 70));
        add(lblGambar, java.awt.BorderLayout.WEST);

        pnlTengah.setOpaque(false);
        pnlTengah.setLayout(new java.awt.GridBagLayout());
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblNama.setForeground(new java.awt.Color(23, 43, 77));
        lblNama.setText("Headphone Bluetooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTengah.add(lblNama, gridBagConstraints);
        lblMeta.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblMeta.setForeground(new java.awt.Color(94, 108, 132));
        lblMeta.setText("Elektronik \u2022 Stok tersedia: 20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTengah.add(lblMeta, gridBagConstraints);
        pnlAksi.setOpaque(false);
        pnlAksi.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        btnKurang.setBackground(new java.awt.Color(255, 255, 255));
        btnKurang.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnKurang.setForeground(new java.awt.Color(23, 43, 77));
        btnKurang.setText("\u2212");
        btnKurang.setFocusPainted(false);
        btnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurangActionPerformed(evt);
            }
        });
        pnlAksi.add(btnKurang);
        lblJumlah.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblJumlah.setForeground(new java.awt.Color(23, 43, 77));
        lblJumlah.setText("1");
        lblJumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJumlah.setPreferredSize(new java.awt.Dimension(34, 20));
        pnlAksi.add(lblJumlah);
        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnTambah.setForeground(new java.awt.Color(23, 43, 77));
        btnTambah.setText("+");
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        pnlAksi.add(btnTambah);
        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnHapus.setForeground(new java.awt.Color(23, 43, 77));
        btnHapus.setText("Hapus");
        btnHapus.setFocusPainted(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        pnlAksi.add(btnHapus);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTengah.add(pnlAksi, gridBagConstraints);
        add(pnlTengah, java.awt.BorderLayout.CENTER);

        pnlSubtotal.setOpaque(false);
        pnlSubtotal.setLayout(new java.awt.GridBagLayout());
        lblSubJudul.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblSubJudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubJudul.setText("Subtotal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSubtotal.add(lblSubJudul, gridBagConstraints);
        lblSubNilai.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblSubNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblSubNilai.setText("Rp350.000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlSubtotal.add(lblSubNilai, gridBagConstraints);
        add(pnlSubtotal, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurangActionPerformed
        ubahJumlah.accept(item.getJumlah() - 1);
    }//GEN-LAST:event_btnKurangActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        ubahJumlah.accept(item.getJumlah() + 1);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapus.run();
    }//GEN-LAST:event_btnHapusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKurang;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblMeta;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblSubJudul;
    private javax.swing.JLabel lblSubNilai;
    private javax.swing.JPanel pnlAksi;
    private javax.swing.JPanel pnlSubtotal;
    private javax.swing.JPanel pnlTengah;
    // End of variables declaration//GEN-END:variables
}
