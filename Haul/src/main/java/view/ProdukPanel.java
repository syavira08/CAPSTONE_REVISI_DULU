package view;

import controller.ProdukController;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Produk;

/**
 * Flowchart Kelola Produk: 1. Lihat  2. Tambah  3. Ubah  4. Hapus.
 * Kartu angka di atas = "Melihat dashboard ringkasan" (use case admin).
 * Tampilan dari Form Builder (ProdukPanel.form); form Tambah/Ubah ada di DialogProduk.
 */
public class ProdukPanel extends javax.swing.JPanel implements Halaman {

    private final ProdukController controller = new ProdukController();
    private final PagerPanel pager = new PagerPanel(8, "data");
    private final DefaultTableModel model;
    private List<Produk> data = new ArrayList<>();
    private List<Produk> tampil = new ArrayList<>();

    public ProdukPanel() {
        initComponents();
        model = (DefaultTableModel) tblProduk.getModel();
        UI.tabel(tblProduk, scrProduk);
        tblProduk.getColumnModel().getColumn(1).setPreferredWidth(240);
        tblProduk.getColumnModel().getColumn(5).setPreferredWidth(170);
        new KolomAksi(tblProduk, 5,
                new KolomAksi.Tombol("Ubah", UI.Jenis.SEKUNDER, b -> true, this::ubah),
                new KolomAksi.Tombol("Hapus", UI.Jenis.BAHAYA, b -> true, this::hapus));
        UI.input(txtCari, "Cari nama atau kode...");
        UI.combo(cmbKategori);
        UI.sekunder(btnTampilkan);
        UI.primer(btnTambah);
        UI.kartu(pnlAngkaProduk, 14, 20, 14, 20);
        UI.kartu(pnlAngkaKategori, 14, 20, 14, 20);
        UI.kartu(pnlAngkaStok, 14, 20, 14, 20);
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanTabel);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        String kategori = (String) cmbKategori.getSelectedItem();
        coba(() -> {
            cmbKategori.removeAllItems();
            cmbKategori.addItem("Semua kategori");
            controller.daftarKategori().forEach(cmbKategori::addItem);
            cmbKategori.setSelectedItem(kategori);

            List<Produk> semua = controller.cari("", null);
            lblAngkaProduk.setText(String.valueOf(semua.size()));
            lblAngkaKategori.setText(String.valueOf(controller.daftarKategori().size()));
            lblAngkaStok.setText(String.valueOf(semua.stream().mapToInt(Produk::getStok).sum()));

            data = controller.cari(txtCari.getText(), (String) cmbKategori.getSelectedItem());
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data produk.");
        });
    }

    private void tampilkanTabel() {
        if (tblProduk.isEditing()) tblProduk.getCellEditor().cancelCellEditing();
        tampil = new ArrayList<>(pager.potong(data));
        model.setRowCount(0);
        for (Produk p : tampil) {
            model.addRow(new Object[]{p.getKode(), p.getNama(), p.getKategori(), UI.rupiah(p.getHarga()), p.getStok(), ""});
        }
        UI.sesuaikanTinggi(scrProduk, tblProduk);
    }

    /** Form Tambah (lama = null) / Ubah produk. */
    private void bukaForm(Produk lama) {
        DialogProduk d = new DialogProduk(SwingUtilities.getWindowAncestor(this), lama);
        d.setVisible(true);
        if (d.isBerhasil()) {
            muat();
            sukses(lama == null ? "Produk berhasil disimpan" : "Produk berhasil diubah");
        }
    }

    private void ubah(int baris) {
        bukaForm(tampil.get(baris));
    }

    private void hapus(int baris) {
        Produk p = tampil.get(baris);
        if (UI.konfirmasi(this, "Hapus produk?", "<b>" + p.getNama()
                + "</b><br><br>Produk akan dihapus dari katalog. Tindakan ini tidak dapat dibatalkan.", "Ya, Hapus", true)
                && coba(() -> controller.hapus(p.getId()))) {
            muat();
            sukses("Produk berhasil dihapus");
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
        pnlRingkasan = new javax.swing.JPanel();
        pnlAngkaProduk = new javax.swing.JPanel();
        lblAngkaProduk = new javax.swing.JLabel();
        lblKetProduk = new javax.swing.JLabel();
        pnlAngkaKategori = new javax.swing.JPanel();
        lblAngkaKategori = new javax.swing.JLabel();
        lblKetKategori = new javax.swing.JLabel();
        pnlAngkaStok = new javax.swing.JPanel();
        lblAngkaStok = new javax.swing.JLabel();
        lblKetStok = new javax.swing.JLabel();
        pnlFilter = new javax.swing.JPanel();
        pnlCari = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        pnlKategori = new javax.swing.JPanel();
        lblKategori = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        scrProduk = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        pnlPager = new javax.swing.JPanel();

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
        lblJudul.setText("Kelola produk");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Kelola katalog, harga, dan ketersediaan elektronik.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);
        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);
        pnlAksiKepala.setOpaque(false);
        pnlAksiKepala.setLayout(new java.awt.BorderLayout());
        btnTambah.setBackground(new java.awt.Color(33, 97, 213));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+ Tambah Produk");
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
        pnlRingkasan.setOpaque(false);
        pnlRingkasan.setLayout(new java.awt.GridLayout(1, 3, 16, 0));
        pnlAngkaProduk.setBackground(new java.awt.Color(255, 255, 255));
        pnlAngkaProduk.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(14, 20, 14, 20)));
        pnlAngkaProduk.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 0));
        lblAngkaProduk.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblAngkaProduk.setForeground(new java.awt.Color(33, 97, 213));
        lblAngkaProduk.setText("15");
        pnlAngkaProduk.add(lblAngkaProduk);
        lblKetProduk.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKetProduk.setForeground(new java.awt.Color(94, 108, 132));
        lblKetProduk.setText("Produk terdaftar");
        pnlAngkaProduk.add(lblKetProduk);
        pnlRingkasan.add(pnlAngkaProduk);
        pnlAngkaKategori.setBackground(new java.awt.Color(255, 255, 255));
        pnlAngkaKategori.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(14, 20, 14, 20)));
        pnlAngkaKategori.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 0));
        lblAngkaKategori.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblAngkaKategori.setForeground(new java.awt.Color(33, 97, 213));
        lblAngkaKategori.setText("4");
        pnlAngkaKategori.add(lblAngkaKategori);
        lblKetKategori.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKetKategori.setForeground(new java.awt.Color(94, 108, 132));
        lblKetKategori.setText("Kategori tersedia");
        pnlAngkaKategori.add(lblKetKategori);
        pnlRingkasan.add(pnlAngkaKategori);
        pnlAngkaStok.setBackground(new java.awt.Color(255, 255, 255));
        pnlAngkaStok.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(14, 20, 14, 20)));
        pnlAngkaStok.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 0));
        lblAngkaStok.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblAngkaStok.setForeground(new java.awt.Color(33, 97, 213));
        lblAngkaStok.setText("274");
        pnlAngkaStok.add(lblAngkaStok);
        lblKetStok.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKetStok.setForeground(new java.awt.Color(94, 108, 132));
        lblKetStok.setText("Total stok unit");
        pnlAngkaStok.add(lblKetStok);
        pnlRingkasan.add(pnlAngkaStok);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlRingkasan, gridBagConstraints);
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
        pnlKategori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlKategori.setOpaque(false);
        pnlKategori.setLayout(new java.awt.GridBagLayout());
        lblKategori.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKategori.setForeground(new java.awt.Color(23, 43, 77));
        lblKategori.setText("Filter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKategori.add(lblKategori, gridBagConstraints);
        cmbKategori.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cmbKategori.setForeground(new java.awt.Color(23, 43, 77));
        cmbKategori.setBackground(new java.awt.Color(255, 255, 255));
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua kategori" }));
        cmbKategori.setPreferredSize(new java.awt.Dimension(190, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlKategori.add(cmbKategori, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(pnlKategori, gridBagConstraints);
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlFilter, gridBagConstraints);
        scrProduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)));
        scrProduk.setPreferredSize(new java.awt.Dimension(700, 300));
        scrProduk.setBackground(new java.awt.Color(255, 255, 255));
        tblProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama produk", "Kategori", "Harga", "Stok", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduk.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tblProduk.setRowHeight(44);
        scrProduk.setViewportView(tblProduk);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrProduk, gridBagConstraints);
        pnlPager.setOpaque(false);
        pnlPager.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlPager, gridBagConstraints);
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
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JLabel lblAngkaKategori;
    private javax.swing.JLabel lblAngkaProduk;
    private javax.swing.JLabel lblAngkaStok;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblKetKategori;
    private javax.swing.JLabel lblKetProduk;
    private javax.swing.JLabel lblKetStok;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAksiKepala;
    private javax.swing.JPanel pnlAngkaKategori;
    private javax.swing.JPanel pnlAngkaProduk;
    private javax.swing.JPanel pnlAngkaStok;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiFilter;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKategori;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlPager;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JScrollPane scrProduk;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
