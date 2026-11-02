package view;

import config.Konfigurasi;
import controller.PelangganController;
import controller.PesananController;
import controller.ValidasiException;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import model.Pelanggan;
import model.Pesanan;

/**
 * Flowchart Checkout & Pembayaran:
 *  ringkasan barang -> pakai voucher? (validasi) -> hitung total -> pilih metode bayar
 *  -> konfirmasi -> simpan pesanan, detail, pembayaran -> tampilkan nomor pesanan & status pembayaran.
 * Tampilan dari Form Builder (CheckoutPanel.form): kartu "form" dan kartu "berhasil" (CardLayout).
 */
public class CheckoutPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final PesananController controller = new PesananController();
    private final DefaultTableModel model;
    private String metode = "TRANSFER";

    public CheckoutPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        model = (DefaultTableModel) tblItem.getModel();
        UI.tabel(tblItem, scrItem);
        UI.input(txtVoucher, "Kode voucher");
        UI.combo(cmbSaluran);
        UI.sekunder(btnKembali);
        UI.sekunder(btnTerapkan);
        UI.primer(btnKonfirmasi);
        UI.primer(btnRiwayat);
        UI.sekunder(btnKatalog);
        UI.kartu(pnlAlamat);
        UI.kartu(pnlMetode);
        UI.kartu(pnlVoucher);
        UI.kartu(pnlRingkasan);
        UI.kartu(pnlDetailBayar);
        UI.sorotan(pnlInfoBayar, 10, 14, 10, 14);
        UI.info(pnlInfoBayar, lblInfoBayar, "Transfer sesuai nominal. Status diperbarui setelah pembayaran diverifikasi admin.");
        UI.ikon(lblCek, "cek", 44, UI.BIRU);
        UI.sembunyi(pnlPesan);
        UI.sembunyi(pnlPesanVoucher);
        pilihMetode(metode);
        UI.lebarTetap(pnlKananWrap, 330);
        UI.tema(this);
    }

    @Override
    public void muat() {
        lblJudul.setText("Checkout");
        lblSubjudul.setText("Keranjang  /  Checkout  /  Pembayaran");
        pnlKepala.setVisible(true);
        ((CardLayout) pnlHalaman.getLayout()).show(pnlHalaman, "form");

        Pesanan k = controller.getKeranjang();
        Pelanggan saya = new PelangganController().profilSaya();

        lblAlamatNama.setText(saya.getNama() + "  •  " + UI.telepon(saya.getNoTelepon()));
        lblAlamatDetail.setText(saya.getAlamat());
        lblAlamatOngkir.setText("Pengiriman reguler • Ongkos kirim " + UI.rupiah(Konfigurasi.ONGKOS_KIRIM));

        model.setRowCount(0);
        for (Pesanan.Item i : k.getItems()) {
            model.addRow(new Object[]{i.getProduk().getNama(), i.getJumlah(), UI.rupiah(i.getSubtotal())});
        }
        UI.sesuaikanTinggi(scrItem, tblItem);

        pilihMetode(metode);

        lblSubLabel.setText("Subtotal (" + k.getJumlahBarang() + " barang)");
        lblSubNilai.setText(UI.rupiah(k.getSubtotal()));
        lblOngkirNilai.setText(UI.rupiah(Konfigurasi.ONGKOS_KIRIM));
        pnlPromo.setVisible(k.getPromo() != null);
        if (k.getPromo() != null) {
            lblPromoLabel.setText("Voucher " + k.getPromo().getKode());
            lblPromoLabel.setForeground(UI.BIRU);
            lblPromoNilai.setText("−" + UI.rupiah(k.getPotongan()));
            lblPromoNilai.setForeground(UI.BIRU);
        }
        lblTotalNilai.setText(UI.rupiah(k.hitungTotal(Konfigurasi.ONGKOS_KIRIM)));
    }

    /** Dua pilihan metode (Transfer bank / E-wallet) yang tampil seperti tab. */
    private void pilihMetode(String m) {
        metode = m;
        UI.tombol(btnTransfer, m.equals("TRANSFER") ? UI.Jenis.PRIMER : UI.Jenis.SEKUNDER);
        UI.tombol(btnEwallet, m.equals("E_WALLET") ? UI.Jenis.PRIMER : UI.Jenis.SEKUNDER);
        lblSaluranLabel.setText(m.equals("TRANSFER") ? "Bank tujuan" : "E-wallet tujuan");
        lblSaluranCatatan.setText(m.equals("TRANSFER") ? "Pembayaran lewat Virtual Account bank pilihan Anda."
                : "E-wallet tersedia: GoPay, OVO, dan DANA");
        cmbSaluran.removeAllItems();
        String[] saluran = m.equals("TRANSFER") ? new String[]{"BCA", "BNI", "Mandiri"} : new String[]{"GoPay", "OVO", "DANA"};
        for (String s : saluran) cmbSaluran.addItem(s);
        pnlPilihMetode.repaint();
    }

    /** Flowchart: "Masukkan kode dan validasi voucher" -> "Sistem menghitung total akhir". */
    private void pakaiVoucher() {
        try {
            controller.pakaiVoucher(txtVoucher.getText());
            Pesanan k = controller.getKeranjang();
            if (k.getPromo() == null) {
                UI.sembunyi(pnlPesanVoucher);
            } else {
                UI.pesan(pnlPesanVoucher, lblPesanVoucher, UI.Pesan.SUKSES,
                        "Voucher berhasil diterapkan. Hemat " + UI.rupiah(k.getPotongan()) + ".");
            }
        } catch (ValidasiException e) {
            UI.pesan(pnlPesanVoucher, lblPesanVoucher, UI.Pesan.GALAT, e.getMessage());
        }
        muat();
    }

    private void konfirmasi() {
        String saluran = (String) cmbSaluran.getSelectedItem();
        Pesanan[] hasil = new Pesanan[1];
        if (coba(() -> hasil[0] = controller.checkout(metode))) {
            txtVoucher.setText("");
            UI.sembunyi(pnlPesanVoucher);
            tampilkanBerhasil(hasil[0], saluran);
        }
    }

    /** Halaman "Pesanan berhasil dibuat": nomor pesanan & status pembayaran. */
    private void tampilkanBerhasil(Pesanan p, String saluran) {
        pnlKepala.setVisible(false);
        String nomorBayar = metode.equals("TRANSFER")
                ? "8800 " + UI.telepon(p.getPelanggan().getNoTelepon().substring(4)) + " " + String.format("%03d", p.getId())
                : UI.telepon(p.getPelanggan().getNoTelepon());
        lblBerhasilSub.setText("Terima kasih, " + p.getPelanggan().getNama()
                + ". Selesaikan pembayaran agar pesanan segera diproses.");
        UI.badge(lblBadgeStatus, p.getTahap().toString());
        lblNomorPesanNilai.setText(p.getNomor());
        lblBatasNilai.setText(UI.tanggal(p.getTanggal().plusHours(Konfigurasi.BATAS_BAYAR_JAM)));
        lblSaluran.setText(saluran + (metode.equals("TRANSFER") ? " Virtual Account" : " (nomor tujuan)"));
        lblNomorBayar.setText(nomorBayar);
        lblTotalBayarNilai.setText(UI.rupiah(p.getTotalHarga()));
        ((CardLayout) pnlHalaman.getLayout()).show(pnlHalaman, "berhasil");
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
        btnKembali = new javax.swing.JButton();
        pnlPesan = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlKonten = new javax.swing.JPanel();
        pnlHalaman = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        pnlKiri = new javax.swing.JPanel();
        pnlAlamat = new javax.swing.JPanel();
        lblAlamatJudul = new javax.swing.JLabel();
        lblAlamatNama = new javax.swing.JLabel();
        lblAlamatDetail = new javax.swing.JLabel();
        lblAlamatOngkir = new javax.swing.JLabel();
        scrItem = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        pnlMetode = new javax.swing.JPanel();
        lblMetodeJudul = new javax.swing.JLabel();
        pnlPilihMetode = new javax.swing.JPanel();
        btnTransfer = new javax.swing.JButton();
        btnEwallet = new javax.swing.JButton();
        lblSaluranLabel = new javax.swing.JLabel();
        cmbSaluran = new javax.swing.JComboBox<>();
        lblSaluranCatatan = new javax.swing.JLabel();
        pnlKananWrap = new javax.swing.JPanel();
        pnlKanan = new javax.swing.JPanel();
        pnlVoucher = new javax.swing.JPanel();
        lblVoucherJudul = new javax.swing.JLabel();
        pnlKodeVoucher = new javax.swing.JPanel();
        lblKodeVoucher = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        btnTerapkan = new javax.swing.JButton();
        pnlPesanVoucher = new javax.swing.JPanel();
        lblPesanVoucher = new javax.swing.JLabel();
        pnlRingkasan = new javax.swing.JPanel();
        lblRingkasanJudul = new javax.swing.JLabel();
        pnlSub = new javax.swing.JPanel();
        lblSubLabel = new javax.swing.JLabel();
        lblSubNilai = new javax.swing.JLabel();
        pnlOngkir = new javax.swing.JPanel();
        lblOngkirLabel = new javax.swing.JLabel();
        lblOngkirNilai = new javax.swing.JLabel();
        pnlPromo = new javax.swing.JPanel();
        lblPromoLabel = new javax.swing.JLabel();
        lblPromoNilai = new javax.swing.JLabel();
        pnlGarisTotal = new javax.swing.JPanel();
        pnlTotal = new javax.swing.JPanel();
        lblTotalLabel = new javax.swing.JLabel();
        lblTotalNilai = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();
        lblSetuju = new javax.swing.JLabel();
        pnlBerhasil = new javax.swing.JPanel();
        pnlBerhasilIsi = new javax.swing.JPanel();
        lblCek = new javax.swing.JLabel();
        lblBerhasilJudul = new javax.swing.JLabel();
        lblBerhasilSub = new javax.swing.JLabel();
        pnlBadgeStatus = new javax.swing.JPanel();
        lblBadgeStatus = new javax.swing.JLabel();
        pnlDetailBayar = new javax.swing.JPanel();
        lblDetailJudul = new javax.swing.JLabel();
        pnlNomorPesan = new javax.swing.JPanel();
        lblNomorPesanLabel = new javax.swing.JLabel();
        lblNomorPesanNilai = new javax.swing.JLabel();
        pnlBatas = new javax.swing.JPanel();
        lblBatasLabel = new javax.swing.JLabel();
        lblBatasNilai = new javax.swing.JLabel();
        lblSaluran = new javax.swing.JLabel();
        lblNomorBayar = new javax.swing.JLabel();
        pnlTotalBayar = new javax.swing.JPanel();
        lblTotalBayarLabel = new javax.swing.JLabel();
        lblTotalBayarNilai = new javax.swing.JLabel();
        pnlInfoBayar = new javax.swing.JPanel();
        lblInfoBayar = new javax.swing.JLabel();
        pnlTombolBerhasil = new javax.swing.JPanel();
        btnRiwayat = new javax.swing.JButton();
        btnKatalog = new javax.swing.JButton();

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
        lblJudul.setText("Checkout");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);

        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Keranjang  /  Checkout  /  Pembayaran");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);

        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);

        pnlAksiKepala.setOpaque(false);
        pnlAksiKepala.setLayout(new java.awt.BorderLayout());

        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(23, 43, 77));
        btnKembali.setText("Kembali ke keranjang");
        btnKembali.setFocusPainted(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        pnlAksiKepala.add(btnKembali, java.awt.BorderLayout.NORTH);

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
        pnlKonten.setLayout(new java.awt.BorderLayout());

        pnlHalaman.setOpaque(false);
        pnlHalaman.setLayout(new java.awt.CardLayout());

        pnlForm.setOpaque(false);
        pnlForm.setLayout(new java.awt.BorderLayout(20, 0));

        pnlKiri.setOpaque(false);
        pnlKiri.setLayout(new java.awt.GridBagLayout());

        pnlAlamat.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlAlamat.setLayout(new java.awt.GridBagLayout());

        lblAlamatJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAlamatJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatJudul.setText("Alamat pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlAlamat.add(lblAlamatJudul, gridBagConstraints);

        lblAlamatNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblAlamatNama.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatNama.setText("Alif Anugrah  •  0812 3456 7801");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlAlamat.add(lblAlamatNama, gridBagConstraints);

        lblAlamatDetail.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatDetail.setText("Jl. Pangeran Antasari No. 10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlAlamat.add(lblAlamatDetail, gridBagConstraints);

        lblAlamatOngkir.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblAlamatOngkir.setForeground(new java.awt.Color(94, 108, 132));
        lblAlamatOngkir.setText("Pengiriman reguler • Ongkos kirim Rp20.000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlAlamat.add(lblAlamatOngkir, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKiri.add(pnlAlamat, gridBagConstraints);

        scrItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrItem.setPreferredSize(new java.awt.Dimension(500, 132));
        scrItem.setBackground(new java.awt.Color(255, 255, 255));

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produk", "Jumlah", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItem.setRowHeight(44);
        scrItem.setViewportView(tblItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKiri.add(scrItem, gridBagConstraints);

        pnlMetode.setBackground(new java.awt.Color(255, 255, 255));
        pnlMetode.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlMetode.setLayout(new java.awt.GridBagLayout());

        lblMetodeJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMetodeJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblMetodeJudul.setText("Metode pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlMetode.add(lblMetodeJudul, gridBagConstraints);

        pnlPilihMetode.setOpaque(false);
        pnlPilihMetode.setLayout(new java.awt.GridLayout(1, 2, 12, 0));

        btnTransfer.setBackground(new java.awt.Color(33, 97, 213));
        btnTransfer.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTransfer.setForeground(new java.awt.Color(255, 255, 255));
        btnTransfer.setText("Transfer bank");
        btnTransfer.setFocusPainted(false);
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });
        pnlPilihMetode.add(btnTransfer);

        btnEwallet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEwallet.setForeground(new java.awt.Color(23, 43, 77));
        btnEwallet.setText("E-wallet");
        btnEwallet.setFocusPainted(false);
        btnEwallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEwalletActionPerformed(evt);
            }
        });
        pnlPilihMetode.add(btnEwallet);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlMetode.add(pnlPilihMetode, gridBagConstraints);

        lblSaluranLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSaluranLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblSaluranLabel.setText("Bank tujuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlMetode.add(lblSaluranLabel, gridBagConstraints);

        cmbSaluran.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbSaluran.setForeground(new java.awt.Color(23, 43, 77));
        cmbSaluran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BCA", "BNI", "Mandiri" }));
        cmbSaluran.setPreferredSize(new java.awt.Dimension(190, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlMetode.add(cmbSaluran, gridBagConstraints);

        lblSaluranCatatan.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSaluranCatatan.setForeground(new java.awt.Color(94, 108, 132));
        lblSaluranCatatan.setText("E-wallet tersedia: GoPay, OVO, dan DANA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlMetode.add(lblSaluranCatatan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKiri.add(pnlMetode, gridBagConstraints);

        pnlForm.add(pnlKiri, java.awt.BorderLayout.CENTER);

        pnlKananWrap.setPreferredSize(new java.awt.Dimension(330, 10));
        pnlKananWrap.setOpaque(false);
        pnlKananWrap.setLayout(new java.awt.BorderLayout());

        pnlKanan.setOpaque(false);
        pnlKanan.setLayout(new java.awt.GridBagLayout());

        pnlVoucher.setBackground(new java.awt.Color(255, 255, 255));
        pnlVoucher.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlVoucher.setLayout(new java.awt.GridBagLayout());

        lblVoucherJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblVoucherJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblVoucherJudul.setText("Voucher belanja");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlVoucher.add(lblVoucherJudul, gridBagConstraints);

        pnlKodeVoucher.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlKodeVoucher.setOpaque(false);
        pnlKodeVoucher.setLayout(new java.awt.GridBagLayout());

        lblKodeVoucher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKodeVoucher.setForeground(new java.awt.Color(23, 43, 77));
        lblKodeVoucher.setText("Kode voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKodeVoucher.add(lblKodeVoucher, gridBagConstraints);

        txtVoucher.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtVoucher.setForeground(new java.awt.Color(23, 43, 77));
        txtVoucher.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtVoucher.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlKodeVoucher.add(txtVoucher, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlVoucher.add(pnlKodeVoucher, gridBagConstraints);

        btnTerapkan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTerapkan.setForeground(new java.awt.Color(23, 43, 77));
        btnTerapkan.setText("Terapkan");
        btnTerapkan.setFocusPainted(false);
        btnTerapkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerapkanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlVoucher.add(btnTerapkan, gridBagConstraints);

        pnlPesanVoucher.setBackground(new java.awt.Color(234, 246, 236));
        pnlPesanVoucher.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlPesanVoucher.setLayout(new java.awt.BorderLayout());

        lblPesanVoucher.setForeground(new java.awt.Color(46, 125, 50));
        lblPesanVoucher.setText("Voucher berhasil diterapkan.");
        pnlPesanVoucher.add(lblPesanVoucher, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlVoucher.add(pnlPesanVoucher, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKanan.add(pnlVoucher, gridBagConstraints);

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

        pnlPromo.setOpaque(false);
        pnlPromo.setLayout(new java.awt.BorderLayout(12, 0));

        lblPromoLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPromoLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblPromoLabel.setText("Voucher HEMAT10000");
        pnlPromo.add(lblPromoLabel, java.awt.BorderLayout.CENTER);

        lblPromoNilai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPromoNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblPromoNilai.setText("−Rp10.000");
        lblPromoNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlPromo.add(lblPromoNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlPromo, gridBagConstraints);

        pnlGarisTotal.setBackground(new java.awt.Color(220, 227, 237));
        pnlGarisTotal.setPreferredSize(new java.awt.Dimension(10, 1));
        pnlGarisTotal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        lblTotalNilai.setText("Rp510.000");
        lblTotalNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlTotal.add(lblTotalNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(pnlTotal, gridBagConstraints);

        btnKonfirmasi.setBackground(new java.awt.Color(33, 97, 213));
        btnKonfirmasi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setText("Konfirmasi Pesanan");
        btnKonfirmasi.setFocusPainted(false);
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(btnKonfirmasi, gridBagConstraints);

        lblSetuju.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSetuju.setForeground(new java.awt.Color(94, 108, 132));
        lblSetuju.setText("<html><div width=270>Dengan mengonfirmasi, Anda menyetujui detail pesanan.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlRingkasan.add(lblSetuju, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKanan.add(pnlRingkasan, gridBagConstraints);

        pnlKananWrap.add(pnlKanan, java.awt.BorderLayout.NORTH);

        pnlForm.add(pnlKananWrap, java.awt.BorderLayout.EAST);

        pnlHalaman.add(pnlForm, "form");

        pnlBerhasil.setOpaque(false);
        pnlBerhasil.setLayout(new java.awt.GridBagLayout());

        pnlBerhasilIsi.setPreferredSize(new java.awt.Dimension(560, 480));
        pnlBerhasilIsi.setOpaque(false);
        pnlBerhasilIsi.setLayout(new java.awt.GridBagLayout());

        lblCek.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCek.setForeground(new java.awt.Color(23, 43, 77));
        lblCek.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlBerhasilIsi.add(lblCek, gridBagConstraints);

        lblBerhasilJudul.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblBerhasilJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblBerhasilJudul.setText("Pesanan berhasil dibuat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlBerhasilIsi.add(lblBerhasilJudul, gridBagConstraints);

        lblBerhasilSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBerhasilSub.setForeground(new java.awt.Color(94, 108, 132));
        lblBerhasilSub.setText("Terima kasih. Selesaikan pembayaran agar pesanan segera diproses.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlBerhasilIsi.add(lblBerhasilSub, gridBagConstraints);

        pnlBadgeStatus.setOpaque(false);
        pnlBadgeStatus.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        lblBadgeStatus.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBadgeStatus.setForeground(new java.awt.Color(138, 90, 0));
        lblBadgeStatus.setText("Menunggu Pembayaran");
        pnlBadgeStatus.add(lblBadgeStatus);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlBerhasilIsi.add(pnlBadgeStatus, gridBagConstraints);

        pnlDetailBayar.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetailBayar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlDetailBayar.setLayout(new java.awt.GridBagLayout());

        lblDetailJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDetailJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblDetailJudul.setText("Detail pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDetailBayar.add(lblDetailJudul, gridBagConstraints);

        pnlNomorPesan.setOpaque(false);
        pnlNomorPesan.setLayout(new java.awt.BorderLayout(12, 0));

        lblNomorPesanLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNomorPesanLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblNomorPesanLabel.setText("Nomor pesanan");
        pnlNomorPesan.add(lblNomorPesanLabel, java.awt.BorderLayout.CENTER);

        lblNomorPesanNilai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNomorPesanNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblNomorPesanNilai.setText("HLC-261102-001");
        lblNomorPesanNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlNomorPesan.add(lblNomorPesanNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(pnlNomorPesan, gridBagConstraints);

        pnlBatas.setOpaque(false);
        pnlBatas.setLayout(new java.awt.BorderLayout(12, 0));

        lblBatasLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBatasLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblBatasLabel.setText("Bayar sebelum");
        pnlBatas.add(lblBatasLabel, java.awt.BorderLayout.CENTER);

        lblBatasNilai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBatasNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblBatasNilai.setText("03 Nov 2026, 10.00");
        lblBatasNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlBatas.add(lblBatasNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(pnlBatas, gridBagConstraints);

        lblSaluran.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSaluran.setForeground(new java.awt.Color(94, 108, 132));
        lblSaluran.setText("BCA Virtual Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(lblSaluran, gridBagConstraints);

        lblNomorBayar.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNomorBayar.setForeground(new java.awt.Color(33, 97, 213));
        lblNomorBayar.setText("8800 3456 7801 001");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(lblNomorBayar, gridBagConstraints);

        pnlTotalBayar.setOpaque(false);
        pnlTotalBayar.setLayout(new java.awt.BorderLayout(12, 0));

        lblTotalBayarLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotalBayarLabel.setForeground(new java.awt.Color(23, 43, 77));
        lblTotalBayarLabel.setText("Total pembayaran");
        pnlTotalBayar.add(lblTotalBayarLabel, java.awt.BorderLayout.CENTER);

        lblTotalBayarNilai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalBayarNilai.setForeground(new java.awt.Color(33, 97, 213));
        lblTotalBayarNilai.setText("Rp510.000");
        lblTotalBayarNilai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlTotalBayar.add(lblTotalBayarNilai, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(pnlTotalBayar, gridBagConstraints);

        pnlInfoBayar.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfoBayar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfoBayar.setLayout(new java.awt.BorderLayout());

        lblInfoBayar.setForeground(new java.awt.Color(33, 97, 213));
        lblInfoBayar.setText("Transfer sesuai nominal. Status diperbarui setelah pembayaran diverifikasi admin.");
        pnlInfoBayar.add(lblInfoBayar, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDetailBayar.add(pnlInfoBayar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlBerhasilIsi.add(pnlDetailBayar, gridBagConstraints);

        pnlTombolBerhasil.setOpaque(false);
        pnlTombolBerhasil.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        btnRiwayat.setBackground(new java.awt.Color(33, 97, 213));
        btnRiwayat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRiwayat.setForeground(new java.awt.Color(255, 255, 255));
        btnRiwayat.setText("Lihat Riwayat Pesanan");
        btnRiwayat.setFocusPainted(false);
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });
        pnlTombolBerhasil.add(btnRiwayat);

        btnKatalog.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKatalog.setForeground(new java.awt.Color(23, 43, 77));
        btnKatalog.setText("Kembali ke Katalog");
        btnKatalog.setFocusPainted(false);
        btnKatalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKatalogActionPerformed(evt);
            }
        });
        pnlTombolBerhasil.add(btnKatalog);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlBerhasilIsi.add(pnlTombolBerhasil, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        pnlBerhasil.add(pnlBerhasilIsi, gridBagConstraints);

        pnlHalaman.add(pnlBerhasil, "berhasil");

        pnlKonten.add(pnlHalaman, java.awt.BorderLayout.CENTER);

        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);

        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        jendela.buka(jendela.keranjang);
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        pilihMetode("TRANSFER");
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnEwalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEwalletActionPerformed
        pilihMetode("E_WALLET");
    }//GEN-LAST:event_btnEwalletActionPerformed

    private void btnTerapkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerapkanActionPerformed
        pakaiVoucher();
    }//GEN-LAST:event_btnTerapkanActionPerformed

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        konfirmasi();
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiwayatActionPerformed
        jendela.buka(jendela.riwayat);
    }//GEN-LAST:event_btnRiwayatActionPerformed

    private void btnKatalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKatalogActionPerformed
        jendela.buka(jendela.katalog);
    }//GEN-LAST:event_btnKatalogActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEwallet;
    private javax.swing.JButton btnKatalog;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JButton btnTerapkan;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JComboBox<String> cmbSaluran;
    private javax.swing.JLabel lblAlamatDetail;
    private javax.swing.JLabel lblAlamatJudul;
    private javax.swing.JLabel lblAlamatNama;
    private javax.swing.JLabel lblAlamatOngkir;
    private javax.swing.JLabel lblBadgeStatus;
    private javax.swing.JLabel lblBatasLabel;
    private javax.swing.JLabel lblBatasNilai;
    private javax.swing.JLabel lblBerhasilJudul;
    private javax.swing.JLabel lblBerhasilSub;
    private javax.swing.JLabel lblCek;
    private javax.swing.JLabel lblDetailJudul;
    private javax.swing.JLabel lblInfoBayar;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKodeVoucher;
    private javax.swing.JLabel lblMetodeJudul;
    private javax.swing.JLabel lblNomorBayar;
    private javax.swing.JLabel lblNomorPesanLabel;
    private javax.swing.JLabel lblNomorPesanNilai;
    private javax.swing.JLabel lblOngkirLabel;
    private javax.swing.JLabel lblOngkirNilai;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblPesanVoucher;
    private javax.swing.JLabel lblPromoLabel;
    private javax.swing.JLabel lblPromoNilai;
    private javax.swing.JLabel lblRingkasanJudul;
    private javax.swing.JLabel lblSaluran;
    private javax.swing.JLabel lblSaluranCatatan;
    private javax.swing.JLabel lblSaluranLabel;
    private javax.swing.JLabel lblSetuju;
    private javax.swing.JLabel lblSubLabel;
    private javax.swing.JLabel lblSubNilai;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JLabel lblTotalBayarLabel;
    private javax.swing.JLabel lblTotalBayarNilai;
    private javax.swing.JLabel lblTotalLabel;
    private javax.swing.JLabel lblTotalNilai;
    private javax.swing.JLabel lblVoucherJudul;
    private javax.swing.JPanel pnlAksiKepala;
    private javax.swing.JPanel pnlAlamat;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlBadgeStatus;
    private javax.swing.JPanel pnlBatas;
    private javax.swing.JPanel pnlBerhasil;
    private javax.swing.JPanel pnlBerhasilIsi;
    private javax.swing.JPanel pnlDetailBayar;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlGarisTotal;
    private javax.swing.JPanel pnlHalaman;
    private javax.swing.JPanel pnlInfoBayar;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKanan;
    private javax.swing.JPanel pnlKananWrap;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKiri;
    private javax.swing.JPanel pnlKodeVoucher;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlMetode;
    private javax.swing.JPanel pnlNomorPesan;
    private javax.swing.JPanel pnlOngkir;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlPesanVoucher;
    private javax.swing.JPanel pnlPilihMetode;
    private javax.swing.JPanel pnlPromo;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JPanel pnlSub;
    private javax.swing.JPanel pnlTombolBerhasil;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JPanel pnlTotalBayar;
    private javax.swing.JPanel pnlVoucher;
    private javax.swing.JScrollPane scrItem;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtVoucher;
    // End of variables declaration//GEN-END:variables
}
