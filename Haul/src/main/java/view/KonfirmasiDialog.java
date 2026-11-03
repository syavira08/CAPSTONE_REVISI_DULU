package view;

/** Dialog konfirmasi / pemberitahuan dengan tampilan seragam (pengganti JOptionPane). */
public class KonfirmasiDialog extends javax.swing.JDialog {

    private boolean setuju;

    public KonfirmasiDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    /**
     * Tampilkan pertanyaan ya/tidak.
     * @param ikon  nama ikon di /view/ikon, mis. "dialog_hapus"
     * @param bahaya true = tombol "ya" berwarna merah
     */
    public static boolean tanya(java.awt.Component asal, String judul, String pesan, String teksYa,
                                String ikon, boolean bahaya) {
        KonfirmasiDialog d = new KonfirmasiDialog(Gaya.jendela(asal));
        d.isi(judul, pesan, ikon);
        d.btnYa.setText(teksYa);
        d.btnYa.setBackground(bahaya ? Gaya.MERAH : Gaya.BIRU);
        d.tampil(asal);
        return d.setuju;
    }

    /** Pemberitahuan dengan satu tombol "Tutup". */
    public static void info(java.awt.Component asal, String judul, String pesan) {
        pesan(asal, judul, pesan, "dialog_sukses");
    }

    /** Pemberitahuan ketika proses tidak berhasil. */
    public static void gagal(java.awt.Component asal, String pesan) {
        pesan(asal, "Tidak dapat diproses", pesan, "dialog_gagal");
    }

    private static void pesan(java.awt.Component asal, String judul, String pesan, String ikon) {
        KonfirmasiDialog d = new KonfirmasiDialog(Gaya.jendela(asal));
        d.isi(judul, pesan, ikon);
        d.btnBatal.setVisible(false);
        d.btnYa.setText("Tutup");
        d.tampil(asal);
    }

    private void isi(String judul, String pesan, String ikon) {
        setTitle(judul);
        lblJudul.setText(judul);
        lblPesan.setText("<html><div width=320>" + pesan + "</div></html>");
        lblIkon.setIcon(Gaya.ikon(ikon));
    }

    private void tampil(java.awt.Component asal) {
        getRootPane().setDefaultButton(btnYa);
        pack();
        setLocationRelativeTo(asal);
        setVisible(true);
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
        lblIkon = new javax.swing.JLabel();
        lblJudul = new javax.swing.JLabel();
        lblPesan = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnYa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Konfirmasi");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 20, 24));
        pnlLatar.setLayout(new java.awt.BorderLayout());
        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.GridBagLayout());
        lblIkon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblIkon.setForeground(new java.awt.Color(16, 24, 40));
        lblIkon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/dialog_tanya.png"))); // NOI18N
        lblIkon.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlIsi.add(lblIkon, gridBagConstraints);
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Judul");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlIsi.add(lblJudul, gridBagConstraints);
        lblPesan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPesan.setForeground(new java.awt.Color(102, 112, 133));
        lblPesan.setText("Pesan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlIsi.add(lblPesan, gridBagConstraints);
        pnlLatar.add(pnlIsi, java.awt.BorderLayout.CENTER);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 0, 0, 0));
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
        btnYa.setBackground(new java.awt.Color(31, 94, 219));
        btnYa.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnYa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYa.setFocusPainted(false);
        btnYa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnYa.setForeground(new java.awt.Color(255, 255, 255));
        btnYa.setPreferredSize(new java.awt.Dimension(120, 36));
        btnYa.setText("Ya");
        btnYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYaActionPerformed(evt);
            }
        });
        pnlTombol.add(btnYa);
        pnlLatar.add(pnlTombol, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        setuju = false;
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYaActionPerformed
        setuju = true;
        dispose();
    }//GEN-LAST:event_btnYaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnYa;
    private javax.swing.JLabel lblIkon;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTombol;
    // End of variables declaration//GEN-END:variables
}
