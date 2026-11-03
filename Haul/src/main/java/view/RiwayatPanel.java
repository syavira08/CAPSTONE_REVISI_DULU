package view;

import controller.PesananController;
import controller.ValidasiException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/** Riwayat pesanan pelanggan: bayar, batalkan, lacak/lihat detail pesanan. */
public class RiwayatPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView induk;
    private final PesananController pesanan = new PesananController();
    private List<Pesanan> data = List.of();

    public RiwayatPanel(PelangganView induk) {
        this.induk = induk;
        initComponents();
        Gaya.tabel(tblRiwayat);
        Gaya.lebarKolom(tblRiwayat, 150, 150, 240, 120, 150, 200);
        Gaya.kolomStatus(tblRiwayat, 4);
        KolomAksi.pasang(tblRiwayat, 5, b -> tombol(data.get(b)), (b, t) -> aksi(data.get(b), t));
    }

    @Override
    public void muat() {
        data = pesanan.riwayat(txtCari.getText(), tahap());
        DefaultTableModel model = (DefaultTableModel) tblRiwayat.getModel();
        model.setRowCount(0);
        for (Pesanan p : data) {
            model.addRow(new Object[]{p.getNomor(), Gaya.tanggal(p.getTanggal()), p.getNamaProduk(),
                Gaya.rupiah(p.getTotalHarga()), Gaya.statusPelanggan(p), ""});
        }
        lblJumlah.setText(data.size() + " pesanan");
    }

    private Pesanan.Tahap tahap() {
        for (Pesanan.Tahap t : Pesanan.Tahap.values()) {
            if (t.toString().equals(cmbStatus.getSelectedItem())) return t;
        }
        return null;   // Semua status
    }

    /** Tombol per status: belum bayar -> Bayar & Batalkan, sudah bayar -> Lacak, selesai -> Lihat. */
    private String[] tombol(Pesanan p) {
        return switch (p.getTahap()) {
            case MENUNGGU_PEMBAYARAN -> new String[]{"Bayar", "Batalkan"};
            case DIPROSES, DIKIRIM -> new String[]{"Lacak"};
            case SELESAI -> new String[]{"Lihat"};
        };
    }

    private void aksi(Pesanan p, String tombol) {
        switch (tombol) {
            case "Bayar" -> {
                if (PembayaranDialog.bayar(this, p)) {
                    NotifikasiDialog.tampil(this, "Pembayaran berhasil", "Pesanan " + p.getNomor() + " sedang diproses");
                }
            }
            case "Batalkan" -> {
                if (KonfirmasiDialog.tanya(this, "Batalkan pesanan", "Yakin ingin membatalkan pesanan <b>"
                        + p.getNomor() + "</b>? Stok barang akan dikembalikan.", "Batalkan", "dialog_hapus", true)) {
                    try {
                        pesanan.batalkan(p);
                        NotifikasiDialog.tampil(this, "Pesanan dibatalkan", p.getNomor());
                    } catch (ValidasiException e) {
                        KonfirmasiDialog.gagal(this, e.getMessage());
                    }
                }
            }
            default -> DetailPesananDialog.tampil(this, p, true);   // Lacak / Lihat
        }
        muat();
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlFilter = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        scrRiwayat = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        lblJumlah = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 247, 251));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 32, 24, 32));
        setLayout(new java.awt.BorderLayout());

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 16, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Riwayat Pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Bayar pesanan yang menunggu pembayaran atau lacak pesanan yang sudah dibayar.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);
        pnlFilter.setBackground(new java.awt.Color(255, 255, 255));
        pnlFilter.setOpaque(false);
        pnlFilter.setLayout(new java.awt.GridBagLayout());
        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCari.setForeground(new java.awt.Color(52, 64, 84));
        lblCari.setText("Cari");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlFilter.add(lblCari, gridBagConstraints);
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(16, 24, 40));
        txtCari.setPreferredSize(new java.awt.Dimension(300, 38));
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlFilter.add(txtCari, gridBagConstraints);
        btnCari.setBackground(new java.awt.Color(255, 255, 255));
        btnCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.setFocusPainted(false);
        btnCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCari.setForeground(new java.awt.Color(16, 24, 40));
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/cari.png"))); // NOI18N
        btnCari.setIconTextGap(8);
        btnCari.setPreferredSize(new java.awt.Dimension(96, 38));
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 0, 0);
        pnlFilter.add(btnCari, gridBagConstraints);
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(52, 64, 84));
        lblStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        pnlFilter.add(lblStatus, gridBagConstraints);
        cmbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbStatus.setForeground(new java.awt.Color(16, 24, 40));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua status", "Menunggu Pembayaran", "Dikirim", "Selesai" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(220, 38));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 16, 0, 0);
        pnlFilter.add(cmbStatus, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        pnlKepala.add(pnlFilter, gridBagConstraints);

        add(pnlKepala, java.awt.BorderLayout.NORTH);

        scrRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        scrRiwayat.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        tblRiwayat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblRiwayat.setGridColor(new java.awt.Color(228, 231, 236));
        tblRiwayat.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Pesanan", "Tanggal", "Produk", "Total", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRiwayat.setRowHeight(52);
        tblRiwayat.setSelectionBackground(new java.awt.Color(232, 240, 255));
        tblRiwayat.setSelectionForeground(new java.awt.Color(16, 24, 40));
        tblRiwayat.setShowVerticalLines(false);
        scrRiwayat.setViewportView(tblRiwayat);
        add(scrRiwayat, java.awt.BorderLayout.CENTER);
        lblJumlah.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        lblJumlah.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(102, 112, 133));
        lblJumlah.setText("0 pesanan");
        add(lblJumlah, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        muat();
    }//GEN-LAST:event_txtCariActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        muat();
    }//GEN-LAST:event_btnCariActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        muat();
    }//GEN-LAST:event_cmbStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JScrollPane scrRiwayat;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
