package view;

import controller.PesananController;
import controller.ValidasiException;
import model.Pesanan;

/** Halaman pembayaran: pelanggan memasukkan nominal, status otomatis menjadi Diproses. */
public class PembayaranDialog extends javax.swing.JDialog {

    private final PesananController controller = new PesananController();
    private Pesanan pesanan;
    private boolean lunas;

    public PembayaranDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    /** Pelanggan memasukkan nominal. Mengembalikan true jika pembayaran berhasil. */
    public static boolean bayar(java.awt.Component asal, Pesanan p) {
        PembayaranDialog d = new PembayaranDialog(Gaya.jendela(asal));
        d.pesanan = p;
        d.lblSub.setText("Pesanan " + p.getNomor() + " \u2022 " + Gaya.metode(p.getMetodeBayar()));
        d.lblTotal.setText(Gaya.rupiah(p.getTotalHarga()));
        d.getRootPane().setDefaultButton(d.btnBayar);
        d.pack();
        d.setLocationRelativeTo(asal);
        d.setVisible(true);
        return d.lunas;
    }

    private void prosesBayar() {
        try {
            controller.bayar(pesanan, txtNominal.getText());
            lunas = true;
            dispose();
        } catch (ValidasiException e) {
            Gaya.error(lblErrNominal, e.getPesan("nominal") != null ? e.getPesan("nominal") : e.getMessage());
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
        pnlIsi = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlTagihan = new javax.swing.JPanel();
        lblTagihanTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblNominal = new javax.swing.JLabel();
        txtNominal = new javax.swing.JTextField();
        lblErrNominal = new javax.swing.JLabel();
        lblPetunjuk = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnNanti = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Pembayaran");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 20, 28));
        pnlLatar.setLayout(new java.awt.BorderLayout());
        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlIsi.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlIsi.add(lblSub, gridBagConstraints);
        pnlTagihan.setBackground(new java.awt.Color(245, 247, 251));
        pnlTagihan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        pnlTagihan.setLayout(new java.awt.GridBagLayout());
        lblTagihanTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTagihanTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblTagihanTeks.setText("Total tagihan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTagihan.add(lblTagihanTeks, gridBagConstraints);
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(31, 94, 219));
        lblTotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTagihan.add(lblTotal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 18, 0);
        pnlIsi.add(pnlTagihan, gridBagConstraints);

        lblNominal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(52, 64, 84));
        lblNominal.setText("Nominal pembayaran (Rp)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlIsi.add(lblNominal, gridBagConstraints);
        txtNominal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtNominal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNominal.setForeground(new java.awt.Color(16, 24, 40));
        txtNominal.setPreferredSize(new java.awt.Dimension(360, 38));
        txtNominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNominalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlIsi.add(txtNominal, gridBagConstraints);
        lblErrNominal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrNominal.setForeground(new java.awt.Color(217, 45, 32));
        lblErrNominal.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrNominal.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlIsi.add(lblErrNominal, gridBagConstraints);
        lblPetunjuk.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblPetunjuk.setForeground(new java.awt.Color(102, 112, 133));
        lblPetunjuk.setText("Masukkan nominal sesuai total tagihan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlIsi.add(lblPetunjuk, gridBagConstraints);
        pnlLatar.add(pnlIsi, java.awt.BorderLayout.CENTER);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 0, 0, 0));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnNanti.setBackground(new java.awt.Color(255, 255, 255));
        btnNanti.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnNanti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNanti.setFocusPainted(false);
        btnNanti.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNanti.setForeground(new java.awt.Color(16, 24, 40));
        btnNanti.setPreferredSize(new java.awt.Dimension(120, 36));
        btnNanti.setText("Bayar Nanti");
        btnNanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNantiActionPerformed(evt);
            }
        });
        pnlTombol.add(btnNanti);
        btnBayar.setBackground(new java.awt.Color(31, 94, 219));
        btnBayar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayar.setFocusPainted(false);
        btnBayar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setPreferredSize(new java.awt.Dimension(120, 36));
        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBayar);
        pnlLatar.add(pnlTombol, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNominalActionPerformed
        prosesBayar();
    }//GEN-LAST:event_txtNominalActionPerformed

    private void btnNantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNantiActionPerformed
        dispose();
    }//GEN-LAST:event_btnNantiActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        prosesBayar();
    }//GEN-LAST:event_btnBayarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnNanti;
    private javax.swing.JLabel lblErrNominal;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblPetunjuk;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTagihanTeks;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTagihan;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JTextField txtNominal;
    // End of variables declaration//GEN-END:variables
}
