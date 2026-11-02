package view;

import java.awt.Component;
import java.awt.Window;
import javax.swing.SwingUtilities;

/** Dialog konfirmasi bergaya HAULECTRA (Hapus? Batalkan? Detail?). Pakai lewat UI.konfirmasi(...). */
public class DialogKonfirmasi extends javax.swing.JDialog {

    private boolean ya = false;

    private DialogKonfirmasi(Window induk, String judul, String pesan, String labelYa, boolean bahaya) {
        super(induk, ModalityType.APPLICATION_MODAL);
        initComponents();
        setTitle(judul);
        lblJudul.setText(judul);
        lblPesan.setText(UI.bungkus(pesan, 380));
        btnYa.setText(labelYa);
        UI.sekunder(btnBatal);
        if (bahaya) UI.bahaya(btnYa); else UI.primer(btnYa);
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(induk);
    }

    /** Tampilkan dialog; true jika tombol aksi (bukan Batal) ditekan. */
    public static boolean tampilkan(Component induk, String judul, String pesan, String labelYa, boolean bahaya) {
        DialogKonfirmasi d = new DialogKonfirmasi(SwingUtilities.getWindowAncestor(induk), judul, pesan, labelYa, bahaya);
        d.setVisible(true);
        return d.ya;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlKaki = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnYa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Konfirmasi");
        setModal(true);
        setResizable(false);

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        pnlKepala.setLayout(new java.awt.BorderLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Hapus produk?");
        pnlKepala.add(lblJudul, java.awt.BorderLayout.CENTER);
        getContentPane().add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 14, 20));
        pnlIsi.setPreferredSize(new java.awt.Dimension(440, 90));
        pnlIsi.setLayout(new java.awt.BorderLayout());
        lblPesan.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPesan.setForeground(new java.awt.Color(94, 108, 132));
        lblPesan.setText("Pesan");
        pnlIsi.add(lblPesan, java.awt.BorderLayout.CENTER);
        getContentPane().add(pnlIsi, java.awt.BorderLayout.CENTER);

        pnlKaki.setBackground(new java.awt.Color(247, 249, 252));
        pnlKaki.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 20, 14, 12));
        pnlKaki.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnBatal.setBackground(new java.awt.Color(255, 255, 255));
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnBatal.setForeground(new java.awt.Color(23, 43, 77));
        btnBatal.setText("Batal");
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlKaki.add(btnBatal);
        btnYa.setBackground(new java.awt.Color(192, 52, 63));
        btnYa.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnYa.setForeground(new java.awt.Color(255, 255, 255));
        btnYa.setText("Ya, Hapus");
        btnYa.setFocusPainted(false);
        btnYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYaActionPerformed(evt);
            }
        });
        pnlKaki.add(btnYa);
        getContentPane().add(pnlKaki, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYaActionPerformed
        ya = true;
        dispose();
    }//GEN-LAST:event_btnYaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnYa;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlKaki;
    private javax.swing.JPanel pnlKepala;
    // End of variables declaration//GEN-END:variables
}
