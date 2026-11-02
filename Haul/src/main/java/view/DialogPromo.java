package view;

import controller.PromoController;
import controller.ValidasiException;
import java.awt.Window;
import java.time.LocalDate;
import model.Promo;

/** Dialog Tambah / Ubah promo. Tanggal ditulis dd/mm/yyyy. */
public class DialogPromo extends javax.swing.JDialog {

    private final PromoController controller = new PromoController();
    private final Promo lama;
    private boolean berhasil = false;

    /** lama = null untuk Tambah Promo, berisi promo untuk Ubah Promo. */
    public DialogPromo(Window induk, Promo lama) {
        super(induk, ModalityType.APPLICATION_MODAL);
        this.lama = lama;
        initComponents();
        UI.input(txtKode, "Contoh: HEMAT20000");
        UI.input(txtDiskon, "0");
        UI.input(txtMinimal, "1");
        UI.input(txtMulai, "dd/mm/yyyy");
        UI.input(txtSelesai, "dd/mm/yyyy");
        UI.combo(cmbJenis);
        UI.sekunder(btnBatal);
        UI.primer(btnSimpan);
        bersihkanError();
        txtMinimal.setText("1");
        txtMulai.setText(LocalDate.now().format(PromoController.FORMAT_TANGGAL));
        txtSelesai.setText(LocalDate.now().plusMonths(1).format(PromoController.FORMAT_TANGGAL));
        if (lama == null) {
            lblJudul.setText("Tambah Promo");
            btnSimpan.setText("Simpan Promo");
        } else {
            lblJudul.setText("Ubah Promo");
            btnSimpan.setText("Simpan Perubahan");
            txtKode.setText(lama.getKode());
            cmbJenis.setSelectedIndex(lama.getJenis() == 'P' ? 1 : 0);
            txtDiskon.setText(lama.getDiskon().toBigInteger().toString());
            txtMinimal.setText(String.valueOf(lama.getMinimalBarang()));
            txtMulai.setText(lama.getMulai().format(PromoController.FORMAT_TANGGAL));
            txtSelesai.setText(lama.getSelesai().format(PromoController.FORMAT_TANGGAL));
        }
        setTitle(lblJudul.getText());
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(induk);
    }

    public boolean isBerhasil() { return berhasil; }

    private void bersihkanError() {
        tampilkanError(null);
    }

    private void tampilkanError(ValidasiException e) {
        UI.tampilkanError(e, "kode", txtKode, errKode, "jenis", cmbJenis, errJenis, "diskon", txtDiskon, errDiskon,
                "minimal", txtMinimal, errMinimal, "mulai", txtMulai, errMulai, "selesai", txtSelesai, errSelesai);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        pnlKode = new javax.swing.JPanel();
        lblKode = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        errKode = new javax.swing.JLabel();
        pnlJenisDiskon = new javax.swing.JPanel();
        pnlJenis = new javax.swing.JPanel();
        lblJenis = new javax.swing.JLabel();
        cmbJenis = new javax.swing.JComboBox<>();
        errJenis = new javax.swing.JLabel();
        pnlDiskon = new javax.swing.JPanel();
        lblDiskon = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        errDiskon = new javax.swing.JLabel();
        pnlMinimal = new javax.swing.JPanel();
        lblMinimal = new javax.swing.JLabel();
        txtMinimal = new javax.swing.JTextField();
        errMinimal = new javax.swing.JLabel();
        pnlPeriode = new javax.swing.JPanel();
        pnlMulai = new javax.swing.JPanel();
        lblMulai = new javax.swing.JLabel();
        txtMulai = new javax.swing.JTextField();
        errMulai = new javax.swing.JLabel();
        pnlSelesai = new javax.swing.JPanel();
        lblSelesai = new javax.swing.JLabel();
        txtSelesai = new javax.swing.JTextField();
        errSelesai = new javax.swing.JLabel();
        pnlPenutup = new javax.swing.JPanel();
        pnlKaki = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promo");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(480, 520));
        setResizable(false);

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        pnlKepala.setLayout(new java.awt.BorderLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Tambah Promo");
        pnlKepala.add(lblJudul, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 10, 20));
        pnlForm.setOpaque(false);
        pnlForm.setLayout(new java.awt.GridBagLayout());

        pnlKode.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlKode.setOpaque(false);
        pnlKode.setLayout(new java.awt.GridBagLayout());

        lblKode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKode.setForeground(new java.awt.Color(23, 43, 77));
        lblKode.setText("Kode voucher");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKode.add(lblKode, gridBagConstraints);

        txtKode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtKode.setForeground(new java.awt.Color(23, 43, 77));
        txtKode.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtKode.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlKode.add(txtKode, gridBagConstraints);

        errKode.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errKode.setForeground(new java.awt.Color(192, 52, 63));
        errKode.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKode.add(errKode, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlForm.add(pnlKode, gridBagConstraints);

        pnlJenisDiskon.setOpaque(false);
        pnlJenisDiskon.setLayout(new java.awt.GridLayout(1, 2, 14, 0));

        pnlJenis.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlJenis.setOpaque(false);
        pnlJenis.setLayout(new java.awt.GridBagLayout());

        lblJenis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(23, 43, 77));
        lblJenis.setText("Jenis potongan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlJenis.add(lblJenis, gridBagConstraints);

        cmbJenis.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbJenis.setForeground(new java.awt.Color(23, 43, 77));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nominal (Rp)", "Persen (%)" }));
        cmbJenis.setPreferredSize(new java.awt.Dimension(190, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlJenis.add(cmbJenis, gridBagConstraints);

        errJenis.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errJenis.setForeground(new java.awt.Color(192, 52, 63));
        errJenis.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlJenis.add(errJenis, gridBagConstraints);

        pnlJenisDiskon.add(pnlJenis);

        pnlDiskon.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDiskon.setOpaque(false);
        pnlDiskon.setLayout(new java.awt.GridBagLayout());

        lblDiskon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiskon.setForeground(new java.awt.Color(23, 43, 77));
        lblDiskon.setText("Potongan (Rp atau %)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDiskon.add(lblDiskon, gridBagConstraints);

        txtDiskon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDiskon.setForeground(new java.awt.Color(23, 43, 77));
        txtDiskon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtDiskon.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDiskon.add(txtDiskon, gridBagConstraints);

        errDiskon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errDiskon.setForeground(new java.awt.Color(192, 52, 63));
        errDiskon.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlDiskon.add(errDiskon, gridBagConstraints);

        pnlJenisDiskon.add(pnlDiskon);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlJenisDiskon, gridBagConstraints);

        pnlMinimal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlMinimal.setOpaque(false);
        pnlMinimal.setLayout(new java.awt.GridBagLayout());

        lblMinimal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMinimal.setForeground(new java.awt.Color(23, 43, 77));
        lblMinimal.setText("Minimal jumlah barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlMinimal.add(lblMinimal, gridBagConstraints);

        txtMinimal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMinimal.setForeground(new java.awt.Color(23, 43, 77));
        txtMinimal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtMinimal.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlMinimal.add(txtMinimal, gridBagConstraints);

        errMinimal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errMinimal.setForeground(new java.awt.Color(192, 52, 63));
        errMinimal.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlMinimal.add(errMinimal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlMinimal, gridBagConstraints);

        pnlPeriode.setOpaque(false);
        pnlPeriode.setLayout(new java.awt.GridLayout(1, 2, 14, 0));

        pnlMulai.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlMulai.setOpaque(false);
        pnlMulai.setLayout(new java.awt.GridBagLayout());

        lblMulai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMulai.setForeground(new java.awt.Color(23, 43, 77));
        lblMulai.setText("Tanggal mulai");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlMulai.add(lblMulai, gridBagConstraints);

        txtMulai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMulai.setForeground(new java.awt.Color(23, 43, 77));
        txtMulai.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtMulai.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlMulai.add(txtMulai, gridBagConstraints);

        errMulai.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errMulai.setForeground(new java.awt.Color(192, 52, 63));
        errMulai.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlMulai.add(errMulai, gridBagConstraints);

        pnlPeriode.add(pnlMulai);

        pnlSelesai.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlSelesai.setOpaque(false);
        pnlSelesai.setLayout(new java.awt.GridBagLayout());

        lblSelesai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSelesai.setForeground(new java.awt.Color(23, 43, 77));
        lblSelesai.setText("Tanggal selesai");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSelesai.add(lblSelesai, gridBagConstraints);

        txtSelesai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSelesai.setForeground(new java.awt.Color(23, 43, 77));
        txtSelesai.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtSelesai.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlSelesai.add(txtSelesai, gridBagConstraints);

        errSelesai.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errSelesai.setForeground(new java.awt.Color(192, 52, 63));
        errSelesai.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlSelesai.add(errSelesai, gridBagConstraints);

        pnlPeriode.add(pnlSelesai);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlPeriode, gridBagConstraints);

        pnlPenutup.setPreferredSize(new java.awt.Dimension(1, 1));
        pnlPenutup.setOpaque(false);
        pnlPenutup.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlPenutup, gridBagConstraints);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pnlKaki.setBackground(new java.awt.Color(247, 249, 252));
        pnlKaki.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 20, 14, 12));
        pnlKaki.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(23, 43, 77));
        btnBatal.setText("Batal");
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlKaki.add(btnBatal);

        btnSimpan.setBackground(new java.awt.Color(33, 97, 213));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan Promo");
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlKaki.add(btnSimpan);

        getContentPane().add(pnlKaki, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            char jenis = cmbJenis.getSelectedIndex() == 1 ? 'P' : 'N';
            controller.simpan(lama == null ? 0 : lama.getId(), txtKode.getText(), jenis, txtDiskon.getText(),
                    txtMinimal.getText(), txtMulai.getText(), txtSelesai.getText());
            berhasil = true;
            dispose();
        } catch (ValidasiException e) {
            tampilkanError(e);   // dialog tetap terbuka
            pack();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JLabel errDiskon;
    private javax.swing.JLabel errJenis;
    private javax.swing.JLabel errKode;
    private javax.swing.JLabel errMinimal;
    private javax.swing.JLabel errMulai;
    private javax.swing.JLabel errSelesai;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblMinimal;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JPanel pnlDiskon;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJenis;
    private javax.swing.JPanel pnlJenisDiskon;
    private javax.swing.JPanel pnlKaki;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKode;
    private javax.swing.JPanel pnlMinimal;
    private javax.swing.JPanel pnlMulai;
    private javax.swing.JPanel pnlPenutup;
    private javax.swing.JPanel pnlPeriode;
    private javax.swing.JPanel pnlSelesai;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtMinimal;
    private javax.swing.JTextField txtMulai;
    private javax.swing.JTextField txtSelesai;
    // End of variables declaration//GEN-END:variables
}
