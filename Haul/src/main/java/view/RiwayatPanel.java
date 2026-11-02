package view;

import controller.PesananController;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/**
 * Flowchart Riwayat Pesanan:
 *  ada pesanan? -> tampilkan riwayat -> ingin membatalkan? / ingin melacak? -> halaman Lacak.
 * Tampilan dari Form Builder (RiwayatPanel.form); tombol Batalkan/Lacak per baris lewat KolomAksi.
 */
public class RiwayatPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final PesananController controller = new PesananController();
    private final PagerPanel pager = new PagerPanel(8, "pesanan");
    private final DefaultTableModel model;
    private List<Pesanan> data = new ArrayList<>();
    private List<Pesanan> tampil = new ArrayList<>();   // baris yang sedang tampil di tabel

    public RiwayatPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        model = (DefaultTableModel) tblRiwayat.getModel();
        UI.tabel(tblRiwayat, scrRiwayat);
        UI.kolomBadge(tblRiwayat, 4);
        tblRiwayat.getColumnModel().getColumn(2).setPreferredWidth(260);
        tblRiwayat.getColumnModel().getColumn(5).setPreferredWidth(150);
        new KolomAksi(tblRiwayat, 5,
                new KolomAksi.Tombol("Batalkan", UI.Jenis.SEKUNDER,
                        b -> b < tampil.size() && tampil.get(b).bisaDibatalkan(), this::batalkan),
                new KolomAksi.Tombol(b -> "Lacak",
                        b -> tampil.get(b).getTahap() == Pesanan.Tahap.DIKIRIM ? UI.Jenis.PRIMER : UI.Jenis.SEKUNDER,
                        b -> b < tampil.size() && (tampil.get(b).getTahap() == Pesanan.Tahap.DIKIRIM
                                || tampil.get(b).getTahap() == Pesanan.Tahap.SELESAI), this::lacak));
        UI.input(txtCari, "Cari nomor pesanan...");
        UI.combo(cmbStatus);
        UI.sekunder(btnTampilkan);
        UI.sorotan(pnlInfo, 10, 14, 10, 14);
        UI.info(pnlInfo, lblInfo, "Pesanan hanya dapat dibatalkan sebelum diproses. Gunakan Lacak untuk pesanan yang sedang dikirim.");
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanTabel);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        coba(() -> {
            data = controller.riwayat(txtCari.getText(), tahapDari(cmbStatus.getSelectedItem()));
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data pesanan. Mulai belanja dari menu Katalog.");
        });
    }

    private void tampilkanTabel() {
        if (tblRiwayat.isEditing()) tblRiwayat.getCellEditor().cancelCellEditing();
        tampil = new ArrayList<>(pager.potong(data));
        model.setRowCount(0);
        for (Pesanan p : tampil) {
            model.addRow(new Object[]{p.getNomor(), UI.tanggal(p.getTanggal().toLocalDate()), p.getNamaProduk(),
                    UI.rupiah(p.getTotalHarga()), p.getTahap().toString(), ""});
        }
        UI.sesuaikanTinggi(scrRiwayat, tblRiwayat);
    }

    /** Cari Tahap dari label dropdown ("Semua status" = null). */
    private static Pesanan.Tahap tahapDari(Object label) {
        for (Pesanan.Tahap t : Pesanan.Tahap.values()) if (t.toString().equals(label)) return t;
        return null;
    }

    private void batalkan(int baris) {
        Pesanan p = tampil.get(baris);
        boolean ya = UI.konfirmasi(this, "Batalkan pesanan?", "<b>" + p.getNomor() + "</b><br><br>Pesanan senilai "
                + UI.rupiah(p.getTotalHarga()) + " belum dibayar. Setelah dibatalkan, pesanan tidak dapat dilanjutkan.",
                "Ya, Batalkan", true);
        if (ya && coba(() -> controller.batalkan(p))) {
            muat();
            sukses("Pesanan " + p.getNomor() + " berhasil dibatalkan.");
        }
    }

    private void lacak(int baris) {
        jendela.lacak.setPesanan(tampil.get(baris));
        jendela.buka(jendela.lacak);
    }

    // ---------- pesan di dalam halaman (tanpa pop-up) ----------
    private void sukses(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.SUKSES, teks); }

    private void galat(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.GALAT, teks); }

    private void info(String teks) { UI.pesan(pnlPesan, lblPesan, UI.Pesan.INFO, teks); }

    @Override
    public void bersihkanPesan() { UI.sembunyi(pnlPesan); }

    /** Jalankan aksi; jika gagal, tampilkan pesannya. Mengembalikan true jika berhasil. */
    private boolean coba(Runnable aksi) {
        try {
            aksi.run();
            return true;
        } catch (RuntimeException e) {
            galat(e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlAtas = new javax.swing.JPanel();
        pnlKepala = new javax.swing.JPanel();
        pnlJudul = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSubjudul = new javax.swing.JLabel();
        pnlPesan = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlKonten = new javax.swing.JPanel();
        pnlFilter = new javax.swing.JPanel();
        pnlCari = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        pnlStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        scrRiwayat = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        pnlPager = new javax.swing.JPanel();
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(243, 246, 250));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 32, 32, 32));
        setLayout(new java.awt.BorderLayout());

        pnlAtas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnlAtas.setOpaque(false);
        pnlAtas.setLayout(new java.awt.BorderLayout());

        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.BorderLayout(12, 0));

        pnlJudul.setOpaque(false);
        pnlJudul.setLayout(new java.awt.BorderLayout(0, 6));

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Riwayat pesanan");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);

        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Pantau pembayaran dan pengiriman semua pesanan Anda.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);

        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);

        pnlAtas.add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlPesan.setBackground(new java.awt.Color(234, 246, 236));
        pnlPesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesan.setLayout(new java.awt.BorderLayout());

        lblPesan.setForeground(new java.awt.Color(46, 125, 50));
        lblPesan.setText("Pesan");
        pnlPesan.add(lblPesan, java.awt.BorderLayout.CENTER);

        pnlAtas.add(pnlPesan, java.awt.BorderLayout.CENTER);

        add(pnlAtas, java.awt.BorderLayout.NORTH);

        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.BorderLayout());

        pnlKonten.setOpaque(false);
        pnlKonten.setLayout(new java.awt.GridBagLayout());

        pnlFilter.setOpaque(false);
        pnlFilter.setLayout(new java.awt.GridBagLayout());

        pnlCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlCari.setOpaque(false);
        pnlCari.setLayout(new java.awt.GridBagLayout());

        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCari.setForeground(new java.awt.Color(23, 43, 77));
        lblCari.setText("Pencarian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlCari.add(lblCari, gridBagConstraints);

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(23, 43, 77));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtCari.setPreferredSize(new java.awt.Dimension(320, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlCari.add(txtCari, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        pnlFilter.add(pnlCari, gridBagConstraints);

        pnlStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new java.awt.GridBagLayout());

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(23, 43, 77));
        lblStatus.setText("Filter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlStatus.add(lblStatus, gridBagConstraints);

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbStatus.setForeground(new java.awt.Color(23, 43, 77));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua status", "Menunggu Pembayaran", "Diproses", "Dikirim", "Selesai" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(200, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlStatus.add(cmbStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(pnlStatus, gridBagConstraints);

        btnTampilkan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTampilkan.setForeground(new java.awt.Color(23, 43, 77));
        btnTampilkan.setText("Tampilkan");
        btnTampilkan.setFocusPainted(false);
        btnTampilkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilkanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(btnTampilkan, gridBagConstraints);

        pnlIsiFilter.setPreferredSize(new java.awt.Dimension(1, 1));
        pnlIsiFilter.setOpaque(false);
        pnlIsiFilter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFilter.add(pnlIsiFilter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlFilter, gridBagConstraints);

        scrRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrRiwayat.setPreferredSize(new java.awt.Dimension(700, 200));
        scrRiwayat.setBackground(new java.awt.Color(255, 255, 255));

        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor pesanan", "Tanggal", "Produk", "Total", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRiwayat.setRowHeight(44);
        scrRiwayat.setViewportView(tblRiwayat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrRiwayat, gridBagConstraints);

        pnlPager.setOpaque(false);
        pnlPager.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlPager, gridBagConstraints);

        pnlInfo.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfo.setLayout(new java.awt.BorderLayout());

        lblInfo.setForeground(new java.awt.Color(33, 97, 213));
        lblInfo.setText("Pesanan hanya dapat dibatalkan sebelum diproses. Gunakan Lacak untuk pesanan yang sedang dikirim.");
        pnlInfo.add(lblInfo, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlInfo, gridBagConstraints);

        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);

        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        pager.reset();
        muat();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiFilter;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlPager;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JScrollPane scrRiwayat;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
