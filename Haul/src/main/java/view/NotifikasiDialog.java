package view;

/** Notifikasi singkat (pop up), mis. "Barang dimasukkan ke keranjang". */
public class NotifikasiDialog extends javax.swing.JDialog {

    public NotifikasiDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.MODELESS);
        initComponents();
    }

    /** Notifikasi berhasil, muncul di bagian atas tengah jendela lalu hilang sendiri. */
    public static void tampil(java.awt.Component asal, String pesan, String detail) {
        tampil(asal, pesan, detail, true);
    }

    public static void tampil(java.awt.Component asal, String pesan, String detail, boolean berhasil) {
        java.awt.Window w = Gaya.jendela(asal);
        NotifikasiDialog d = new NotifikasiDialog(w);
        d.lblPesan.setText(pesan);
        d.lblDetail.setText(detail);
        d.lblDetail.setVisible(detail != null && !detail.isBlank());
        if (!berhasil) {
            d.lblIkon.setIcon(Gaya.ikon("notif_info"));
            d.pnlLatar.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 215, 254)),
                    javax.swing.BorderFactory.createEmptyBorder(12, 16, 12, 22)));
        }
        d.pack();
        if (w != null) {
            d.setLocation(w.getX() + (w.getWidth() - d.getWidth()) / 2, w.getY() + 80);
        }
        d.setVisible(true);
        javax.swing.Timer t = new javax.swing.Timer(2200, e -> d.dispose());
        t.setRepeats(false);
        t.start();
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlLatar = new javax.swing.JPanel();
        lblIkon = new javax.swing.JLabel();
        pnlTeks = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        lblDetail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusableWindowState(false);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(183, 223, 201)), javax.swing.BorderFactory.createEmptyBorder(12, 16, 12, 22)));
        pnlLatar.setLayout(new java.awt.BorderLayout(12, 0));
        lblIkon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblIkon.setForeground(new java.awt.Color(16, 24, 40));
        lblIkon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/notif_sukses.png"))); // NOI18N
        lblIkon.setText("");
        pnlLatar.add(lblIkon, java.awt.BorderLayout.WEST);
        pnlTeks.setBackground(new java.awt.Color(255, 255, 255));
        pnlTeks.setOpaque(false);
        pnlTeks.setLayout(new java.awt.GridBagLayout());
        lblPesan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPesan.setForeground(new java.awt.Color(16, 24, 40));
        lblPesan.setText("Barang dimasukkan ke keranjang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTeks.add(lblPesan, gridBagConstraints);
        lblDetail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDetail.setForeground(new java.awt.Color(102, 112, 133));
        lblDetail.setText("Detail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTeks.add(lblDetail, gridBagConstraints);
        pnlLatar.add(pnlTeks, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDetail;
    private javax.swing.JLabel lblIkon;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTeks;
    // End of variables declaration//GEN-END:variables
}
