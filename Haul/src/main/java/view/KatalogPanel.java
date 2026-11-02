package view;

import controller.AuthController;
import controller.PesananController;
import controller.ProdukController;
import controller.PromoController;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import model.Produk;
import model.Promo;

/**
 * Flowchart Katalog Produk: ambil data produk -> ada? tampilkan daftar : "Belum ada data produk".
 * Pelanggan bisa memilih kategori dan menambahkan produk ke keranjang.
 * Tampilan dibuat di Form Builder (KatalogPanel.form); kartu produk memakai form KartuProduk.
 */
public class KatalogPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final ProdukController produkController = new ProdukController();
    private final PesananController pesananController = new PesananController();
    private final PagerPanel pager = new PagerPanel(6, "produk");
    private List<Produk> hasil = new ArrayList<>();

    public KatalogPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        UI.input(txtCari, "Cari laptop, smartphone, audio...");
        UI.combo(cmbKategori);
        UI.sekunder(btnTampilkan);
        UI.sekunder(btnLihatProduk);
        UI.sorotan(pnlBanner, 16, 18, 16, 18);
        UI.kartu(pnlKosong, 40, 20, 40, 20);
        UI.ikon(lblKosongIkon, "kotak", 30, UI.PUDAR);
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanProduk);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        lblSubjudul.setText("Selamat datang, " + AuthController.getPenggunaLogin().getNama()
                + ". Lengkapi hari Anda dengan teknologi yang tepat.");
        String kategoriDipilih = (String) cmbKategori.getSelectedItem();
        coba(() -> {
            cmbKategori.removeAllItems();
            cmbKategori.addItem("Semua kategori");
            produkController.daftarKategori().forEach(cmbKategori::addItem);
            cmbKategori.setSelectedItem(kategoriDipilih);

            bannerPromo(new PromoController().promoAktif());
            hasil = produkController.cari(txtCari.getText(), (String) cmbKategori.getSelectedItem());
            tampilkanProduk();
        });
    }

    /** Banner promo diambil dari voucher yang sedang aktif di database. */
    private void bannerPromo(Promo promo) {
        pnlBanner.setVisible(promo != null);
        if (promo == null) return;
        lblPromoTag.setText("PROMO HAULECTRA • " + UI.tanggal(promo.getMulai()) + " – " + UI.tanggal(promo.getSelesai()));
        lblPromoJudul.setText("Hemat " + promo.getTeksDiskon() + " untuk belanja favorit Anda");
        lblPromoIsi.setText("Minimal " + promo.getMinimalBarang() + " barang. Gunakan voucher "
                + promo.getKode() + " saat checkout.");
    }

    /** Tampilkan kartu produk untuk halaman aktif. */
    private void tampilkanProduk() {
        List<Produk> halaman = pager.potong(hasil);
        pnlGrid.removeAll();
        for (Produk p : halaman) {
            // Flowchart Tambah Produk: sistem memeriksa stok -> tambah ke keranjang / "Stok tidak mencukupi"
            pnlGrid.add(new KartuProduk(p, () -> {
                if (coba(() -> pesananController.tambahKeKeranjang(p.getId()))) {
                    sukses(p.getNama() + " ditambahkan ke keranjang.");
                }
            }));
        }
        // kolom kosong agar kartu terakhir tidak melebar
        for (int i = halaman.size(); i % 3 != 0; i++) pnlGrid.add(new javax.swing.JPanel() {{ setOpaque(false); }});
        pnlGrid.setVisible(!hasil.isEmpty());
        pnlKosong.setVisible(hasil.isEmpty());
        pnlGrid.revalidate();
        pnlGrid.repaint();
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
        pnlBanner = new javax.swing.JPanel();
        pnlPromoTeks = new javax.swing.JPanel();
        lblPromoTag = new javax.swing.JLabel();
        lblPromoJudul = new javax.swing.JLabel();
        lblPromoIsi = new javax.swing.JLabel();
        pnlLihat = new javax.swing.JPanel();
        btnLihatProduk = new javax.swing.JButton();
        pnlFilter = new javax.swing.JPanel();
        pnlCari = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        pnlKategori = new javax.swing.JPanel();
        lblKategori = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        pnlGrid = new javax.swing.JPanel();
        pnlKosong = new javax.swing.JPanel();
        lblKosongIkon = new javax.swing.JLabel();
        lblKosongJudul = new javax.swing.JLabel();
        lblKosongIsi = new javax.swing.JLabel();
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
        lblJudul.setText("Temukan elektronik pilihan Anda");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Selamat datang. Lengkapi hari Anda dengan teknologi yang tepat.");
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
        pnlBanner.setBackground(new java.awt.Color(234, 241, 252));
        pnlBanner.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlBanner.setLayout(new java.awt.BorderLayout(12, 0));
        pnlPromoTeks.setOpaque(false);
        pnlPromoTeks.setLayout(new java.awt.GridBagLayout());
        lblPromoTag.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblPromoTag.setForeground(new java.awt.Color(33, 97, 213));
        lblPromoTag.setText("PROMO HAULECTRA \u2022 01 JAN 2026 \u2013 31 DES 2026");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlPromoTeks.add(lblPromoTag, gridBagConstraints);
        lblPromoJudul.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblPromoJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblPromoJudul.setText("Hemat 20% untuk belanja favorit Anda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlPromoTeks.add(lblPromoJudul, gridBagConstraints);
        lblPromoIsi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPromoIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblPromoIsi.setText("Minimal 10 barang. Gunakan voucher HAUL20 saat checkout.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlPromoTeks.add(lblPromoIsi, gridBagConstraints);
        pnlBanner.add(pnlPromoTeks, java.awt.BorderLayout.CENTER);
        pnlLihat.setOpaque(false);
        pnlLihat.setLayout(new java.awt.GridBagLayout());
        btnLihatProduk.setBackground(new java.awt.Color(255, 255, 255));
        btnLihatProduk.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnLihatProduk.setForeground(new java.awt.Color(23, 43, 77));
        btnLihatProduk.setText("Lihat produk");
        btnLihatProduk.setFocusPainted(false);
        btnLihatProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatProdukActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlLihat.add(btnLihatProduk, gridBagConstraints);
        pnlBanner.add(pnlLihat, java.awt.BorderLayout.EAST);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlBanner, gridBagConstraints);
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
        txtCari.setPreferredSize(new java.awt.Dimension(340, 38));
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
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlKonten.add(pnlFilter, gridBagConstraints);
        pnlGrid.setOpaque(false);
        pnlGrid.setLayout(new java.awt.GridLayout(0, 3, 16, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlKonten.add(pnlGrid, gridBagConstraints);
        pnlKosong.setBackground(new java.awt.Color(255, 255, 255));
        pnlKosong.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(40, 20, 40, 20)));
        pnlKosong.setLayout(new java.awt.GridBagLayout());
        lblKosongIkon.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKosongIkon.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIkon.setText(" ");
        lblKosongIkon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        pnlKosong.add(lblKosongIkon, gridBagConstraints);
        lblKosongJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblKosongJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblKosongJudul.setText("Produk belum tersedia");
        lblKosongJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosong.add(lblKosongJudul, gridBagConstraints);
        lblKosongIsi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKosongIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIsi.setText("Coba kategori lain atau hapus kata pencarian.");
        lblKosongIsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosong.add(lblKosongIsi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlKonten.add(pnlKosong, gridBagConstraints);
        pnlPager.setOpaque(false);
        pnlPager.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlKonten.add(pnlPager, gridBagConstraints);
        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);
        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLihatProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatProdukActionPerformed
        SwingUtilities.invokeLater(() -> pnlGrid.scrollRectToVisible(
                new Rectangle(0, 0, 1, Math.max(1, pnlGrid.getHeight()))));
    }//GEN-LAST:event_btnLihatProdukActionPerformed

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        pager.reset();
        muat();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLihatProduk;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblKosongIkon;
    private javax.swing.JLabel lblKosongIsi;
    private javax.swing.JLabel lblKosongJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblPromoIsi;
    private javax.swing.JLabel lblPromoJudul;
    private javax.swing.JLabel lblPromoTag;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlGrid;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiFilter;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKategori;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlKosong;
    private javax.swing.JPanel pnlLihat;
    private javax.swing.JPanel pnlPager;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlPromoTeks;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
