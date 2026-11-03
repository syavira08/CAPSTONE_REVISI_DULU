package view;

import controller.PromoController;
import controller.ValidasiException;
import model.Promo;

/** Form tambah/ubah promo dengan validasi per isian. */
public class PromoDialog extends javax.swing.JDialog {

    private final PromoController controller = new PromoController();
    private Promo promo;   // null = promo baru
    private boolean tersimpan;

    public PromoDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    public static boolean tampil(java.awt.Component asal, Promo p) {
        PromoDialog d = new PromoDialog(Gaya.jendela(asal));
        d.promo = p;
        if (p != null) {
            d.setTitle("Ubah Promo");
            d.lblJudul.setText("Ubah Promo " + p.getKode());
            d.txtKode.setText(p.getKode());
            (p.getJenis() == 'P' ? d.rbPersen : d.rbNominal).setSelected(true);
            d.txtDiskon.setText(p.getDiskon().stripTrailingZeros().toPlainString());
            d.txtMinimal.setText(String.valueOf(p.getMinimalBarang()));
            d.txtMulai.setText(p.getMulai().format(PromoController.FORMAT_TANGGAL));
            d.txtSelesai.setText(p.getSelesai().format(PromoController.FORMAT_TANGGAL));
        } else {
            d.setTitle("Tambah Promo");
            d.txtMinimal.setText("1");
            d.txtMulai.setText(java.time.LocalDate.now().format(PromoController.FORMAT_TANGGAL));
        }
        d.labelPotongan();
        d.getRootPane().setDefaultButton(d.btnSimpan);
        d.pack();
        d.setLocationRelativeTo(asal);
        d.setVisible(true);
        return d.tersimpan;
    }

    private void labelPotongan() {
        lblDiskon.setText(rbPersen.isSelected() ? "Besar potongan (%), maks. 90" : "Besar potongan (Rp)");
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        grpJenis = new javax.swing.ButtonGroup();
        pnlLatar = new javax.swing.JPanel();
        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        lblKode = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        lblErrKode = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        pnlJenis = new javax.swing.JPanel();
        rbPersen = new javax.swing.JRadioButton();
        lblJarak = new javax.swing.JLabel();
        rbNominal = new javax.swing.JRadioButton();
        lblErrJenis = new javax.swing.JLabel();
        lblDiskon = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        lblErrDiskon = new javax.swing.JLabel();
        lblMinimal = new javax.swing.JLabel();
        txtMinimal = new javax.swing.JTextField();
        lblErrMinimal = new javax.swing.JLabel();
        pnlTanggal = new javax.swing.JPanel();
        pnlMulai = new javax.swing.JPanel();
        lblMulai = new javax.swing.JLabel();
        txtMulai = new javax.swing.JTextField();
        lblErrMulai = new javax.swing.JLabel();
        pnlSelesai = new javax.swing.JPanel();
        lblSelesai = new javax.swing.JLabel();
        txtSelesai = new javax.swing.JTextField();
        lblErrSelesai = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Promo");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlLatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlLatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 20, 28));
        pnlLatar.setLayout(new java.awt.BorderLayout());
        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnlKepala.setOpaque(false);
        pnlKepala.setLayout(new java.awt.GridBagLayout());
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 24, 40));
        lblJudul.setText("Tambah Promo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Voucher aktif otomatis tampil di Beranda pelanggan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);
        pnlLatar.add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setOpaque(false);
        pnlData.setLayout(new java.awt.GridBagLayout());
        lblKode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKode.setForeground(new java.awt.Color(52, 64, 84));
        lblKode.setText("Kode voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblKode, gridBagConstraints);
        txtKode.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtKode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtKode.setForeground(new java.awt.Color(16, 24, 40));
        txtKode.setPreferredSize(new java.awt.Dimension(380, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtKode, gridBagConstraints);
        lblErrKode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrKode.setForeground(new java.awt.Color(217, 45, 32));
        lblErrKode.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrKode.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrKode, gridBagConstraints);
        lblJenis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(52, 64, 84));
        lblJenis.setText("Jenis potongan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblJenis, gridBagConstraints);
        pnlJenis.setBackground(new java.awt.Color(255, 255, 255));
        pnlJenis.setOpaque(false);
        pnlJenis.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        rbPersen.setBackground(new java.awt.Color(255, 255, 255));
        grpJenis.add(rbPersen);
        rbPersen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbPersen.setFocusPainted(false);
        rbPersen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbPersen.setForeground(new java.awt.Color(16, 24, 40));
        rbPersen.setSelected(true);
        rbPersen.setText("Persen (%)");
        rbPersen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPersenActionPerformed(evt);
            }
        });
        pnlJenis.add(rbPersen);
        lblJarak.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJarak.setForeground(new java.awt.Color(16, 24, 40));
        lblJarak.setPreferredSize(new java.awt.Dimension(24, 8));
        lblJarak.setText("");
        pnlJenis.add(lblJarak);
        rbNominal.setBackground(new java.awt.Color(255, 255, 255));
        grpJenis.add(rbNominal);
        rbNominal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbNominal.setFocusPainted(false);
        rbNominal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbNominal.setForeground(new java.awt.Color(16, 24, 40));
        rbNominal.setText("Nominal (Rp)");
        rbNominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNominalActionPerformed(evt);
            }
        });
        pnlJenis.add(rbNominal);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(pnlJenis, gridBagConstraints);

        lblErrJenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrJenis.setForeground(new java.awt.Color(217, 45, 32));
        lblErrJenis.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrJenis.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrJenis, gridBagConstraints);
        lblDiskon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiskon.setForeground(new java.awt.Color(52, 64, 84));
        lblDiskon.setText("Besar potongan (%)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblDiskon, gridBagConstraints);
        txtDiskon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtDiskon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDiskon.setForeground(new java.awt.Color(16, 24, 40));
        txtDiskon.setPreferredSize(new java.awt.Dimension(380, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtDiskon, gridBagConstraints);
        lblErrDiskon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrDiskon.setForeground(new java.awt.Color(217, 45, 32));
        lblErrDiskon.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrDiskon.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrDiskon, gridBagConstraints);
        lblMinimal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMinimal.setForeground(new java.awt.Color(52, 64, 84));
        lblMinimal.setText("Minimal jumlah barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblMinimal, gridBagConstraints);
        txtMinimal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtMinimal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMinimal.setForeground(new java.awt.Color(16, 24, 40));
        txtMinimal.setPreferredSize(new java.awt.Dimension(380, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtMinimal, gridBagConstraints);
        lblErrMinimal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrMinimal.setForeground(new java.awt.Color(217, 45, 32));
        lblErrMinimal.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrMinimal.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrMinimal, gridBagConstraints);
        pnlTanggal.setBackground(new java.awt.Color(255, 255, 255));
        pnlTanggal.setOpaque(false);
        pnlTanggal.setLayout(new java.awt.GridLayout(1, 2, 16, 0));
        pnlMulai.setBackground(new java.awt.Color(255, 255, 255));
        pnlMulai.setOpaque(false);
        pnlMulai.setLayout(new java.awt.GridBagLayout());
        lblMulai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMulai.setForeground(new java.awt.Color(52, 64, 84));
        lblMulai.setText("Tanggal mulai (dd/mm/yyyy)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMulai.add(lblMulai, gridBagConstraints);
        txtMulai.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtMulai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMulai.setForeground(new java.awt.Color(16, 24, 40));
        txtMulai.setPreferredSize(new java.awt.Dimension(180, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlMulai.add(txtMulai, gridBagConstraints);
        lblErrMulai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrMulai.setForeground(new java.awt.Color(217, 45, 32));
        lblErrMulai.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrMulai.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlMulai.add(lblErrMulai, gridBagConstraints);
        pnlTanggal.add(pnlMulai);

        pnlSelesai.setBackground(new java.awt.Color(255, 255, 255));
        pnlSelesai.setOpaque(false);
        pnlSelesai.setLayout(new java.awt.GridBagLayout());
        lblSelesai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSelesai.setForeground(new java.awt.Color(52, 64, 84));
        lblSelesai.setText("Tanggal berakhir (dd/mm/yyyy)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlSelesai.add(lblSelesai, gridBagConstraints);
        txtSelesai.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtSelesai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSelesai.setForeground(new java.awt.Color(16, 24, 40));
        txtSelesai.setPreferredSize(new java.awt.Dimension(180, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlSelesai.add(txtSelesai, gridBagConstraints);
        lblErrSelesai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrSelesai.setForeground(new java.awt.Color(217, 45, 32));
        lblErrSelesai.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrSelesai.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        pnlSelesai.add(lblErrSelesai, gridBagConstraints);
        pnlTanggal.add(pnlSelesai);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlData.add(pnlTanggal, gridBagConstraints);

        pnlLatar.add(pnlData, java.awt.BorderLayout.CENTER);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 0, 0, 0));
        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnBatal.setBackground(new java.awt.Color(255, 255, 255));
        btnBatal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(16, 24, 40));
        btnBatal.setPreferredSize(new java.awt.Dimension(100, 36));
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBatal);
        btnSimpan.setBackground(new java.awt.Color(31, 94, 219));
        btnSimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setPreferredSize(new java.awt.Dimension(120, 36));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlTombol.add(btnSimpan);
        pnlLatar.add(pnlTombol, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlLatar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbPersenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPersenActionPerformed
        labelPotongan();
    }//GEN-LAST:event_rbPersenActionPerformed

    private void rbNominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNominalActionPerformed
        labelPotongan();
    }//GEN-LAST:event_rbNominalActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        for (javax.swing.JLabel l : new javax.swing.JLabel[]{lblErrKode, lblErrJenis, lblErrDiskon, lblErrMinimal,
            lblErrMulai, lblErrSelesai}) {
            Gaya.error(l, null);
        }
        try {
            controller.simpan(promo == null ? 0 : promo.getId(), txtKode.getText(), rbPersen.isSelected() ? 'P' : 'N',
                    txtDiskon.getText(), txtMinimal.getText(), txtMulai.getText(), txtSelesai.getText());
            tersimpan = true;
            dispose();
        } catch (ValidasiException e) {
            Gaya.error(lblErrKode, e.getPesan("kode"));
            Gaya.error(lblErrJenis, e.getPesan("jenis"));
            Gaya.error(lblErrDiskon, e.getPesan("diskon"));
            Gaya.error(lblErrMinimal, e.getPesan("minimal"));
            Gaya.error(lblErrMulai, e.getPesan("mulai"));
            Gaya.error(lblErrSelesai, e.getPesan("selesai"));
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.ButtonGroup grpJenis;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblErrDiskon;
    private javax.swing.JLabel lblErrJenis;
    private javax.swing.JLabel lblErrKode;
    private javax.swing.JLabel lblErrMinimal;
    private javax.swing.JLabel lblErrMulai;
    private javax.swing.JLabel lblErrSelesai;
    private javax.swing.JLabel lblJarak;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblMinimal;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlJenis;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlMulai;
    private javax.swing.JPanel pnlSelesai;
    private javax.swing.JPanel pnlTanggal;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JRadioButton rbNominal;
    private javax.swing.JRadioButton rbPersen;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtMinimal;
    private javax.swing.JTextField txtMulai;
    private javax.swing.JTextField txtSelesai;
    // End of variables declaration//GEN-END:variables
}
