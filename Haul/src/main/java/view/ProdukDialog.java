package view;

import controller.ProdukController;
import controller.ValidasiException;
import model.Produk;

/** Form tambah/ubah produk: unggah foto, kategori dipilih dari daftar. */
public class ProdukDialog extends javax.swing.JDialog {

    private final ProdukController controller = new ProdukController();
    private Produk produk;          // null = produk baru
    private byte[] gambarBaru;      // foto yang dipilih (belum disimpan)
    private String namaFile;
    private boolean tersimpan;

    public ProdukDialog(java.awt.Window parent) {
        super(parent, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        for (String k : controller.daftarKategori()) cmbKategori.addItem(k);   // hanya bisa dipilih
    }

    /** @param p produk yang diubah, atau null untuk tambah. Mengembalikan true jika tersimpan. */
    public static boolean tampil(java.awt.Component asal, Produk p) {
        ProdukDialog d = new ProdukDialog(Gaya.jendela(asal));
        d.produk = p;
        if (p != null) {
            d.setTitle("Ubah Produk");
            d.lblJudul.setText("Ubah Produk " + p.getKode());
            d.txtNama.setText(p.getNama());
            d.cmbKategori.setSelectedItem(p.getKategori());
            d.txtHarga.setText(p.getHarga().toBigInteger().toString());
            d.txtStok.setText(String.valueOf(p.getStok()));
            d.lblPratinjau.setIcon(Gaya.foto(p.getGambar(), 198, 198));
            if (p.getGambar() != null) d.btnPilihGambar.setText("Ganti Gambar");
        } else {
            d.setTitle("Tambah Produk");
        }
        d.getRootPane().setDefaultButton(d.btnSimpan);
        d.pack();
        d.setLocationRelativeTo(asal);
        d.setVisible(true);
        return d.tersimpan;
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
        lblSub = new javax.swing.JLabel();
        pnlFoto = new javax.swing.JPanel();
        lblPratinjau = new javax.swing.JLabel();
        btnPilihGambar = new javax.swing.JButton();
        lblNamaFile = new javax.swing.JLabel();
        lblErrGambar = new javax.swing.JLabel();
        pnlFotoSisa = new javax.swing.JPanel();
        pnlData = new javax.swing.JPanel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblErrNama = new javax.swing.JLabel();
        lblKategori = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        lblErrKategori = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        lblErrHarga = new javax.swing.JLabel();
        lblStok = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        lblErrStok = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Produk");
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
        lblJudul.setText("Tambah Produk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlKepala.add(lblJudul, gridBagConstraints);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(102, 112, 133));
        lblSub.setText("Lengkapi data produk lalu simpan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlKepala.add(lblSub, gridBagConstraints);
        pnlLatar.add(pnlKepala, java.awt.BorderLayout.NORTH);

        pnlFoto.setBackground(new java.awt.Color(255, 255, 255));
        pnlFoto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 24));
        pnlFoto.setOpaque(false);
        pnlFoto.setLayout(new java.awt.GridBagLayout());
        lblPratinjau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)));
        lblPratinjau.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPratinjau.setForeground(new java.awt.Color(16, 24, 40));
        lblPratinjau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPratinjau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/foto_kosong.png"))); // NOI18N
        lblPratinjau.setPreferredSize(new java.awt.Dimension(200, 200));
        lblPratinjau.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlFoto.add(lblPratinjau, gridBagConstraints);
        btnPilihGambar.setBackground(new java.awt.Color(255, 255, 255));
        btnPilihGambar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)));
        btnPilihGambar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPilihGambar.setFocusPainted(false);
        btnPilihGambar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnPilihGambar.setForeground(new java.awt.Color(16, 24, 40));
        btnPilihGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/unggah.png"))); // NOI18N
        btnPilihGambar.setIconTextGap(8);
        btnPilihGambar.setPreferredSize(new java.awt.Dimension(200, 38));
        btnPilihGambar.setText("Pilih Gambar");
        btnPilihGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihGambarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlFoto.add(btnPilihGambar, gridBagConstraints);
        lblNamaFile.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNamaFile.setForeground(new java.awt.Color(102, 112, 133));
        lblNamaFile.setText("Format JPG / PNG, maks. 2 MB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlFoto.add(lblNamaFile, gridBagConstraints);
        lblErrGambar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrGambar.setForeground(new java.awt.Color(217, 45, 32));
        lblErrGambar.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrGambar.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlFoto.add(lblErrGambar, gridBagConstraints);
        pnlFotoSisa.setBackground(new java.awt.Color(255, 255, 255));
        pnlFotoSisa.setOpaque(false);
        pnlFotoSisa.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlFoto.add(pnlFotoSisa, gridBagConstraints);

        pnlLatar.add(pnlFoto, java.awt.BorderLayout.WEST);

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setOpaque(false);
        pnlData.setLayout(new java.awt.GridBagLayout());
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNama.setForeground(new java.awt.Color(52, 64, 84));
        lblNama.setText("Nama produk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblNama, gridBagConstraints);
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(16, 24, 40));
        txtNama.setPreferredSize(new java.awt.Dimension(320, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtNama, gridBagConstraints);
        lblErrNama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrNama.setForeground(new java.awt.Color(217, 45, 32));
        lblErrNama.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrNama.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrNama, gridBagConstraints);
        lblKategori.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKategori.setForeground(new java.awt.Color(52, 64, 84));
        lblKategori.setText("Kategori");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblKategori, gridBagConstraints);
        cmbKategori.setBackground(new java.awt.Color(255, 255, 255));
        cmbKategori.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbKategori.setForeground(new java.awt.Color(16, 24, 40));
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih kategori" }));
        cmbKategori.setPreferredSize(new java.awt.Dimension(320, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(cmbKategori, gridBagConstraints);
        lblErrKategori.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrKategori.setForeground(new java.awt.Color(217, 45, 32));
        lblErrKategori.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrKategori.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrKategori, gridBagConstraints);
        lblHarga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHarga.setForeground(new java.awt.Color(52, 64, 84));
        lblHarga.setText("Harga (Rp)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblHarga, gridBagConstraints);
        txtHarga.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtHarga.setForeground(new java.awt.Color(16, 24, 40));
        txtHarga.setPreferredSize(new java.awt.Dimension(320, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtHarga, gridBagConstraints);
        lblErrHarga.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrHarga.setForeground(new java.awt.Color(217, 45, 32));
        lblErrHarga.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrHarga.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrHarga, gridBagConstraints);
        lblStok.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStok.setForeground(new java.awt.Color(52, 64, 84));
        lblStok.setText("Stok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlData.add(lblStok, gridBagConstraints);
        txtStok.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(208, 213, 221)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtStok.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtStok.setForeground(new java.awt.Color(16, 24, 40));
        txtStok.setPreferredSize(new java.awt.Dimension(320, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlData.add(txtStok, gridBagConstraints);
        lblErrStok.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblErrStok.setForeground(new java.awt.Color(217, 45, 32));
        lblErrStok.setPreferredSize(new java.awt.Dimension(40, 17));
        lblErrStok.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        pnlData.add(lblErrStok, gridBagConstraints);
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

    private void btnPilihGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihGambarActionPerformed
        javax.swing.JFileChooser pilih = new javax.swing.JFileChooser();
        pilih.setDialogTitle("Pilih gambar produk");
        pilih.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar (JPG, PNG)", "jpg", "jpeg", "png"));
        if (pilih.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) return;
        java.io.File file = pilih.getSelectedFile();
        try {
            gambarBaru = Gaya.siapkanGambar(file);
            namaFile = file.getName();
            lblPratinjau.setIcon(Gaya.foto(gambarBaru, 198, 198));
            lblNamaFile.setText(namaFile);
            Gaya.error(lblErrGambar, null);
        } catch (java.io.IOException e) {
            Gaya.error(lblErrGambar, "File tidak dapat dibaca sebagai gambar.");
        }
    }//GEN-LAST:event_btnPilihGambarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        for (javax.swing.JLabel l : new javax.swing.JLabel[]{lblErrNama, lblErrKategori, lblErrHarga, lblErrStok, lblErrGambar}) {
            Gaya.error(l, null);
        }
        String kategori = cmbKategori.getSelectedIndex() <= 0 ? null : (String) cmbKategori.getSelectedItem();
        try {
            controller.simpan(produk == null ? 0 : produk.getId(), txtNama.getText(), kategori, txtHarga.getText(),
                    txtStok.getText(), gambarBaru, namaFile);
            tersimpan = true;
            dispose();
        } catch (ValidasiException e) {
            Gaya.error(lblErrNama, e.getPesan("nama"));
            Gaya.error(lblErrKategori, e.getPesan("kategori"));
            Gaya.error(lblErrHarga, e.getPesan("harga"));
            Gaya.error(lblErrStok, e.getPesan("stok"));
            Gaya.error(lblErrGambar, e.getPesan("gambar"));
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnPilihGambar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JLabel lblErrGambar;
    private javax.swing.JLabel lblErrHarga;
    private javax.swing.JLabel lblErrKategori;
    private javax.swing.JLabel lblErrNama;
    private javax.swing.JLabel lblErrStok;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaFile;
    private javax.swing.JLabel lblPratinjau;
    private javax.swing.JLabel lblStok;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlFoto;
    private javax.swing.JPanel pnlFotoSisa;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlLatar;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
