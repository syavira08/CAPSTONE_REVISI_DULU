package view;

/** Satu langkah pada garis waktu pelacakan: titik + judul + keterangan. Langkah aktif berwarna biru. */
public class LangkahLacak extends javax.swing.JPanel {

    public LangkahLacak(String judul, String keterangan, boolean aktif) {
        initComponents();
        lblJudul.setText(judul);
        lblKeterangan.setText(keterangan);
        lblTitik.setForeground(aktif ? UI.BIRU : UI.GARIS);
        lblJudul.setForeground(aktif ? UI.BIRU : UI.TEKS);
        UI.tema(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        lblTitik = new javax.swing.JLabel();
        pnlTeks = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblKeterangan = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(10, 0));

        lblTitik.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTitik.setForeground(new java.awt.Color(220, 227, 237));
        lblTitik.setText("\u25cf");
        lblTitik.setVerticalAlignment(1);
        add(lblTitik, java.awt.BorderLayout.WEST);

        pnlTeks.setOpaque(false);
        pnlTeks.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Paket dalam pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTeks.add(lblJudul, gridBagConstraints);
        lblKeterangan.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblKeterangan.setForeground(new java.awt.Color(94, 108, 132));
        lblKeterangan.setText("Estimasi tiba 03 Nov 2026");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlTeks.add(lblKeterangan, gridBagConstraints);
        add(pnlTeks, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblTitik;
    private javax.swing.JPanel pnlTeks;
    // End of variables declaration//GEN-END:variables
}
