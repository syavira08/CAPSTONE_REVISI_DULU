package view;

import controller.PesananController;
import controller.PromoController;
import controller.ValidasiException;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;

/**
 * Flowchart Kelola Pengiriman: 1. Lihat pengiriman  2. Ubah status pengiriman.
 * Status dipilih lewat dropdown di tabel lalu "Simpan"; "Isi/Ubah resi" mengisi form di bawah tabel.
 * Tampilan dari Form Builder (PengirimanPanel.form).
 */
public class PengirimanPanel extends javax.swing.JPanel implements Halaman {

    private final PesananController controller = new PesananController();
    private final PagerPanel pager = new PagerPanel(8, "pengiriman");
    private final DefaultTableModel model;
    private List<Pesanan> data = new ArrayList<>();
    private List<Pesanan> tampil = new ArrayList<>();
    private Pesanan dipilih;   // pesanan yang sedang diisi pada form di bawah tabel

    public PengirimanPanel() {
        initComponents();
        model = (DefaultTableModel) tblKirim.getModel();
        UI.tabel(tblKirim, scrKirim);
        tblKirim.getColumnModel().getColumn(0).setPreferredWidth(180);
        tblKirim.getColumnModel().getColumn(3).setPreferredWidth(180);
        tblKirim.getColumnModel().getColumn(4).setPreferredWidth(190);
        KolomCombo.pasang(tblKirim, 3, "Siap Dikirim", "Dalam Pengiriman", "Diterima");
        new KolomAksi(tblKirim, 4,
                new KolomAksi.Tombol("Simpan", UI.Jenis.PRIMER, b -> true, this::simpanBaris),
                new KolomAksi.Tombol(b -> tampil.get(b).getNomorResi() == null ? "Isi resi" : "Ubah resi",
                        b -> UI.Jenis.SEKUNDER, b -> b < tampil.size() && !tampil.get(b).isDiterima(), this::pilih));
        UI.input(txtCari, "Cari nomor pesanan atau resi...");
        UI.combo(cmbFilter);
        UI.input(txtResi, "Contoh: HL011");
        UI.input(txtEstimasi, "dd/mm/yyyy");
        UI.sekunder(btnTampilkan);
        UI.primer(btnSimpanForm);
        UI.kartu(pnlFormKirim);
        UI.sorotan(pnlInfo, 10, 14, 10, 14);
        UI.info(pnlInfo, lblInfo, lblInfo.getText());
        bersihkanError();
        pnlPager.add(pager, BorderLayout.CENTER);
        pager.setAksi(this::tampilkanTabel);
        UI.sembunyi(pnlPesan);
        UI.tema(this);
    }

    @Override
    public void muat() {
        Object f = cmbFilter.getSelectedItem();
        coba(() -> {
            data = controller.daftarPengiriman(txtCari.getText(), kirimDari(f));
            tampilkanTabel();
            if (data.isEmpty()) info("Belum ada data pengiriman.");
        });
    }

    private static Pesanan.TahapKirim kirimDari(Object label) {
        for (Pesanan.TahapKirim t : Pesanan.TahapKirim.values()) if (t.toString().equals(label)) return t;
        return null;
    }

    private void tampilkanTabel() {
        if (tblKirim.isEditing()) tblKirim.getCellEditor().cancelCellEditing();
        tampil = new ArrayList<>(pager.potong(data));
        model.setRowCount(0);
        for (Pesanan p : tampil) {
            model.addRow(new Object[]{
                    "<html>" + p.getNomor() + "<br><span style='color:#5E6C84'>" + p.getPelanggan().getNama() + "</span></html>",
                    p.getNomorResi() == null ? "Belum tersedia" : p.getNomorResi(),
                    p.getEstimasiTiba() == null ? "-" : UI.tanggal(p.getEstimasiTiba()),
                    p.getTahapKirim().toString(), ""});
        }
        UI.sesuaikanTinggi(scrKirim, tblKirim);
    }

    /** Tombol "Isi/Ubah resi": isi form di bawah tabel dari baris yang dipilih. */
    private void pilih(int baris) {
        dipilih = tampil.get(baris);
        bersihkanError();
        lblFormJudul.setText("Perbarui pengiriman • " + dipilih.getNomor() + " (" + dipilih.getPelanggan().getNama() + ")");
        txtResi.setText(dipilih.getNomorResi() == null ? "" : dipilih.getNomorResi());
        LocalDate est = dipilih.getEstimasiTiba() == null ? LocalDate.now().plusDays(3) : dipilih.getEstimasiTiba();
        txtEstimasi.setText(est.format(PromoController.FORMAT_TANGGAL));
        txtResi.requestFocusInWindow();
    }

    /** Tombol "Simpan" pada baris: simpan status yang dipilih pada dropdown (resi & estimasi tetap). */
    private void simpanBaris(int baris) {
        Pesanan p = tampil.get(baris);
        if (p.getNomorResi() == null) {
            galat("Isi nomor resi lebih dulu lewat tombol Isi resi.");
            return;
        }
        LocalDate est = p.getEstimasiTiba() == null ? LocalDate.now().plusDays(3) : p.getEstimasiTiba();
        simpan(p, p.getNomorResi(), est.format(PromoController.FORMAT_TANGGAL),
                kirimDari(model.getValueAt(baris, 3)), false);
    }

    private void simpan(Pesanan p, String resi, String estimasi, Pesanan.TahapKirim status, boolean dariForm) {
        try {
            controller.simpanPengiriman(p, resi, estimasi, status);
            muat();
            sukses("Pengiriman berhasil disimpan");
            if (dariForm) {
                dipilih = null;
                lblFormJudul.setText("Perbarui pengiriman — pilih pesanan pada tabel");
                txtResi.setText("");
                txtEstimasi.setText("");
            }
        } catch (ValidasiException e) {
            String detail = e.getPesan("status") != null ? e.getPesan("status") : e.getMessage();
            galat(detail);
            if (dariForm) tampilkanError(e);
        } catch (RuntimeException e) {
            galat(e.getMessage());
        }
    }

    private void bersihkanError() {
        tampilkanError(null);
    }

    private void tampilkanError(ValidasiException e) {
        UI.tampilkanError(e, "resi", txtResi, errResi, "estimasi", txtEstimasi, errEstimasi);
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
        pnlFilter = new javax.swing.JPanel();
        pnlCari = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        pnlSaring = new javax.swing.JPanel();
        lblSaring = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlIsiFilter = new javax.swing.JPanel();
        scrKirim = new javax.swing.JScrollPane();
        tblKirim = new javax.swing.JTable();
        pnlPager = new javax.swing.JPanel();
        pnlFormKirim = new javax.swing.JPanel();
        lblFormJudul = new javax.swing.JLabel();
        pnlFormIsi = new javax.swing.JPanel();
        pnlResi = new javax.swing.JPanel();
        lblResi = new javax.swing.JLabel();
        txtResi = new javax.swing.JTextField();
        errResi = new javax.swing.JLabel();
        pnlEstimasi = new javax.swing.JPanel();
        lblEstimasi = new javax.swing.JLabel();
        txtEstimasi = new javax.swing.JTextField();
        errEstimasi = new javax.swing.JLabel();
        btnSimpanForm = new javax.swing.JButton();
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();

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
        lblJudul.setText("Kelola pengiriman");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("Catat nomor resi, estimasi tiba, dan progres pengiriman.");
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
        pnlSaring.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlSaring.setOpaque(false);
        pnlSaring.setLayout(new java.awt.GridBagLayout());
        lblSaring.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSaring.setForeground(new java.awt.Color(23, 43, 77));
        lblSaring.setText("Filter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlSaring.add(lblSaring, gridBagConstraints);
        cmbFilter.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cmbFilter.setForeground(new java.awt.Color(23, 43, 77));
        cmbFilter.setBackground(new java.awt.Color(255, 255, 255));
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua pengiriman", "Siap Dikirim", "Dalam Pengiriman", "Diterima" }));
        cmbFilter.setPreferredSize(new java.awt.Dimension(200, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlSaring.add(cmbFilter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlFilter.add(pnlSaring, gridBagConstraints);
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonten.add(pnlFilter, gridBagConstraints);
        scrKirim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)));
        scrKirim.setPreferredSize(new java.awt.Dimension(700, 240));
        scrKirim.setBackground(new java.awt.Color(255, 255, 255));
        tblKirim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pesanan / penerima", "Nomor resi", "Estimasi tiba", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKirim.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tblKirim.setRowHeight(44);
        scrKirim.setViewportView(tblKirim);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(scrKirim, gridBagConstraints);
        pnlPager.setOpaque(false);
        pnlPager.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlPager, gridBagConstraints);
        pnlFormKirim.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormKirim.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlFormKirim.setLayout(new java.awt.GridBagLayout());
        lblFormJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblFormJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblFormJudul.setText("Perbarui pengiriman \u2014 pilih pesanan pada tabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormKirim.add(lblFormJudul, gridBagConstraints);
        pnlFormIsi.setOpaque(false);
        pnlFormIsi.setLayout(new java.awt.GridBagLayout());
        pnlResi.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlResi.setOpaque(false);
        pnlResi.setLayout(new java.awt.GridBagLayout());
        lblResi.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblResi.setForeground(new java.awt.Color(23, 43, 77));
        lblResi.setText("Nomor resi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlResi.add(lblResi, gridBagConstraints);
        txtResi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtResi.setForeground(new java.awt.Color(23, 43, 77));
        txtResi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtResi.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlResi.add(txtResi, gridBagConstraints);
        errResi.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errResi.setForeground(new java.awt.Color(192, 52, 63));
        errResi.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlResi.add(errResi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormIsi.add(pnlResi, gridBagConstraints);
        pnlEstimasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlEstimasi.setOpaque(false);
        pnlEstimasi.setLayout(new java.awt.GridBagLayout());
        lblEstimasi.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEstimasi.setForeground(new java.awt.Color(23, 43, 77));
        lblEstimasi.setText("Estimasi tiba");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEstimasi.add(lblEstimasi, gridBagConstraints);
        txtEstimasi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txtEstimasi.setForeground(new java.awt.Color(23, 43, 77));
        txtEstimasi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        txtEstimasi.setPreferredSize(new java.awt.Dimension(160, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlEstimasi.add(txtEstimasi, gridBagConstraints);
        errEstimasi.setFont(new java.awt.Font("Segoe UI", 0, 11));
        errEstimasi.setForeground(new java.awt.Color(192, 52, 63));
        errEstimasi.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEstimasi.add(errEstimasi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 0);
        pnlFormIsi.add(pnlEstimasi, gridBagConstraints);
        btnSimpanForm.setBackground(new java.awt.Color(33, 97, 213));
        btnSimpanForm.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSimpanForm.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpanForm.setText("Simpan Pengiriman");
        btnSimpanForm.setFocusPainted(false);
        btnSimpanForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanFormActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(22, 14, 0, 0);
        pnlFormIsi.add(btnSimpanForm, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlFormKirim.add(pnlFormIsi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlFormKirim, gridBagConstraints);
        pnlInfo.setBackground(new java.awt.Color(234, 241, 252));
        pnlInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlInfo.setLayout(new java.awt.BorderLayout());
        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblInfo.setForeground(new java.awt.Color(33, 97, 213));
        lblInfo.setText("Nomor resi dan progres terbaru dapat dilihat pelanggan melalui Lacak Pesanan.");
        pnlInfo.add(lblInfo, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKonten.add(pnlInfo, gridBagConstraints);
        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);
        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        pager.reset();
        muat();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    private void btnSimpanFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanFormActionPerformed
        if (dipilih == null) {
            galat("Pilih pesanan pada tabel terlebih dahulu (tombol Isi resi / Ubah resi).");
            return;
        }
        // status pengiriman mengikuti dropdown pada tabel; "Siap Dikirim" + resi baru = Dalam Pengiriman
        Pesanan.TahapKirim status = dipilih.isDiterima() ? Pesanan.TahapKirim.DITERIMA : Pesanan.TahapKirim.DALAM_PENGIRIMAN;
        for (int i = 0; i < tampil.size(); i++) {
            if (tampil.get(i) == dipilih && Pesanan.TahapKirim.DITERIMA.toString().equals(model.getValueAt(i, 3))) {
                status = Pesanan.TahapKirim.DITERIMA;
            }
        }
        simpan(dipilih, txtResi.getText(), txtEstimasi.getText(), status, true);
    }//GEN-LAST:event_btnSimpanFormActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpanForm;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbFilter;
    private javax.swing.JLabel errEstimasi;
    private javax.swing.JLabel errResi;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblEstimasi;
    private javax.swing.JLabel lblFormJudul;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblResi;
    private javax.swing.JLabel lblSaring;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlEstimasi;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlFormIsi;
    private javax.swing.JPanel pnlFormKirim;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlIsiFilter;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlPager;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlResi;
    private javax.swing.JPanel pnlSaring;
    private javax.swing.JScrollPane scrKirim;
    private javax.swing.JTable tblKirim;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEstimasi;
    private javax.swing.JTextField txtResi;
    // End of variables declaration//GEN-END:variables
}
