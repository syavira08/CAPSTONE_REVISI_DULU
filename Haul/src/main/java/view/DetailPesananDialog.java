package view;

import config.Konfigurasi;
import controller.PesananController;
import controller.ValidasiException;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/** Detail pesanan dalam satu tampilan (pengganti Lacak Pesanan). Dipakai pelanggan & admin. */
public class DetailPesananDialog extends javax.swing.JDialog {

    private final PesananController controller = new PesananController();
    private Pesanan pesanan;

    public DetailPesananDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        Gaya.tabel(tblBarang);
        Gaya.lebarKolom(tblBarang, 300, 120, 70, 130);
    }

    /** @param pelanggan true = dibuka pelanggan (bisa konfirmasi diterima), false = dibuka admin */
    public static void tampil(java.awt.Component asal, Pesanan p, boolean pelanggan) {
        DetailPesananDialog d = new DetailPesananDialog(Gaya.jendela(asal));
        d.pesanan = p;
        d.isi(pelanggan);
        d.pack();
        d.setLocationRelativeTo(asal);
        d.setVisible(true);
    }

    private void isi(boolean pelanggan) {
        Pesanan p = pesanan;
        lblNomor.setText(p.getNomor() + " \u2022 " + Gaya.tanggal(p.getTanggal()));
        Gaya.badge(lblStatus, pelanggan ? Gaya.statusPelanggan(p) : p.getTahap().toString());
        lblPenerima.setText(p.getPelanggan().getNama() + " \u2022 " + p.getPelanggan().getNoTelepon());
        lblAlamat.setText("<html><div width=180>" + p.getPelanggan().getAlamat() + "</div></html>");
        lblMetode.setText(Gaya.metode(p.getMetodeBayar()));
        lblBayar.setText(p.isLunas() ? "Lunas" : "Belum dibayar");
        lblResi.setText(p.getNomorResi() == null ? "Belum dikirim" : p.getNomorResi());
        lblEstimasi.setText(p.getTahap() == Pesanan.Tahap.SELESAI
                ? "Diterima " + Gaya.tanggal(p.getTanggalDiterima())
                : Gaya.tanggal(p.getEstimasiTiba()));

        DefaultTableModel model = (DefaultTableModel) tblBarang.getModel();
        model.setRowCount(0);
        for (Pesanan.Item i : p.getItems()) {
            model.addRow(new Object[]{i.getProduk().getNama(), Gaya.rupiah(i.getProduk().getHarga()),
                i.getJumlah(), Gaya.rupiah(i.getSubtotal())});
        }
        lblSubtotal.setText(Gaya.rupiah(p.getSubtotal()));
        lblOngkir.setText(Gaya.rupiah(Konfigurasi.ONGKOS_KIRIM));
        lblPotongan.setText("-" + Gaya.rupiah(p.getPotongan()));
        lblTotal.setText(Gaya.rupiah(p.getTotalHarga()));
        btnDiterima.setVisible(pelanggan && p.getTahap() == Pesanan.Tahap.DIKIRIM);
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
        lblNomor = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlInfo = new javax.swing.JPanel();
        lblPenerimaTeks = new javax.swing.JLabel();
        lblPenerima = new javax.swing.JLabel();
        lblMetodeTeks = new javax.swing.JLabel();
        lblMetode = new javax.swing.JLabel();
        lblResiTeks = new javax.swing.JLabel();
        lblResi = new javax.swing.JLabel();
        lblAlamatTeks = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBayarTeks = new javax.swing.JLabel();
        lblBayar = new javax.swing.JLabel();
        lblEstimasiTeks = new javax.swing.JLabel();
        lblEstimasi = new javax.swing.JLabel();
        scrBarang = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        pnlTotal = new javax.swing.JPanel();
        lblSubtotalTeks = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblOngkirTeks = new javax.swing.JLabel();
        lblOngkir = new javax.swing.JLabel();
        lblPotonganTeks = new javax.swing.JLabel();
        lblPotongan = new javax.swing.JLabel();
        lblTotalTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnTutup = new javax.swing.JButton();
        btnDiterima = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Pesanan");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 20, 28));
        pnlLatar.setPreferredSize(new java.awt.Dimension(760, 560));
        pnlLatar.setLayout(new java.awt.BorderLayout());
        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 18, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Detail Pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblNomor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNomor.setForeground(new java.awt.Color(102, 112, 133));
        lblNomor.setText("Nomor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblNomor, gridBagConstraints);
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(16, 24, 40));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setPreferredSize(new java.awt.Dimension(150, 28));
        lblStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlKepala.add(lblStatus, gridBagConstraints);
        pnlLatar.add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlIsi.setBackground(new java.awt.Color(255, 255, 255));
        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.GridBagLayout());
        pnlInfo.setBackground(new java.awt.Color(245, 247, 251));
        pnlInfo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18)));
        pnlInfo.setLayout(new java.awt.GridBagLayout());
        lblPenerimaTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPenerimaTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblPenerimaTeks.setText("Penerima");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        pnlInfo.add(lblPenerimaTeks, gridBagConstraints);
        lblPenerima.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPenerima.setForeground(new java.awt.Color(16, 24, 40));
        lblPenerima.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlInfo.add(lblPenerima, gridBagConstraints);
        lblMetodeTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMetodeTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblMetodeTeks.setText("Metode pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 0);
        pnlInfo.add(lblMetodeTeks, gridBagConstraints);
        lblMetode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(16, 24, 40));
        lblMetode.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 24, 0, 0);
        pnlInfo.add(lblMetode, gridBagConstraints);
        lblResiTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblResiTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblResiTeks.setText("Nomor resi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 0);
        pnlInfo.add(lblResiTeks, gridBagConstraints);
        lblResi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblResi.setForeground(new java.awt.Color(16, 24, 40));
        lblResi.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 24, 0, 0);
        pnlInfo.add(lblResi, gridBagConstraints);
        lblAlamatTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAlamatTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblAlamatTeks.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlInfo.add(lblAlamatTeks, gridBagConstraints);
        lblAlamat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(16, 24, 40));
        lblAlamat.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlInfo.add(lblAlamat, gridBagConstraints);
        lblBayarTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBayarTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblBayarTeks.setText("Pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(14, 24, 0, 0);
        pnlInfo.add(lblBayarTeks, gridBagConstraints);
        lblBayar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBayar.setForeground(new java.awt.Color(16, 24, 40));
        lblBayar.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 24, 0, 0);
        pnlInfo.add(lblBayar, gridBagConstraints);
        lblEstimasiTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEstimasiTeks.setForeground(new java.awt.Color(52, 64, 84));
        lblEstimasiTeks.setText("Estimasi tiba");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(14, 24, 0, 0);
        pnlInfo.add(lblEstimasiTeks, gridBagConstraints);
        lblEstimasi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEstimasi.setForeground(new java.awt.Color(16, 24, 40));
        lblEstimasi.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(4, 24, 0, 0);
        pnlInfo.add(lblEstimasi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlIsi.add(pnlInfo, gridBagConstraints);

        scrBarang.setBackground(new java.awt.Color(255, 255, 255));
        scrBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        scrBarang.setPreferredSize(new java.awt.Dimension(600, 150));
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
        gridBagConstraints.weighty = 1.0;
        pnlIsi.add(scrBarang, gridBagConstraints);
        pnlTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlTotal.setOpaque(false);
        pnlTotal.setLayout(new java.awt.GridBagLayout());
        lblSubtotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtotalTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblSubtotalTeks.setText("Subtotal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTotal.add(lblSubtotalTeks, gridBagConstraints);
        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(16, 24, 40));
        lblSubtotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 24, 0, 0);
        pnlTotal.add(lblSubtotal, gridBagConstraints);
        lblOngkirTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblOngkirTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblOngkirTeks.setText("Ongkos kirim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTotal.add(lblOngkirTeks, gridBagConstraints);
        lblOngkir.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOngkir.setForeground(new java.awt.Color(16, 24, 40));
        lblOngkir.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 24, 0, 0);
        pnlTotal.add(lblOngkir, gridBagConstraints);
        lblPotonganTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPotonganTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblPotonganTeks.setText("Potongan voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTotal.add(lblPotonganTeks, gridBagConstraints);
        lblPotongan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPotongan.setForeground(new java.awt.Color(18, 128, 92));
        lblPotongan.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 24, 0, 0);
        pnlTotal.add(lblPotongan, gridBagConstraints);
        lblTotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTotalTeks.setForeground(new java.awt.Color(102, 112, 133));
        lblTotalTeks.setText("Total pembayaran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTotal.add(lblTotalTeks, gridBagConstraints);
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(31, 94, 219));
        lblTotal.setText("Rp0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 24, 0, 0);
        pnlTotal.add(lblTotal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlIsi.add(pnlTotal, gridBagConstraints);

        pnlLatar.add(pnlIsi, java.awt.BorderLayout.CENTER);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnTutup.setBackground(new java.awt.Color(255, 255, 255));
        btnTutup.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnTutup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTutup.setFocusPainted(false);
        btnTutup.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTutup.setForeground(new java.awt.Color(16, 24, 40));
        btnTutup.setPreferredSize(new java.awt.Dimension(100, 36));
        btnTutup.setText("Tutup");
        btnTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupActionPerformed(evt);
            }
        });
        pnlTombol.add(btnTutup);
        btnDiterima.setBackground(new java.awt.Color(31, 94, 219));
        btnDiterima.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnDiterima.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDiterima.setFocusPainted(false);
        btnDiterima.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDiterima.setForeground(new java.awt.Color(255, 255, 255));
        btnDiterima.setPreferredSize(new java.awt.Dimension(210, 36));
        btnDiterima.setText("Pesanan Sudah Diterima");
        btnDiterima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiterimaActionPerformed(evt);
            }
        });
        pnlTombol.add(btnDiterima);
        pnlLatar.add(pnlTombol, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    private void btnDiterimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiterimaActionPerformed
        if (KonfirmasiDialog.tanya(this, "Konfirmasi penerimaan", "Apakah pesanan <b>" + pesanan.getNomor()
                + "</b> sudah Anda terima dengan baik? Status pesanan akan menjadi Selesai.", "Ya, Sudah Diterima",
                "dialog_sukses", false)) {
            try {
                controller.konfirmasiDiterima(pesanan);
                isi(true);
                NotifikasiDialog.tampil(this, "Pesanan selesai", "Terima kasih telah berbelanja di HAULECTRA");
            } catch (ValidasiException e) {
                KonfirmasiDialog.gagal(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDiterimaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiterima;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAlamatTeks;
    private javax.swing.JLabel lblBayar;
    private javax.swing.JLabel lblBayarTeks;
    private javax.swing.JLabel lblEstimasi;
    private javax.swing.JLabel lblEstimasiTeks;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblMetodeTeks;
    private javax.swing.JLabel lblNomor;
    private javax.swing.JLabel lblOngkir;
    private javax.swing.JLabel lblOngkirTeks;
    private javax.swing.JLabel lblPenerima;
    private javax.swing.JLabel lblPenerimaTeks;
    private javax.swing.JLabel lblPotongan;
    private javax.swing.JLabel lblPotonganTeks;
    private javax.swing.JLabel lblResi;
    private javax.swing.JLabel lblResiTeks;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalTeks;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTeks;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JScrollPane scrBarang;
    private javax.swing.JTable tblBarang;
    // End of variables declaration//GEN-END:variables
}
