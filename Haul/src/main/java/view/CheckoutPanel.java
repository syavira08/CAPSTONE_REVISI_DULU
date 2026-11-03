package view;

import config.Konfigurasi;
import controller.PelangganController;
import controller.PesananController;
import controller.ValidasiException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pelanggan;
import model.Pesanan;
import model.Promo;

/** Checkout (flowchart Checkout): alamat, barang, metode pembayaran, voucher, konfirmasi buat pesanan. */
public class CheckoutPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView induk;
    private final PesananController pesanan = new PesananController();
    private final PelangganController pelanggan = new PelangganController();
    private List<Promo> voucher = List.of();
    private boolean siap;

    public CheckoutPanel(PelangganView induk) {
        this.induk = induk;
        initComponents();
        scrKiri.getVerticalScrollBar().setUnitIncrement(16);
        Gaya.tabel(tblBarang);
        Gaya.lebarKolom(tblBarang, 260, 110, 70, 120);
    }

    @Override
    public void muat() {
        Pesanan k = pesanan.getKeranjang();
        Pelanggan saya = pelanggan.profilSaya();
        lblPenerima.setText(saya.getNama() + " \u2022 " + saya.getNoTelepon());
        lblAlamat.setText(saya.getAlamat());

        DefaultTableModel model = (DefaultTableModel) tblBarang.getModel();
        model.setRowCount(0);
        for (Pesanan.Item i : k.getItems()) {
            model.addRow(new Object[]{i.getProduk().getNama(), Gaya.rupiah(i.getProduk().getHarga()),
                i.getJumlah(), Gaya.rupiah(i.getSubtotal())});
        }

        grpMetode.clearSelection();
        Gaya.error(lblErrMetode, null);

        // voucher aktif dari database (pelanggan cukup memilih)
        siap = false;
        voucher = pesanan.voucherTersedia();
        cmbVoucher.removeAllItems();
        cmbVoucher.addItem("Tanpa voucher");
        for (Promo p : voucher) {
            cmbVoucher.addItem(p.getKode() + "  \u2022  potongan " + p.getTeksDiskon() + ", min. "
                    + p.getMinimalBarang() + " barang");
        }
        int pilih = k.getPromo() == null ? 0 : voucher.stream().map(Promo::getKode).toList().indexOf(k.getPromo().getKode()) + 1;
        cmbVoucher.setSelectedIndex(Math.max(0, pilih));
        if (pilih <= 0) k.setPromo(null);
        siap = true;
        infoVoucher(k.getPromo() == null ? " " : "Voucher " + k.getPromo().getKode() + " dipakai.", Gaya.HIJAU);
        hitung();
    }

    private void hitung() {
        Pesanan k = pesanan.getKeranjang();
        lblSubtotalTeks.setText("Subtotal (" + k.getJumlahBarang() + " barang)");
        lblSubtotal.setText(Gaya.rupiah(k.getSubtotal()));
        lblOngkir.setText(Gaya.rupiah(Konfigurasi.ONGKOS_KIRIM));
        lblPotongan.setText("-" + Gaya.rupiah(k.getPotongan()));
        lblTotal.setText(Gaya.rupiah(k.hitungTotal(Konfigurasi.ONGKOS_KIRIM)));
    }

    private void infoVoucher(String teks, java.awt.Color warna) {
        lblVoucherInfo.setText(teks);
        lblVoucherInfo.setForeground(warna);
    }

    private String metode() {
        if (rbTransfer.isSelected()) return "TRANSFER";
        if (rbEwallet.isSelected()) return "E_WALLET";
        return null;
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        grpMetode = new javax.swing.ButtonGroup();
        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        scrKiri = new javax.swing.JScrollPane();
        pnlKiri = new javax.swing.JPanel();
        pnlAlamat = new javax.swing.JPanel();
        lblAlamatJudul = new javax.swing.JLabel();
        lblPenerima = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblUbahAlamat = new javax.swing.JLabel();
        pnlBarang = new javax.swing.JPanel();
        lblBarangJudul = new javax.swing.JLabel();
        scrBarang = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        pnlMetode = new javax.swing.JPanel();
        lblMetodeJudul = new javax.swing.JLabel();
        rbTransfer = new javax.swing.JRadioButton();
        rbEwallet = new javax.swing.JRadioButton();
        pnlMetodeSisa = new javax.swing.JPanel();
        lblErrMetode = new javax.swing.JLabel();
        pnlVoucher = new javax.swing.JPanel();
        lblVoucherJudul = new javax.swing.JLabel();
        cmbVoucher = new javax.swing.JComboBox<>();
        lblVoucherInfo = new javax.swing.JLabel();
        pnlKiriSisa = new javax.swing.JPanel();
        pnlKanan = new javax.swing.JPanel();
        pnlRingkasan = new javax.swing.JPanel();
        lblRingkasan = new javax.swing.JLabel();
        lblSubtotalTeks = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblOngkirTeks = new javax.swing.JLabel();
        lblOngkir = new javax.swing.JLabel();
        lblPotonganTeks = new javax.swing.JLabel();
        lblPotongan = new javax.swing.JLabel();
        sepTotal = new javax.swing.JSeparator();
        lblTotalTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnBuatPesanan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        setBackground(new java.awt.Color(245, 247, 251));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 32, 24, 32));
        setLayout(new java.awt.BorderLayout());

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Checkout");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Periksa alamat, pilih metode pembayaran dan voucher, lalu buat pesanan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);
        add(pnlKepala, java.awt.BorderLayout.NORTH);

        scrKiri.setBorder(null);
        scrKiri.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlKiri.setBackground(new java.awt.Color(245, 247, 251));
        pnlKiri.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlKiri.setLayout(new java.awt.GridBagLayout());
        pnlAlamat.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pnlAlamat.setLayout(new java.awt.GridBagLayout());
        lblAlamatJudul.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblAlamatJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblAlamatJudul.setText("Alamat pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlAlamat.add(lblAlamatJudul, gridBagConstraints);
        lblPenerima.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPenerima.setForeground(new java.awt.Color(16, 24, 40));
        lblPenerima.setText("Nama \u2022 Telepon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlAlamat.add(lblPenerima, gridBagConstraints);
        lblAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(102, 112, 133));
        lblAlamat.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlAlamat.add(lblAlamat, gridBagConstraints);
        lblUbahAlamat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblUbahAlamat.setForeground(new java.awt.Color(102, 112, 133));
        lblUbahAlamat.setText("Alamat dapat diubah di menu Profil.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlAlamat.add(lblUbahAlamat, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlKiri.add(pnlAlamat, gridBagConstraints);

        pnlBarang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBarang.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pnlBarang.setLayout(new java.awt.GridBagLayout());
        lblBarangJudul.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblBarangJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblBarangJudul.setText("Barang dipesan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlBarang.add(lblBarangJudul, gridBagConstraints);
        scrBarang.setBackground(new java.awt.Color(255, 255, 255));
        scrBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        scrBarang.setPreferredSize(new java.awt.Dimension(600, 170));
        tblBarang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBarang.setGridColor(new java.awt.Color(228, 231, 236));
        tblBarang.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produk", "Harga", "Jumlah", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBarang.setRowHeight(40);
        tblBarang.setSelectionBackground(new java.awt.Color(232, 240, 255));
        tblBarang.setSelectionForeground(new java.awt.Color(16, 24, 40));
        tblBarang.setShowVerticalLines(false);
        scrBarang.setViewportView(tblBarang);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlBarang.add(scrBarang, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlKiri.add(pnlBarang, gridBagConstraints);

        pnlMetode.setBackground(new java.awt.Color(255, 255, 255));
        pnlMetode.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pnlMetode.setLayout(new java.awt.GridBagLayout());
        lblMetodeJudul.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblMetodeJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblMetodeJudul.setText("Metode pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlMetode.add(lblMetodeJudul, gridBagConstraints);
        rbTransfer.setBackground(new java.awt.Color(255, 255, 255));
        grpMetode.add(rbTransfer);
        rbTransfer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbTransfer.setFocusPainted(false);
        rbTransfer.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbTransfer.setForeground(new java.awt.Color(16, 24, 40));
        rbTransfer.setText("Transfer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 24);
        pnlMetode.add(rbTransfer, gridBagConstraints);
        rbEwallet.setBackground(new java.awt.Color(255, 255, 255));
        grpMetode.add(rbEwallet);
        rbEwallet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbEwallet.setFocusPainted(false);
        rbEwallet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbEwallet.setForeground(new java.awt.Color(16, 24, 40));
        rbEwallet.setText("E-Wallet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlMetode.add(rbEwallet, gridBagConstraints);
        pnlMetodeSisa.setBackground(new java.awt.Color(255, 255, 255));
        pnlMetodeSisa.setOpaque(false);
        pnlMetodeSisa.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMetode.add(pnlMetodeSisa, gridBagConstraints);

        lblErrMetode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrMetode.setForeground(new java.awt.Color(217, 45, 32));
        lblErrMetode.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrMetode.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlMetode.add(lblErrMetode, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlKiri.add(pnlMetode, gridBagConstraints);

        pnlVoucher.setBackground(new java.awt.Color(255, 255, 255));
        pnlVoucher.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pnlVoucher.setLayout(new java.awt.GridBagLayout());
        lblVoucherJudul.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblVoucherJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblVoucherJudul.setText("Voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlVoucher.add(lblVoucherJudul, gridBagConstraints);
        cmbVoucher.setBackground(new java.awt.Color(255, 255, 255));
        cmbVoucher.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbVoucher.setForeground(new java.awt.Color(16, 24, 40));
        cmbVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanpa voucher" }));
        cmbVoucher.setPreferredSize(new java.awt.Dimension(420, 38));
        cmbVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVoucherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlVoucher.add(cmbVoucher, gridBagConstraints);
        lblVoucherInfo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblVoucherInfo.setForeground(new java.awt.Color(102, 112, 133));
        lblVoucherInfo.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlVoucher.add(lblVoucherInfo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKiri.add(pnlVoucher, gridBagConstraints);

        pnlKiriSisa.setBackground(new java.awt.Color(255, 255, 255));
        pnlKiriSisa.setOpaque(false);
        pnlKiriSisa.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlKiri.add(pnlKiriSisa, gridBagConstraints);

        scrKiri.setViewportView(pnlKiri);

        add(scrKiri, java.awt.BorderLayout.CENTER);
        pnlKanan.setBackground(new java.awt.Color(255, 255, 255));
        pnlKanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));
        pnlKanan.setOpaque(false);
        pnlKanan.setLayout(new java.awt.BorderLayout());
        pnlRingkasan.setBackground(new java.awt.Color(255, 255, 255));
        pnlRingkasan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pnlRingkasan.setLayout(new java.awt.GridBagLayout());
        lblRingkasan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblRingkasan.setForeground(new java.awt.Color(16, 24, 40));
        lblRingkasan.setText("Ringkasan belanja");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlRingkasan.add(lblRingkasan, gridBagConstraints);
        lblSubtotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtotalTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblSubtotalTeks.setText("Subtotal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblSubtotalTeks, gridBagConstraints);
        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(16, 24, 40));
        lblSubtotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblSubtotal, gridBagConstraints);
        lblOngkirTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblOngkirTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblOngkirTeks.setText("Ongkos kirim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblOngkirTeks, gridBagConstraints);
        lblOngkir.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOngkir.setForeground(new java.awt.Color(16, 24, 40));
        lblOngkir.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblOngkir, gridBagConstraints);
        lblPotonganTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPotonganTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblPotonganTeks.setText("Potongan voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblPotonganTeks, gridBagConstraints);
        lblPotongan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPotongan.setForeground(new java.awt.Color(18, 128, 92));
        lblPotongan.setText("-Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblPotongan, gridBagConstraints);
        sepTotal.setForeground(new java.awt.Color(228, 231, 236));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 4, 0);
        pnlRingkasan.add(sepTotal, gridBagConstraints);
        lblTotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTotalTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblTotalTeks.setText("Total pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblTotalTeks, gridBagConstraints);
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(31, 94, 219));
        lblTotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlRingkasan.add(lblTotal, gridBagConstraints);
        btnBuatPesanan.setBackground(new java.awt.Color(31, 94, 219));
        btnBuatPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnBuatPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuatPesanan.setFocusPainted(false);
        btnBuatPesanan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuatPesanan.setForeground(new java.awt.Color(255, 255, 255));
        btnBuatPesanan.setPreferredSize(new java.awt.Dimension(290, 42));
        btnBuatPesanan.setText("Buat Pesanan");
        btnBuatPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuatPesananActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        pnlRingkasan.add(btnBuatPesanan, gridBagConstraints);
        btnKembali.setBackground(new java.awt.Color(255, 255, 255));
        btnKembali.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnKembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKembali.setFocusPainted(false);
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(16, 24, 40));
        btnKembali.setPreferredSize(new java.awt.Dimension(120, 40));
        btnKembali.setText("Kembali ke Keranjang");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlRingkasan.add(btnKembali, gridBagConstraints);
        pnlKanan.add(pnlRingkasan, java.awt.BorderLayout.NORTH);

        add(pnlKanan, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVoucherActionPerformed
        if (!siap) return;
        int i = cmbVoucher.getSelectedIndex();
        try {
            pesanan.pakaiVoucher(i <= 0 ? null : voucher.get(i - 1));
            Promo dipakai = pesanan.getKeranjang().getPromo();
            infoVoucher(dipakai == null ? " " : "Voucher " + dipakai.getKode() + " dipakai. Hemat "
                    + Gaya.rupiah(pesanan.getKeranjang().getPotongan()) + ".", Gaya.HIJAU);
        } catch (ValidasiException e) {
            infoVoucher(e.getMessage(), Gaya.MERAH);
            siap = false;
            cmbVoucher.setSelectedIndex(0);
            siap = true;
        }
        hitung();
    }//GEN-LAST:event_cmbVoucherActionPerformed

    private void btnBuatPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuatPesananActionPerformed
        String metode = metode();
        if (metode == null) {
            Gaya.error(lblErrMetode, "Pilih metode pembayaran.");
            return;
        }
        Gaya.error(lblErrMetode, null);
        String total = Gaya.rupiah(pesanan.getKeranjang().hitungTotal(Konfigurasi.ONGKOS_KIRIM));
        if (!KonfirmasiDialog.tanya(this, "Buat pesanan", "Total pembayaran <b>" + total + "</b> dengan metode "
                + Gaya.metode(metode) + ". Yakin buat pesanan?", "Buat Pesanan", "dialog_tanya", false)) {
            return;
        }
        try {
            Pesanan baru = pesanan.checkout(metode);
            induk.perbaruiKeranjang();
            boolean lunas = PembayaranDialog.bayar(this, baru);
            induk.buka("riwayat");
            if (lunas) {
                NotifikasiDialog.tampil(this, "Pembayaran berhasil", "Pesanan " + baru.getNomor() + " sedang diproses");
            } else {
                NotifikasiDialog.tampil(this, "Pesanan berhasil dibuat", "Bayar pesanan " + baru.getNomor() + " dari Riwayat Pesanan");
            }
        } catch (ValidasiException e) {
            KonfirmasiDialog.gagal(this, e.getMessage());
            muat();
        }
    }//GEN-LAST:event_btnBuatPesananActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        induk.buka("keranjang");
    }//GEN-LAST:event_btnKembaliActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuatPesanan;
    private javax.swing.JButton btnKembali;
    private javax.swing.JComboBox<String> cmbVoucher;
    private javax.swing.ButtonGroup grpMetode;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAlamatJudul;
    private javax.swing.JLabel lblBarangJudul;
    private javax.swing.JLabel lblErrMetode;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblMetodeJudul;
    private javax.swing.JLabel lblOngkir;
    private javax.swing.JLabel lblOngkirTeks;
    private javax.swing.JLabel lblPenerima;
    private javax.swing.JLabel lblPotongan;
    private javax.swing.JLabel lblPotonganTeks;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalTeks;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTeks;
    private javax.swing.JLabel lblUbahAlamat;
    private javax.swing.JLabel lblVoucherInfo;
    private javax.swing.JLabel lblVoucherJudul;
    private javax.swing.JPanel pnlAlamat;
    private javax.swing.JPanel pnlBarang;
    private javax.swing.JPanel pnlKanan;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKiri;
    private javax.swing.JPanel pnlKiriSisa;
    private javax.swing.JPanel pnlMetode;
    private javax.swing.JPanel pnlMetodeSisa;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JPanel pnlVoucher;
    private javax.swing.JRadioButton rbEwallet;
    private javax.swing.JRadioButton rbTransfer;
    private javax.swing.JScrollPane scrBarang;
    private javax.swing.JScrollPane scrKiri;
    private javax.swing.JSeparator sepTotal;
    private javax.swing.JTable tblBarang;
    // End of variables declaration//GEN-END:variables
}
