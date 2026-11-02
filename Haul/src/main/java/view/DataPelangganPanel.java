package view;

import controller.PelangganController;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pelanggan;

/** Flowchart Kelola Data Pelanggan: lihat data pelanggan (hanya-baca). Tampilan dari Form Builder. */
public class DataPelangganPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganController controller = new PelangganController();
    private final PagerPanel pager = new PagerPanel(8, "pelanggan");
    private final DefaultTableModel model;
    private List<Pelanggan> data = new ArrayList<>();

    public DataPelangganPanel() {
        initComponents();
        model = (DefaultTableModel) tblPelanggan.getModel();
        UI.tabel(tblPelanggan, scrPelanggan);
        tblPelanggan.getColumnModel().getColumn(4).setPreferredWidth(260);
        UI.input(txtCari, "Cari nama atau username pelanggan...");
        UI.sekunder(btnTampilkan);
        UI.sorotan(pnlInfo, 10, 14, 10, 14);
        UI.info(pnlInfo, lblInfo, lblInfo.getText());
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanTabel);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        coba(() -> {
            data = controller.daftar(txtCari.getText());
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data pelanggan.");
        });
    }

    private void tampilkanTabel() {
        model.setRowCount(0);
        for (Pelanggan p : pager.potong(data)) {
            model.addRow(new Object[]{p.getKode(), p.getNama(), p.getUsername(), UI.telepon(p.getNoTelepon()),
                    p.getAlamat(), UI.tanggal(p.getTanggalBergabung())});
        }
        UI.sesuaikanTinggi(scrPelanggan, tblPelanggan);
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
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        pnlFilter = new javax.swing.JPanel();
        pnlCari = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        scrPelanggan = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
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

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblJudul.setText("Data pelanggan");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);

        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Direktori akun pelanggan. Data hanya dapat dilihat oleh admin.");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);

        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);

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

        pnlInfo.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfo.setLayout(new java.awt.BorderLayout());

        lblInfo.setForeground(new java.awt.Color(33, 97, 213));
        lblInfo.setText("Mode hanya-baca. Perubahan data pribadi dilakukan oleh pelanggan melalui Profil.");
        pnlInfo.add(lblInfo, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlInfo, gridBagConstraints);

        pnlFilter.setOpaque(false);
        pnlFilter.setLayout(new java.awt.GridBagLayout());

        pnlCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlCari.setOpaque(false);
        pnlCari.setLayout(new java.awt.GridBagLayout());

        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCari.setForeground(new java.awt.Color(23, 43, 77));
        lblCari.setText("Pencarian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlCari.add(lblCari, gridBagConstraints);

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(23, 43, 77));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
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

        btnTampilkan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTampilkan.setForeground(new java.awt.Color(23, 43, 77));
        btnTampilkan.setText("Tampilkan");
        btnTampilkan.setFocusPainted(false);
        btnTampilkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilkanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(btnTampilkan, gridBagConstraints);

        pnlIsiFilter.setPreferredSize(new java.awt.Dimension(1, 1));
        pnlIsiFilter.setOpaque(false);
        pnlIsiFilter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
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

        scrPelanggan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrPelanggan.setPreferredSize(new java.awt.Dimension(700, 300));
        scrPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama lengkap", "Username", "No. telepon", "Alamat", "Bergabung"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPelanggan.setRowHeight(44);
        scrPelanggan.setViewportView(tblPelanggan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrPelanggan, gridBagConstraints);

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

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        pager.reset();
        muat();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiFilter;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlPager;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JScrollPane scrPelanggan;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
