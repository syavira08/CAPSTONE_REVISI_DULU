package view;

import controller.PesananController;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/**
 * Flowchart Kelola Pesanan: 1. Lihat pesanan  2. Ubah status pesanan.
 * Alur status: Menunggu Pembayaran -> Diproses (pembayaran diverifikasi) -> Dikirim -> Selesai, atau Dibatalkan.
 * Tampilan dari Form Builder (PesananAdminPanel.form); dropdown status + tombol Simpan ada di tiap baris.
 */
public class PesananAdminPanel extends javax.swing.JPanel implements Halaman {

    private final PesananController controller = new PesananController();
    private final PagerPanel pager = new PagerPanel(8, "pesanan");
    private final DefaultTableModel model;
    private List<Pesanan> data = new ArrayList<>();
    private List<Pesanan> tampil = new ArrayList<>();

    public PesananAdminPanel() {
        initComponents();
        model = (DefaultTableModel) tblPesanan.getModel();
        UI.tabel(tblPesanan, scrPesanan);
        UI.kolomBadge(tblPesanan, 4);
        tblPesanan.getColumnModel().getColumn(5).setPreferredWidth(190);
        tblPesanan.getColumnModel().getColumn(6).setPreferredWidth(100);
        KolomCombo.pasang(tblPesanan, 5, "Menunggu Pembayaran", "Diproses", "Dikirim", "Selesai", "Dibatalkan");
        new KolomAksi(tblPesanan, 6, new KolomAksi.Tombol("Simpan", UI.Jenis.PRIMER, b -> true, this::simpan));
        UI.input(txtCari, "Cari nomor pesanan atau pelanggan...");
        UI.combo(cmbFilter);
        UI.sekunder(btnTampilkan);
        UI.sorotan(pnlInfo, 10, 14, 10, 14);
        UI.info(pnlInfo, lblInfo, lblInfo.getText());
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanTabel);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        coba(() -> {
            data = controller.cari(txtCari.getText(), tahapDari(cmbFilter.getSelectedItem()));
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data pesanan.");
        });
    }

    /** Cari Tahap dari label dropdown ("Semua status" = null). */
    private static Pesanan.Tahap tahapDari(Object label) {
        for (Pesanan.Tahap t : Pesanan.Tahap.values()) if (t.toString().equals(label)) return t;
        return null;
    }

    private void tampilkanTabel() {
        if (tblPesanan.isEditing()) tblPesanan.getCellEditor().cancelCellEditing();
        tampil = new ArrayList<>(pager.potong(data));
        model.setRowCount(0);
        for (Pesanan p : tampil) {
            String bayar = p.getMetodeBayar() == null ? "-" : p.getMetodeBayar().toUpperCase().contains("WALLET") ? "E-Wallet" : "Transfer Bank";
            model.addRow(new Object[]{p.getNomor(), p.getPelanggan().getNama(), UI.rupiah(p.getTotalHarga()), bayar,
                    p.getTahap().toString(), p.getTahap().toString(), ""});
        }
        UI.sesuaikanTinggi(scrPesanan, tblPesanan);
    }

    private void simpan(int baris) {
        Pesanan p = tampil.get(baris);
        Pesanan.Tahap tujuan = tahapDari(model.getValueAt(baris, 5));
        if (tujuan == Pesanan.Tahap.DIBATALKAN && !UI.konfirmasi(this, "Batalkan pesanan?", "<b>" + p.getNomor()
                + "</b> akan dibatalkan dan stok produk dikembalikan.", "Ya, Batalkan", true)) {
            return;
        }
        if (coba(() -> controller.ubahStatus(p, tujuan))) {
            muat();
            sukses("Status pesanan berhasil diubah");
        }
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
        pnlSaring = new javax.swing.JPanel();
        lblSaring = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        scrPesanan = new javax.swing.JScrollPane();
        tblPesanan = new javax.swing.JTable();
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
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Kelola pesanan");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Pantau transaksi dan perbarui status pemrosesan pesanan.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);
        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);
        pnlAtas.add(pnlKepala, java.awt.BorderLayout.NORTH);
        pnlPesan.setBackground(new java.awt.Color(234, 246, 236));
        pnlPesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesan.setLayout(new java.awt.BorderLayout());
        lblPesan.setFont(new java.awt.Font("Segoe UI", 0, 12));
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
        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblCari.setForeground(new java.awt.Color(23, 43, 77));
        lblCari.setText("Pencarian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlCari.add(lblCari, gridBagConstraints);
        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtCari.setForeground(new java.awt.Color(23, 43, 77));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
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
        pnlSaring.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlSaring.setOpaque(false);
        pnlSaring.setLayout(new java.awt.GridBagLayout());
        lblSaring.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSaring.setForeground(new java.awt.Color(23, 43, 77));
        lblSaring.setText("Filter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSaring.add(lblSaring, gridBagConstraints);
        cmbFilter.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cmbFilter.setForeground(new java.awt.Color(23, 43, 77));
        cmbFilter.setBackground(new java.awt.Color(255, 255, 255));
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua status", "Menunggu Pembayaran", "Diproses", "Dikirim", "Selesai" }));
        cmbFilter.setPreferredSize(new java.awt.Dimension(200, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlSaring.add(cmbFilter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(pnlSaring, gridBagConstraints);
        btnTampilkan.setBackground(new java.awt.Color(255, 255, 255));
        btnTampilkan.setFont(new java.awt.Font("Segoe UI", 1, 13));
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
        scrPesanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)));
        scrPesanan.setPreferredSize(new java.awt.Dimension(700, 300));
        scrPesanan.setBackground(new java.awt.Color(255, 255, 255));
        tblPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor pesanan", "Pelanggan", "Total", "Pembayaran", "Status saat ini", "Ubah status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPesanan.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tblPesanan.setRowHeight(44);
        scrPesanan.setViewportView(tblPesanan);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrPesanan, gridBagConstraints);
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
        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblInfo.setForeground(new java.awt.Color(33, 97, 213));
        lblInfo.setText("Pembayaran terverifikasi \u2192 Diproses \u2192 Dikirim \u2192 Selesai. Pesanan dibatalkan tidak dapat diproses kembali. Status Dikirim membutuhkan nomor resi dari menu Pengiriman.");
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
    private javax.swing.JComboBox<String> cmbFilter;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSaring;
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
    private javax.swing.JPanel pnlSaring;
    private javax.swing.JScrollPane scrPesanan;
    private javax.swing.JTable tblPesanan;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
