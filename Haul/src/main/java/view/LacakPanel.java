package view;

import controller.PesananController;
import model.Pesanan;

/**
 * Flowchart Lacak Pesanan:
 *  ada data pengiriman? -> tampilkan nomor resi, estimasi, status -> barang sudah diterima?
 *  -> pelanggan konfirmasi -> status pengiriman menjadi Diterima.
 * Tampilan dari Form Builder (LacakPanel.form); garis waktu memakai form LangkahLacak.
 */
public class LacakPanel extends javax.swing.JPanel implements Halaman {

    private final PelangganView jendela;
    private final PesananController controller = new PesananController();
    private Pesanan pesanan;

    public LacakPanel(PelangganView jendela) {
        this.jendela = jendela;
        initComponents();
        UI.sekunder(btnKembali);
        UI.primer(btnTerima);
        UI.kartu(pnlStatus);
        UI.kartu(pnlDetail);
        UI.kartu(pnlAlamat);
        UI.kartu(pnlKonfirmasi);
        UI.kartu(pnlKosongStatus, 40, 20, 40, 20);
        UI.ikon(lblKosongIkon, "kotak", 30, UI.PUDAR);
        UI.sorotan(pnlEstimasi, 10, 14, 10, 14);
        UI.sembunyi(pnlPesan);
        UI.lebarTetap(pnlKananWrap, 330);
        UI.tema(this);
    }

    public void setPesanan(Pesanan p) {
        this.pesanan = p;
    }

    @Override
    public void muat() {
        pesanan = controller.muatUlang(pesanan.getId());
        lblSubjudul.setText(pesanan.getNomor() + "  •  Dipesan pada " + UI.tanggal(pesanan.getTanggal().toLocalDate()));

        // ----- kiri: status pengiriman -----
        boolean adaResi = pesanan.getNomorResi() != null;
        pnlStatus.setVisible(adaResi);
        pnlKosongStatus.setVisible(!adaResi);
        if (adaResi) {
            lblResiNilai.setText(pesanan.getNomorResi());
            UI.badge(lblBadgeKirim, pesanan.getTahapKirim().toString());
            UI.info(pnlEstimasi, lblEstimasi, "Estimasi tiba: " + UI.tanggal(pesanan.getEstimasiTiba()));
            pnlWaktu.removeAll();
            if (pesanan.isDiterima()) {
                langkah("Pesanan diterima", UI.tanggal(pesanan.getTanggalDiterima()), true);
            }
            langkah("Paket dalam pengiriman", "Estimasi tiba " + UI.tanggal(pesanan.getEstimasiTiba()), !pesanan.isDiterima());
            langkah("Pembayaran terverifikasi", "Barang dikemas oleh HAULECTRA", false);
            langkah("Pesanan dibuat", UI.tanggal(pesanan.getTanggal()), false);
            pnlWaktu.revalidate();
            pnlWaktu.repaint();
        }

        // ----- kanan: detail, alamat, konfirmasi -----
        lblDetailProduk.setText(pesanan.getNamaProduk());
        lblDetailJumlah.setText(pesanan.getJumlahBarang() + " barang");
        lblDetailTotal.setText("Total " + UI.rupiah(pesanan.getTotalHarga()));
        lblAlamatNama.setText(pesanan.getPelanggan().getNama());
        lblAlamatTelp.setText(UI.telepon(pesanan.getPelanggan().getNoTelepon()));
        lblAlamatDetail.setText(UI.bungkus(pesanan.getPelanggan().getAlamat(), 250));
        lblKonfirmasiIsi.setText(UI.bungkus("Pastikan barang sudah diterima dalam kondisi baik sebelum konfirmasi.", 250));
        pnlKonfirmasi.setVisible(pesanan.getTahap() == Pesanan.Tahap.DIKIRIM && adaResi);
    }

    /** Satu baris garis waktu (titik + judul + keterangan). */
    private void langkah(String judul, String keterangan, boolean aktif) {
        UI.tambahBaris(pnlWaktu, new LangkahLacak(judul, keterangan, aktif), 14);
    }

    private void konfirmasi() {
        boolean ya = UI.konfirmasi(this, "Konfirmasi penerimaan", "Pastikan " + pesanan.getNamaProduk()
                + " untuk pesanan " + pesanan.getNomor() + " sudah diterima dalam kondisi baik.", "Ya, Sudah Diterima", false);
        if (ya && coba(() -> controller.konfirmasiDiterima(pesanan))) {
            muat();
            sukses("Pesanan selesai. Terima kasih telah berbelanja!");
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
        btnKembali = new javax.swing.JButton();
        pnlPesan = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlIsi = new javax.swing.JPanel();
        pnlKonten = new javax.swing.JPanel();
        pnlKiri = new javax.swing.JPanel();
        pnlStatus = new javax.swing.JPanel();
        lblStatusJudul = new javax.swing.JLabel();
        pnlResi = new javax.swing.JPanel();
        pnlResiTeks = new javax.swing.JPanel();
        lblResiLabel = new javax.swing.JLabel();
        lblResiNilai = new javax.swing.JLabel();
        pnlBadgeKirim = new javax.swing.JPanel();
        lblBadgeKirim = new javax.swing.JLabel();
        pnlEstimasi = new javax.swing.JPanel();
        lblEstimasi = new javax.swing.JLabel();
        pnlWaktu = new javax.swing.JPanel();
        pnlKosongStatus = new javax.swing.JPanel();
        lblKosongIkon = new javax.swing.JLabel();
        lblKosongJudul = new javax.swing.JLabel();
        lblKosongIsi = new javax.swing.JLabel();
        pnlKananWrap = new javax.swing.JPanel();
        pnlKanan = new javax.swing.JPanel();
        pnlDetail = new javax.swing.JPanel();
        lblDetailJudul = new javax.swing.JLabel();
        lblDetailProduk = new javax.swing.JLabel();
        lblDetailJumlah = new javax.swing.JLabel();
        lblDetailTotal = new javax.swing.JLabel();
        pnlAlamat = new javax.swing.JPanel();
        lblAlamatJudul = new javax.swing.JLabel();
        lblAlamatNama = new javax.swing.JLabel();
        lblAlamatTelp = new javax.swing.JLabel();
        lblAlamatDetail = new javax.swing.JLabel();
        pnlKonfirmasi = new javax.swing.JPanel();
        lblKonfirmasiJudul = new javax.swing.JLabel();
        lblKonfirmasiIsi = new javax.swing.JLabel();
        btnTerima = new javax.swing.JButton();

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
        lblJudul.setText("Lacak pesanan");
        pnlJudul.add(lblJudul, java.awt.BorderLayout.NORTH);
        lblSubjudul.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubjudul.setForeground(new java.awt.Color(94, 108, 132));
        lblSubjudul.setText("HLC-261001-016  \u2022  Dipesan pada 1 Oktober 2026");
        pnlJudul.add(lblSubjudul, java.awt.BorderLayout.CENTER);
        pnlKepala.add(pnlJudul, java.awt.BorderLayout.CENTER);
        pnlAksiKepala.setOpaque(false);
        pnlAksiKepala.setLayout(new java.awt.BorderLayout());
        btnKembali.setBackground(new java.awt.Color(255, 255, 255));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnKembali.setForeground(new java.awt.Color(23, 43, 77));
        btnKembali.setText("Kembali ke riwayat");
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
        lblPesan.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPesan.setForeground(new java.awt.Color(46, 125, 50));
        lblPesan.setText("Pesan");
        pnlPesan.add(lblPesan, java.awt.BorderLayout.CENTER);
        pnlAtas.add(pnlPesan, java.awt.BorderLayout.CENTER);
        add(pnlAtas, java.awt.BorderLayout.NORTH);

        pnlIsi.setOpaque(false);
        pnlIsi.setLayout(new java.awt.BorderLayout());
        pnlKonten.setOpaque(false);
        pnlKonten.setLayout(new java.awt.BorderLayout(20, 0));
        pnlKiri.setOpaque(false);
        pnlKiri.setLayout(new java.awt.GridBagLayout());
        pnlStatus.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatus.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlStatus.setLayout(new java.awt.GridBagLayout());
        lblStatusJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblStatusJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblStatusJudul.setText("Status pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlStatus.add(lblStatusJudul, gridBagConstraints);
        pnlResi.setOpaque(false);
        pnlResi.setLayout(new java.awt.BorderLayout(12, 0));
        pnlResiTeks.setOpaque(false);
        pnlResiTeks.setLayout(new java.awt.GridBagLayout());
        lblResiLabel.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblResiLabel.setForeground(new java.awt.Color(94, 108, 132));
        lblResiLabel.setText("Nomor resi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlResiTeks.add(lblResiLabel, gridBagConstraints);
        lblResiNilai.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblResiNilai.setForeground(new java.awt.Color(23, 43, 77));
        lblResiNilai.setText("HL004");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlResiTeks.add(lblResiNilai, gridBagConstraints);
        pnlResi.add(pnlResiTeks, java.awt.BorderLayout.CENTER);
        pnlBadgeKirim.setOpaque(false);
        pnlBadgeKirim.setLayout(new java.awt.GridBagLayout());
        lblBadgeKirim.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblBadgeKirim.setForeground(new java.awt.Color(33, 97, 213));
        lblBadgeKirim.setText("Dalam Pengiriman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlBadgeKirim.add(lblBadgeKirim, gridBagConstraints);
        pnlResi.add(pnlBadgeKirim, java.awt.BorderLayout.EAST);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlStatus.add(pnlResi, gridBagConstraints);
        pnlEstimasi.setBackground(new java.awt.Color(234, 241, 252));
        pnlEstimasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlEstimasi.setLayout(new java.awt.BorderLayout());
        lblEstimasi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblEstimasi.setForeground(new java.awt.Color(33, 97, 213));
        lblEstimasi.setText("Estimasi tiba: 3 November 2026");
        pnlEstimasi.add(lblEstimasi, java.awt.BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlStatus.add(pnlEstimasi, gridBagConstraints);
        pnlWaktu.setOpaque(false);
        pnlWaktu.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlStatus.add(pnlWaktu, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKiri.add(pnlStatus, gridBagConstraints);
        pnlKosongStatus.setBackground(new java.awt.Color(255, 255, 255));
        pnlKosongStatus.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(40, 20, 40, 20)));
        pnlKosongStatus.setLayout(new java.awt.GridBagLayout());
        lblKosongIkon.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKosongIkon.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIkon.setText(" ");
        lblKosongIkon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        pnlKosongStatus.add(lblKosongIkon, gridBagConstraints);
        lblKosongJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblKosongJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblKosongJudul.setText("Belum ada data pengiriman");
        lblKosongJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosongStatus.add(lblKosongJudul, gridBagConstraints);
        lblKosongIsi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKosongIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblKosongIsi.setText("Nomor resi muncul setelah pesanan dikirim admin.");
        lblKosongIsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlKosongStatus.add(lblKosongIsi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKiri.add(pnlKosongStatus, gridBagConstraints);
        pnlKonten.add(pnlKiri, java.awt.BorderLayout.CENTER);
        pnlKananWrap.setPreferredSize(new java.awt.Dimension(330, 10));
        pnlKananWrap.setOpaque(false);
        pnlKananWrap.setLayout(new java.awt.BorderLayout());
        pnlKanan.setOpaque(false);
        pnlKanan.setLayout(new java.awt.GridBagLayout());
        pnlDetail.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlDetail.setLayout(new java.awt.GridBagLayout());
        lblDetailJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblDetailJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblDetailJudul.setText("Detail pesanan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlDetail.add(lblDetailJudul, gridBagConstraints);
        lblDetailProduk.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblDetailProduk.setForeground(new java.awt.Color(23, 43, 77));
        lblDetailProduk.setText("Laptop ASUS VivoBook");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDetail.add(lblDetailProduk, gridBagConstraints);
        lblDetailJumlah.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblDetailJumlah.setForeground(new java.awt.Color(94, 108, 132));
        lblDetailJumlah.setText("1 barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDetail.add(lblDetailJumlah, gridBagConstraints);
        lblDetailTotal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblDetailTotal.setForeground(new java.awt.Color(23, 43, 77));
        lblDetailTotal.setText("Total Rp7.500.000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlDetail.add(lblDetailTotal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKanan.add(pnlDetail, gridBagConstraints);
        pnlAlamat.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlAlamat.setLayout(new java.awt.GridBagLayout());
        lblAlamatJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblAlamatJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatJudul.setText("Alamat penerima");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlAlamat.add(lblAlamatJudul, gridBagConstraints);
        lblAlamatNama.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblAlamatNama.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatNama.setText("Alif Anugrah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlAlamat.add(lblAlamatNama, gridBagConstraints);
        lblAlamatTelp.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblAlamatTelp.setForeground(new java.awt.Color(23, 43, 77));
        lblAlamatTelp.setText("0812 3456 7801");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlAlamat.add(lblAlamatTelp, gridBagConstraints);
        lblAlamatDetail.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblAlamatDetail.setForeground(new java.awt.Color(94, 108, 132));
        lblAlamatDetail.setText("Jl. Pangeran Antasari No. 10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlAlamat.add(lblAlamatDetail, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKanan.add(pnlAlamat, gridBagConstraints);
        pnlKonfirmasi.setBackground(new java.awt.Color(255, 255, 255));
        pnlKonfirmasi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        pnlKonfirmasi.setLayout(new java.awt.GridBagLayout());
        lblKonfirmasiJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblKonfirmasiJudul.setForeground(new java.awt.Color(23, 43, 77));
        lblKonfirmasiJudul.setText("Sudah menerima paket?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlKonfirmasi.add(lblKonfirmasiJudul, gridBagConstraints);
        lblKonfirmasiIsi.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKonfirmasiIsi.setForeground(new java.awt.Color(94, 108, 132));
        lblKonfirmasiIsi.setText("<html><div width=250>Pastikan barang sudah diterima dalam kondisi baik sebelum konfirmasi.</div></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlKonfirmasi.add(lblKonfirmasiIsi, gridBagConstraints);
        btnTerima.setBackground(new java.awt.Color(33, 97, 213));
        btnTerima.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnTerima.setForeground(new java.awt.Color(255, 255, 255));
        btnTerima.setText("Konfirmasi Diterima");
        btnTerima.setFocusPainted(false);
        btnTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerimaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlKonfirmasi.add(btnTerima, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlKanan.add(pnlKonfirmasi, gridBagConstraints);
        pnlKananWrap.add(pnlKanan, java.awt.BorderLayout.NORTH);
        pnlKonten.add(pnlKananWrap, java.awt.BorderLayout.EAST);
        pnlIsi.add(pnlKonten, java.awt.BorderLayout.NORTH);
        add(pnlIsi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        jendela.buka(jendela.riwayat);
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerimaActionPerformed
        konfirmasi();
    }//GEN-LAST:event_btnTerimaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTerima;
    private javax.swing.JLabel lblAlamatDetail;
    private javax.swing.JLabel lblAlamatJudul;
    private javax.swing.JLabel lblAlamatNama;
    private javax.swing.JLabel lblAlamatTelp;
    private javax.swing.JLabel lblBadgeKirim;
    private javax.swing.JLabel lblDetailJudul;
    private javax.swing.JLabel lblDetailJumlah;
    private javax.swing.JLabel lblDetailProduk;
    private javax.swing.JLabel lblDetailTotal;
    private javax.swing.JLabel lblEstimasi;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKonfirmasiIsi;
    private javax.swing.JLabel lblKonfirmasiJudul;
    private javax.swing.JLabel lblKosongIkon;
    private javax.swing.JLabel lblKosongIsi;
    private javax.swing.JLabel lblKosongJudul;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblResiLabel;
    private javax.swing.JLabel lblResiNilai;
    private javax.swing.JLabel lblStatusJudul;
    private javax.swing.JLabel lblSubjudul;
    private javax.swing.JPanel pnlAksiKepala;
    private javax.swing.JPanel pnlAlamat;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlBadgeKirim;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlEstimasi;
    private javax.swing.JPanel pnlIsi;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlKanan;
    private javax.swing.JPanel pnlKananWrap;
    private javax.swing.JPanel pnlKepala;
    private javax.swing.JPanel pnlKiri;
    private javax.swing.JPanel pnlKonfirmasi;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlKosongStatus;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JPanel pnlResi;
    private javax.swing.JPanel pnlResiTeks;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlWaktu;
    // End of variables declaration//GEN-END:variables
}
