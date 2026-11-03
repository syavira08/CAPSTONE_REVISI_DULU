package view;

import controller.PesananController;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/** Cetak laporan pesanan: admin memilih status dan kolom yang dicetak. */
public class CetakLaporanDialog extends javax.swing.JDialog {

    private final PesananController controller = new PesananController();

    public CetakLaporanDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    public static void tampil(java.awt.Component asal) {
        CetakLaporanDialog d = new CetakLaporanDialog(Gaya.jendela(asal));
        d.pack();
        d.setLocationRelativeTo(asal);
        d.setVisible(true);
    }

    /** Nilai satu kolom laporan untuk satu pesanan. */
    private Object nilai(Pesanan p, String kolom) {
        return switch (kolom) {
            case "No. Pesanan" -> p.getNomor();
            case "Tanggal" -> Gaya.tanggal(p.getTanggal());
            case "Pelanggan" -> p.getPelanggan().getNama();
            case "Produk" -> p.getNamaProduk();
            case "Total" -> Gaya.rupiah(p.getTotalHarga());
            case "Metode Bayar" -> Gaya.metode(p.getMetodeBayar());
            case "Status" -> p.getTahap().toString();
            default -> p.getNomorResi() == null ? "-" : p.getNomorResi();
        };
    }

    private void cetak() {
        List<String> kolom = new ArrayList<>();
        for (javax.swing.JCheckBox c : new javax.swing.JCheckBox[]{chkNomor, chkTanggal, chkPelanggan, chkProduk,
            chkTotal, chkMetode, chkStatus, chkResi}) {
            if (c.isSelected()) kolom.add(c.getText());
        }
        if (kolom.isEmpty()) {
            Gaya.error(lblErr, "Pilih minimal satu data yang dicetak.");
            return;
        }
        Pesanan.Tahap tahap = null;
        for (Pesanan.Tahap t : Pesanan.Tahap.values()) {
            if (t.toString().equals(cmbStatus.getSelectedItem())) tahap = t;
        }
        List<Pesanan> data = controller.cari("", tahap);
        if (data.isEmpty()) {
            Gaya.error(lblErr, "Tidak ada pesanan dengan status ini.");
            return;
        }
        Gaya.error(lblErr, null);

        DefaultTableModel model = new DefaultTableModel(kolom.toArray(), 0);
        for (Pesanan p : data) model.addRow(kolom.stream().map(k -> nilai(p, k)).toArray());
        javax.swing.JTable tabel = new javax.swing.JTable(model);
        tabel.setRowHeight(24);
        tabel.setSize(tabel.getPreferredSize());
        tabel.getTableHeader().setSize(tabel.getTableHeader().getPreferredSize());
        String judul = "Laporan Pesanan HAULECTRA - " + cmbStatus.getSelectedItem();
        try {
            if (tabel.print(javax.swing.JTable.PrintMode.FIT_WIDTH, new MessageFormat(judul),
                    new MessageFormat("Halaman {0}"))) {
                dispose();
            }
        } catch (PrinterException e) {
            KonfirmasiDialog.gagal(this, "Laporan gagal dicetak. " + e.getMessage());
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
        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        lblKolom = new javax.swing.JLabel();
        pnlKolom = new javax.swing.JPanel();
        chkNomor = new javax.swing.JCheckBox();
        chkTanggal = new javax.swing.JCheckBox();
        chkPelanggan = new javax.swing.JCheckBox();
        chkProduk = new javax.swing.JCheckBox();
        chkTotal = new javax.swing.JCheckBox();
        chkMetode = new javax.swing.JCheckBox();
        chkStatus = new javax.swing.JCheckBox();
        chkResi = new javax.swing.JCheckBox();
        lblErr = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cetak Laporan");
        setResizable(false);

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 20, 28));
        pnlLatar.setLayout(new java.awt.BorderLayout());

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 18, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Cetak Laporan Pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Tentukan status dan data apa saja yang ingin dicetak.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);

        pnlLatar.add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.GridBagLayout());

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(52, 64, 84));
        lblStatus.setText("Status pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlIsi.add(lblStatus, gridBagConstraints);

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbStatus.setForeground(new java.awt.Color(16, 24, 40));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua status", "Menunggu Pembayaran", "Diproses", "Dikirim", "Selesai" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(360, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlIsi.add(cmbStatus, gridBagConstraints);

        lblKolom.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKolom.setForeground(new java.awt.Color(52, 64, 84));
        lblKolom.setText("Data yang dicetak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlIsi.add(lblKolom, gridBagConstraints);

        pnlKolom.setBackground(new java.awt.Color(255, 255, 255));
        pnlKolom.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(12, 14, 12, 14)));
        pnlKolom.setLayout(new java.awt.GridLayout(0, 2, 12, 8));

        chkNomor.setBackground(new java.awt.Color(255, 255, 255));
        chkNomor.setFocusPainted(false);
        chkNomor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkNomor.setForeground(new java.awt.Color(16, 24, 40));
        chkNomor.setSelected(true);
        chkNomor.setText("No. Pesanan");
        pnlKolom.add(chkNomor);

        chkTanggal.setBackground(new java.awt.Color(255, 255, 255));
        chkTanggal.setFocusPainted(false);
        chkTanggal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkTanggal.setForeground(new java.awt.Color(16, 24, 40));
        chkTanggal.setSelected(true);
        chkTanggal.setText("Tanggal");
        pnlKolom.add(chkTanggal);

        chkPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        chkPelanggan.setFocusPainted(false);
        chkPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkPelanggan.setForeground(new java.awt.Color(16, 24, 40));
        chkPelanggan.setSelected(true);
        chkPelanggan.setText("Pelanggan");
        pnlKolom.add(chkPelanggan);

        chkProduk.setBackground(new java.awt.Color(255, 255, 255));
        chkProduk.setFocusPainted(false);
        chkProduk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkProduk.setForeground(new java.awt.Color(16, 24, 40));
        chkProduk.setText("Produk");
        pnlKolom.add(chkProduk);

        chkTotal.setBackground(new java.awt.Color(255, 255, 255));
        chkTotal.setFocusPainted(false);
        chkTotal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkTotal.setForeground(new java.awt.Color(16, 24, 40));
        chkTotal.setSelected(true);
        chkTotal.setText("Total");
        pnlKolom.add(chkTotal);

        chkMetode.setBackground(new java.awt.Color(255, 255, 255));
        chkMetode.setFocusPainted(false);
        chkMetode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkMetode.setForeground(new java.awt.Color(16, 24, 40));
        chkMetode.setText("Metode Bayar");
        pnlKolom.add(chkMetode);

        chkStatus.setBackground(new java.awt.Color(255, 255, 255));
        chkStatus.setFocusPainted(false);
        chkStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkStatus.setForeground(new java.awt.Color(16, 24, 40));
        chkStatus.setSelected(true);
        chkStatus.setText("Status");
        pnlKolom.add(chkStatus);

        chkResi.setBackground(new java.awt.Color(255, 255, 255));
        chkResi.setFocusPainted(false);
        chkResi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        chkResi.setForeground(new java.awt.Color(16, 24, 40));
        chkResi.setSelected(true);
        chkResi.setText("No. Resi");
        pnlKolom.add(chkResi);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlIsi.add(pnlKolom, gridBagConstraints);

        lblErr.setForeground(new java.awt.Color(217, 45, 32));
        lblErr.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErr.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlIsi.add(lblErr, gridBagConstraints);

        pnlLatar.add(pnlIsi, java.awt.BorderLayout.CENTER);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(16, 24, 40));
        btnBatal.setText("Batal");
        btnBatal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.setPreferredSize(new java.awt.Dimension(100, 36));
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBatal);

        btnCetak.setBackground(new java.awt.Color(31, 94, 219));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/cetak_putih.png"))); // NOI18N
        btnCetak.setText("Cetak");
        btnCetak.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.setFocusPainted(false);
        btnCetak.setIconTextGap(8);
        btnCetak.setPreferredSize(new java.awt.Dimension(120, 36));
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        pnlTombol.add(btnCetak);

        pnlLatar.add(pnlTombol, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        cetak();
    }//GEN-LAST:event_btnCetakActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCetak;
    private javax.swing.JCheckBox chkMetode;
    private javax.swing.JCheckBox chkNomor;
    private javax.swing.JCheckBox chkPelanggan;
    private javax.swing.JCheckBox chkProduk;
    private javax.swing.JCheckBox chkResi;
    private javax.swing.JCheckBox chkStatus;
    private javax.swing.JCheckBox chkTanggal;
    private javax.swing.JCheckBox chkTotal;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel lblErr;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKolom;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKolom;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTombol;
    // End of variables declaration//GEN-END:variables
}
