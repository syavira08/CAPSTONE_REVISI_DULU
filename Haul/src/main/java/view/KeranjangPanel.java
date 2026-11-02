package view;

import config.Konfigurasi;
import controller.PesananController;
import model.Pesanan;

/**
 * Flowchart Keranjang Belanja:
 *  2. Hapus produk   3. Ubah jumlah (harus > 0 & stok cukup)   4. Lanjut ke Checkout.
 *  (1. Tambah produk dilakukan dari halaman Katalog.)
 * Tampilan dari Form Builder (KeranjangPanel.form); tiap barang memakai form BarisKeranjang.
 */
public class KeranjangPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final PesananController controller = new PesananController();

    public KeranjangPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        UI.sekunder(btnLanjutBelanja);
        UI.primer(btnCheckout);
        UI.sekunder(btnLihatKatalog);
        UI.sorotan(pnlInfoHarga, 10, 14, 10, 14);
        UI.info(pnlInfoHarga, lblInfoHarga, "Harga dan ketersediaan stok akan diperiksa kembali saat checkout.");
        UI.kartu(pnlDaftarKartu);
        UI.kartu(pnlRingkasan);
        UI.kartu(pnlKosong, 40, 20, 40, 20);
        UI.ikon(lblKosongIkon, "kotak", 30, UI.PUDAR);
        UI.sembunyi(pnlPesan);
        UI.lebarTetap(pnlKananWrap, 330);
        UI.tema(this);
    }

    @Override
    public void muat() {
        Pesanan k = controller.getKeranjang();
        boolean kosong = k.getItems().isEmpty();
        pnlDuaKolom.setVisible(!kosong);
        pnlKosong.setVisible(kosong);
        if (kosong) return;

        lblDaftarJudul.setText("Produk Anda (" + k.getItems().size() + ")");
        pnlDaftar.removeAll();
        for (Pesanan.Item item : k.getItems()) {
            int id = item.getProduk().getId();
            UI.tambahBaris(pnlDaftar, new BarisKeranjang(item, jumlah -> ubahJumlah(id, jumlah), () -> {
                controller.hapusDariKeranjang(id);
                muat();
                sukses(item.getProduk().getNama() + " dihapus dari keranjang.");
            }), 14);
        }
        pnlDaftar.revalidate();
        pnlDaftar.repaint();

        lblSubLabel.setText("Subtotal (" + k.getJumlahBarang() + " barang)");
        lblSubNilai.setText(UI.rupiah(k.getSubtotal()));
        lblOngkirNilai.setText(UI.rupiah(Konfigurasi.ONGKOS_KIRIM));
        lblTotalNilai.setText(UI.rupiah(k.getSubtotal().add(Konfigurasi.ONGKOS_KIRIM)));
    }

    private void ubahJumlah(int idProduk, int jumlah) {
        if (coba(() -> controller.ubahJumlah(idProduk, jumlah))) {
            muat();
            bersihkanPesan();
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
        btnLanjutBelanja = new javax.swing.JButton();
        pnlPesan = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlKonten = new javax.swing.JPanel();
        pnlDuaKolom = new javax.swing.JPanel();
        pnlKiri = new javax.swing.JPanel();
        pnlDaftarKartu = new javax.swing.JPanel();
        lblDaftarJudul = new javax.swing.JLabel();
        pnlDaftar = new javax.swing.JPanel();
        pnlInfoHarga = new javax.swing.JPanel();
        lblInfoHarga = new javax.swing.JLabel();
        pnlKananWrap = new javax.swing.JPanel();
        pnlRingkasan = new javax.swing.JPanel();
        lblRingkasanJudul = new javax.swing.JLabel();
        pnlSub = new javax.swing.JPanel();
        lblSubLabel = new javax.swing.JLabel();
        lblSubNilai = new javax.swing.JLabel();
        pnlOngkir = new javax.swing.JPanel();
        lblOngkirLabel = new javax.swing.JLabel();
        lblOngkirNilai = new javax.swing.JLabel();
        pnlGarisTotal = new javax.swing.JPanel();
        pnlTotal = new javax.swing.JPanel();
        lblTotalLabel = new javax.swing.JLabel();
        lblTotalNilai = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        lblAman = new javax.swing.JLabel();
        pnlKosong = new javax.swing.JPanel();
        lblKosongIkon = new javax.swing.JLabel();
        lblKosongJudul = new javax.swing.JLabel();
        lblKosongIsi = new javax.swing.JLabel();
        btnLihatKatalog = new javax.swing.JButton();

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
        lblJudul.setText("Keranjang belanja");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);

        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Periksa produk dan jumlah sebelum melanjutkan checkout.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);

        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);

        pnlAksiKepala.setOpaque(false);
        pnlAksiKepala.setLayout(new java.awt.BorderLayout());

        btnLanjutBelanja.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLanjutBelanja.setForeground(new java.awt.Color(23, 43, 77));
        btnLanjutBelanja.setText("Lanjut belanja");
        btnLanjutBelanja.setFocusPainted(false);
        btnLanjutBelanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanjutBelanjaActionPerformed(evt);
            }
        });
        pnlAksiKepala.add(btnLanjutBelanja, java.awt.BorderLayout.NORTH);

        pnlKepala.add(pnlAksiKepala, java.awt.BorderLayout.EAST);

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

        pnlDuaKolom.setOpaque(false);
        pnlDuaKolom.setLayout(new java.awt.BorderLayout(20, 0));

        pnlKiri.setOpaque(false);
        pnlKiri.setLayout(new java.awt.GridBagLayout());

        pnlDaftarKartu.setBackground(new java.awt.Color(255, 255, 255));
        pnlDaftarKartu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlDaftarKartu.setLayout(new java.awt.GridBagLayout());

        lblDaftarJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDaftarJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblDaftarJudul.setText("Produk Anda (2)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDaftarKartu.add(lblDaftarJudul, gridBagConstraints);

        pnlDaftar.setOpaque(false);
        pnlDaftar.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlDaftarKartu.add(pnlDaftar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKiri.add(pnlDaftarKartu, gridBagConstraints);

        pnlInfoHarga.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfoHarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfoHarga.setLayout(new java.awt.BorderLayout());

        lblInfoHarga.setForeground(new java.awt.Color(33, 97, 213));
        lblInfoHarga.setText("Harga dan ketersediaan stok akan diperiksa kembali saat checkout.");
        pnlInfoHarga.add(lblInfoHarga, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKiri.add(pnlInfoHarga, gridBagConstraints);

        pnlDuaKolom.add(pnlKiri, java.awt.BorderLayout.CENTER);

        pnlKananWrap.setPreferredSize(new java.awt.Dimension(330, 10));
        pnlKananWrap.setOpaque(false);
        pnlKananWrap.setLayout(new java.awt.BorderLayout());

        pnlRingkasan.setBackground(new java.awt.Color(255, 255, 255));
        pnlRingkasan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlRingkasan.setLayout(new java.awt.GridBagLayout());

        lblRingkasanJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblRingkasanJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblRingkasanJudul.setText("Ringkasan belanja");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlRingkasan.add(lblRingkasanJudul, gridBagConstraints);

        pnlSub.setOpaque(false);
        pnlSub.setLayout(new java.awt.BorderLayout(12, 0));

        lblSubLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblSubLabel.setText("Subtotal (2 barang)");
        pnlSub.add(lblSubLabel, java.awt.BorderLayout.CENTER);

        lblSubNilai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblSubNilai.setText("Rp500.000");
        lblSubNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlSub.add(lblSubNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlSub, gridBagConstraints);

        pnlOngkir.setOpaque(false);
        pnlOngkir.setLayout(new java.awt.BorderLayout(12, 0));

        lblOngkirLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblOngkirLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblOngkirLabel.setText("Ongkos kirim");
        pnlOngkir.add(lblOngkirLabel, java.awt.BorderLayout.CENTER);

        lblOngkirNilai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblOngkirNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblOngkirNilai.setText("Rp20.000");
        lblOngkirNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlOngkir.add(lblOngkirNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlOngkir, gridBagConstraints);

        pnlGarisTotal.setBackground(new java.awt.Color(220, 227, 237));
        pnlGarisTotal.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGarisTotal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlGarisTotal, gridBagConstraints);

        pnlTotal.setOpaque(false);
        pnlTotal.setLayout(new java.awt.BorderLayout(12, 0));

        lblTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotalLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblTotalLabel.setText("Total pembayaran");
        pnlTotal.add(lblTotalLabel, java.awt.BorderLayout.CENTER);

        lblTotalNilai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalNilai.setForeground(new java.awt.Color(33, 97, 213));
        lblTotalNilai.setText("Rp520.000");
        lblTotalNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlTotal.add(lblTotalNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlTotal, gridBagConstraints);

        btnCheckout.setBackground(new java.awt.Color(33, 97, 213));
        btnCheckout.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setText("Lanjut Checkout");
        btnCheckout.setFocusPainted(false);
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(btnCheckout, gridBagConstraints);

        lblAman.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblAman.setForeground(new java.awt.Color(94, 108, 132));
        lblAman.setText("<html><div width=270>Pembayaran aman melalui transfer bank atau e-wallet.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(lblAman, gridBagConstraints);

        pnlKananWrap.add(pnlRingkasan, java.awt.BorderLayout.NORTH);

        pnlDuaKolom.add(pnlKananWrap, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlDuaKolom, gridBagConstraints);

        pnlKosong.setBackground(new java.awt.Color(255, 255, 255));
        pnlKosong.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(40, 20, 40, 20)));
        pnlKosong.setLayout(new java.awt.GridBagLayout());

        lblKosongIkon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblKosongIkon.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIkon.setText(" ");
        lblKosongIkon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        pnlKosong.add(lblKosongIkon, gridBagConstraints);

        lblKosongJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblKosongJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblKosongJudul.setText("Keranjang kosong");
        lblKosongJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosong.add(lblKosongJudul, gridBagConstraints);

        lblKosongIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIsi.setText("Pilih elektronik favorit dan tambahkan ke keranjang.");
        lblKosongIsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosong.add(lblKosongIsi, gridBagConstraints);

        btnLihatKatalog.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLihatKatalog.setForeground(new java.awt.Color(23, 43, 77));
        btnLihatKatalog.setText("Lihat Katalog");
        btnLihatKatalog.setFocusPainted(false);
        btnLihatKatalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatKatalogActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlKosong.add(btnLihatKatalog, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlKosong, gridBagConstraints);

        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);

        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanjutBelanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanjutBelanjaActionPerformed
        jendela.buka(jendela.katalog);
    }//GEN-LAST:event_btnLanjutBelanjaActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        if (coba(controller::periksaSebelumCheckout)) jendela.buka(jendela.checkout);
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnLihatKatalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatKatalogActionPerformed
        jendela.buka(jendela.katalog);
    }//GEN-LAST:event_btnLihatKatalogActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnLanjutBelanja;
    private javax.swing.JButton btnLihatKatalog;
    private javax.swing.JLabel lblAman;
    private javax.swing.JLabel lblDaftarJudul;
    private javax.swing.JLabel lblInfoHarga;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKosongIkon;
    private javax.swing.JLabel lblKosongIsi;
    private javax.swing.JLabel lblKosongJudul;
    private javax.swing.JLabel lblOngkirLabel;
    private javax.swing.JLabel lblOngkirNilai;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblRingkasanJudul;
    private javax.swing.JLabel lblSubLabel;
    private javax.swing.JLabel lblSubNilai;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JLabel lblTotalLabel;
    private javax.swing.JLabel lblTotalNilai;
    private javax.swing.JPanel pnlAksiKepala;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlDaftarKartu;
    private javax.swing.JPanel pnlDuaKolom;
    private javax.swing.JPanel pnlGarisTotal;
    private javax.swing.JPanel pnlInfoHarga;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKananWrap;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKiri;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlKosong;
    private javax.swing.JPanel pnlOngkir;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JPanel pnlSub;
    private javax.swing.JPanel pnlTotal;
    // End of variables declaration//GEN-END:variables
}
