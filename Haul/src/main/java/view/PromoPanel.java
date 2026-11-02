package view;

import controller.PromoController;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Promo;

/**
 * Flowchart Kelola Promo: 1. Lihat  2. Tambah  3. Ubah  4. Hapus.
 * Tampilan dari Form Builder (PromoPanel.form); form Tambah/Ubah ada di DialogPromo.
 */
public class PromoPanel extends javax.swing.JPanel implements Halaman {

    private final PromoController controller = new PromoController();
    private final PagerPanel pager = new PagerPanel(8, "promo");
    private final DefaultTableModel model;
    private List<Promo> data = new ArrayList<>();
    private List<Promo> tampil = new ArrayList<>();

    public PromoPanel() {
        initComponents();
        model = (DefaultTableModel) tblPromo.getModel();
        UI.tabel(tblPromo, scrPromo);
        UI.kolomBadge(tblPromo, 5);
        tblPromo.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblPromo.getColumnModel().getColumn(6).setPreferredWidth(230);
        new KolomAksi(tblPromo, 6,
                new KolomAksi.Tombol("Lihat", UI.Jenis.SEKUNDER, b -> true, this::lihat),
                new KolomAksi.Tombol("Ubah", UI.Jenis.SEKUNDER, b -> true, this::ubah),
                new KolomAksi.Tombol("Hapus", UI.Jenis.BAHAYA, b -> true, this::hapus));
        UI.input(txtCari, "Cari kode voucher...");
        UI.combo(cmbStatus);
        UI.sekunder(btnTampilkan);
        UI.primer(btnTambah);
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
            data = controller.cari(txtCari.getText(), (String) cmbStatus.getSelectedItem());
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data promo.");
        });
    }

    private void tampilkanTabel() {
        if (tblPromo.isEditing()) tblPromo.getCellEditor().cancelCellEditing();
        tampil = new ArrayList<>(pager.potong(data));
        model.setRowCount(0);
        for (Promo p : tampil) {
            model.addRow(new Object[]{p.getKode(), p.getLabelJenis(), p.getTeksDiskon(), p.getMinimalBarang() + " barang",
                    UI.tanggal(p.getMulai()) + " – " + UI.tanggal(p.getSelesai()), p.getStatus(), ""});
        }
        UI.sesuaikanTinggi(scrPromo, tblPromo);
    }

    private void lihat(int baris) {
        Promo p = tampil.get(baris);
        UI.konfirmasi(this, "Detail Promo", "<b>" + p.getKode() + " • " + p.getStatus() + "</b><br><br>"
                + "Jenis: " + p.getLabelJenis() + "<br>Potongan: " + p.getTeksDiskon()
                + "<br>Minimal pembelian: " + p.getMinimalBarang() + " barang<br>Periode: "
                + UI.tanggal(p.getMulai()) + " – " + UI.tanggal(p.getSelesai())
                + "<br><br>Satu voucher per pesanan dan tidak dapat digabungkan.", "Tutup", false);
    }

    /** Form Tambah (lama = null) / Ubah promo. */
    private void bukaForm(Promo lama) {
        DialogPromo d = new DialogPromo(SwingUtilities.getWindowAncestor(this), lama);
        d.setVisible(true);
        if (d.isBerhasil()) {
            muat();
            sukses(lama == null ? "Promo berhasil disimpan" : "Promo berhasil diubah");
        }
    }

    private void ubah(int baris) {
        bukaForm(tampil.get(baris));
    }

    private void hapus(int baris) {
        Promo p = tampil.get(baris);
        if (UI.konfirmasi(this, "Hapus promo?", "Hapus voucher <b>" + p.getKode()
                + "</b>? Pelanggan tidak dapat menggunakan voucher ini setelah dihapus.", "Ya, Hapus", true)
                && coba(() -> controller.hapus(p.getId()))) {
            muat();
            sukses("Promo berhasil dihapus");
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
        pnlAksiKepala = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
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
        scrPromo = new javax.swing.JScrollPane();
        tblPromo = new javax.swing.JTable();
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
        lblJudul.setText("Kelola promo");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Atur voucher dan periode penawaran untuk pelanggan.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);
        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);
        pnlAksiKepala.setOpaque(false);
        pnlAksiKepala.setLayout(new java.awt.BorderLayout());
        btnTambah.setBackground(new java.awt.Color(33, 97, 213));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+ Tambah Promo");
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        pnlAksiKepala.add(btnTambah, java.awt.BorderLayout.NORTH);
        pnlKepala.add(pnlAksiKepala, java.awt.BorderLayout.EAST);
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
        pnlStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new java.awt.GridBagLayout());
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatus.setForeground(new java.awt.Color(23, 43, 77));
        lblStatus.setText("Filter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlStatus.add(lblStatus, gridBagConstraints);
        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cmbStatus.setForeground(new java.awt.Color(23, 43, 77));
        cmbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua promo", "Aktif", "Terjadwal", "Berakhir" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(190, 38));
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
        scrPromo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)));
        scrPromo.setPreferredSize(new java.awt.Dimension(700, 240));
        scrPromo.setBackground(new java.awt.Color(255, 255, 255));
        tblPromo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode voucher", "Jenis", "Potongan", "Min. barang", "Periode", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPromo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tblPromo.setRowHeight(44);
        scrPromo.setViewportView(tblPromo);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrPromo, gridBagConstraints);
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
        lblInfo.setText("Satu voucher per pesanan. Promo berlaku sesuai periode dan minimal jumlah barang.");
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

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        bukaForm(null);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        pager.reset();
        muat();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAksiKepala;
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
    private javax.swing.JScrollPane scrPromo;
    private javax.swing.JTable tblPromo;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
