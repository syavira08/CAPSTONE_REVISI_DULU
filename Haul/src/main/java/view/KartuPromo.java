package view;

import model.Promo;

/** Kartu voucher yang sedang berlaku (ditampilkan di Beranda). */
public class KartuPromo extends javax.swing.JPanel {

    public KartuPromo(Promo promo) {
        initComponents();
        lblKode.setText(promo.getKode());
        lblPotongan.setText("Potongan " + promo.getTeksDiskon());
        lblSyarat.setText("Min. " + promo.getMinimalBarang() + " barang \u2022 berlaku s.d. " + Gaya.tanggal(promo.getSelesai()));
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblKode = new javax.swing.JLabel();
        lblPotongan = new javax.swing.JLabel();
        lblSyarat = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 4, 1, 1, new java.awt.Color(31, 94, 219)), javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14)));
        setLayout(new java.awt.GridBagLayout());

        lblKode.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblKode.setForeground(new java.awt.Color(31, 94, 219));
        lblKode.setText("KODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(lblKode, gridBagConstraints);
        lblPotongan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPotongan.setForeground(new java.awt.Color(16, 24, 40));
        lblPotongan.setText("Potongan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(lblPotongan, gridBagConstraints);
        lblSyarat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSyarat.setForeground(new java.awt.Color(102, 112, 133));
        lblSyarat.setText("Syarat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(lblSyarat, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblPotongan;
    private javax.swing.JLabel lblSyarat;
    // End of variables declaration//GEN-END:variables
}
