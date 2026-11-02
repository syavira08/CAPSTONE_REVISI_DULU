package view;

import controller.ProdukController;
import controller.ValidasiException;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import model.Produk;

/** Dialog Tambah / Ubah produk: input -> validasi (ProdukController) -> simpan. */
public class DialogProduk extends javax.swing.JDialog {

    private final ProdukController controller = new ProdukController();
    private final Produk lama;
    private boolean berhasil = false;

    /** lama = null untuk Tambah Produk, berisi produk untuk Ubah Produk. */
    public DialogProduk(Window induk, Produk lama) {
        super(induk, ModalityType.APPLICATION_MODAL);
        this.lama = lama;
        initComponents();
        UI.input(txtNama, "Nama produk");
        UI.input(txtHarga, "0");
        UI.input(txtStok, "0");
        UI.combo(cmbKategori);
        cmbKategori.setEditable(true);   // boleh mengetik kategori baru
        cmbKategori.setModel(new DefaultComboBoxModel<>(controller.daftarKategori().toArray(new String[0])));
        UI.sekunder(btnBatal);
        UI.primer(btnSimpan);
        bersihkanError();
        if (lama == null) {
            lblJudul.setText("Tambah Produk");
            btnSimpan.setText("Simpan Produk");
            cmbKategori.setSelectedItem(null);
        } else {
            lblJudul.setText("Ubah Produk • " + lama.getKode());
            btnSimpan.setText("Simpan Perubahan");
            txtNama.setText(lama.getNama());
            cmbKategori.setSelectedItem(lama.getKategori());
            txtHarga.setText(lama.getHarga().toBigInteger().toString());
            txtStok.setText(String.valueOf(lama.getStok()));
        }
        setTitle(lblJudul.getText());
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(induk);
    }

    public boolean isBerhasil() { return berhasil; }

    private void bersihkanError() {
        UI.tampilkanError(null, "nama", txtNama, errNama, "kategori", cmbKategori, errKategori,
                "harga", txtHarga, errHarga, "stok", txtStok, errStok);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlKepala = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        pnlNama = new javax.swing.JPanel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        errNama = new javax.swing.JLabel();
        pnlKategori = new javax.swing.JPanel();
        lblKategori = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        errKategori = new javax.swing.JLabel();
        pnlHargaStok = new javax.swing.JPanel();
        pnlHarga = new javax.swing.JPanel();
        lblHarga = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        errHarga = new javax.swing.JLabel();
        pnlStok = new javax.swing.JPanel();
        lblStok = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        errStok = new javax.swing.JLabel();
        pnlPenutup = new javax.swing.JPanel();
        pnlKaki = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produk");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(480, 430));
        setResizable(false);

        pnlKepala.setBackground(new java.awt.Color(255, 255, 255));
        pnlKepala.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        pnlKepala.setLayout(new java.awt.BorderLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Tambah Produk");
        pnlKepala.add(lblJudul, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 10, 20));
        pnlForm.setOpaque(false);
        pnlForm.setLayout(new java.awt.GridBagLayout());

        pnlNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlNama.setOpaque(false);
        pnlNama.setLayout(new java.awt.GridBagLayout());

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNama.setForeground(new java.awt.Color(23, 43, 77));
        lblNama.setText("Nama produk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlNama.add(lblNama, gridBagConstraints);

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(23, 43, 77));
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtNama.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlNama.add(txtNama, gridBagConstraints);

        errNama.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errNama.setForeground(new java.awt.Color(192, 52, 63));
        errNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlNama.add(errNama, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlForm.add(pnlNama, gridBagConstraints);

        pnlKategori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlKategori.setOpaque(false);
        pnlKategori.setLayout(new java.awt.GridBagLayout());

        lblKategori.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKategori.setForeground(new java.awt.Color(23, 43, 77));
        lblKategori.setText("Kategori");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKategori.add(lblKategori, gridBagConstraints);

        cmbKategori.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbKategori.setForeground(new java.awt.Color(23, 43, 77));
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elektronik", "Aksesoris" }));
        cmbKategori.setPreferredSize(new java.awt.Dimension(190, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlKategori.add(cmbKategori, gridBagConstraints);

        errKategori.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errKategori.setForeground(new java.awt.Color(192, 52, 63));
        errKategori.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKategori.add(errKategori, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlKategori, gridBagConstraints);

        pnlHargaStok.setOpaque(false);
        pnlHargaStok.setLayout(new java.awt.GridLayout(1, 2, 14, 0));

        pnlHarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlHarga.setOpaque(false);
        pnlHarga.setLayout(new java.awt.GridBagLayout());

        lblHarga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHarga.setForeground(new java.awt.Color(23, 43, 77));
        lblHarga.setText("Harga (Rp)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlHarga.add(lblHarga, gridBagConstraints);

        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtHarga.setForeground(new java.awt.Color(23, 43, 77));
        txtHarga.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtHarga.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlHarga.add(txtHarga, gridBagConstraints);

        errHarga.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errHarga.setForeground(new java.awt.Color(192, 52, 63));
        errHarga.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlHarga.add(errHarga, gridBagConstraints);

        pnlHargaStok.add(pnlHarga);

        pnlStok.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlStok.setOpaque(false);
        pnlStok.setLayout(new java.awt.GridBagLayout());

        lblStok.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStok.setForeground(new java.awt.Color(23, 43, 77));
        lblStok.setText("Stok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlStok.add(lblStok, gridBagConstraints);

        txtStok.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtStok.setForeground(new java.awt.Color(23, 43, 77));
        txtStok.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtStok.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlStok.add(txtStok, gridBagConstraints);

        errStok.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        errStok.setForeground(new java.awt.Color(192, 52, 63));
        errStok.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlStok.add(errStok, gridBagConstraints);

        pnlHargaStok.add(pnlStok);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlForm.add(pnlHargaStok, gridBagConstraints);

        pnlPenutup.setPreferredSize(new java.awt.Dimension(1, 1));
        pnlPenutup.setOpaque(false);
        pnlPenutup.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
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
        btnSimpan.setText("Simpan Produk");
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
            Object kategori = cmbKategori.getEditor().getItem();
            controller.simpan(lama == null ? 0 : lama.getId(), txtNama.getText(),
                    String.valueOf(kategori == null ? "" : kategori), txtHarga.getText(), txtStok.getText());
            berhasil = true;
            dispose();
        } catch (ValidasiException e) {
            UI.tampilkanError(e, "nama", txtNama, errNama, "kategori", cmbKategori, errKategori,
                    "harga", txtHarga, errHarga, "stok", txtStok, errStok);   // dialog tetap terbuka
            pack();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JLabel errHarga;
    private javax.swing.JLabel errKategori;
    private javax.swing.JLabel errNama;
    private javax.swing.JLabel errStok;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblStok;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlHarga;
    private javax.swing.JPanel pnlHargaStok;
    private javax.swing.JPanel pnlKaki;
    private javax.swing.JPanel pnlKategori;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlNama;
    private javax.swing.JPanel pnlPenutup;
    private javax.swing.JPanel pnlStok;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
