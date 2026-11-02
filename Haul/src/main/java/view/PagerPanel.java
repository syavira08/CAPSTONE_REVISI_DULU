package view;

import java.util.List;
import javax.swing.JButton;

/**
 * Navigasi halaman (Sebelumnya · 1 2 3 · Berikutnya) + teks "Menampilkan 1-6 dari 15 produk".
 * Dipakai tabel dan katalog. Cara pakai: data = pager.potong(semuaData); lalu tampilkan data.
 */
public class PagerPanel extends javax.swing.JPanel {

    private final int perHalaman;
    private final String satuan;
    private int halaman = 1;
    private int total = 0;
    private Runnable aksi = () -> { };

    public PagerPanel(int perHalaman, String satuan) {
        this.perHalaman = perHalaman;
        this.satuan = satuan;
        initComponents();
        UI.tombol(btnSebelumnya, UI.Jenis.SEKUNDER, true);
        UI.tombol(btnBerikutnya, UI.Jenis.SEKUNDER, true);
        UI.tema(this);
        tampilkan();
    }

    /** Dipanggil saat pengguna pindah halaman (panel memuat ulang tampilannya). */
    public void setAksi(Runnable aksi) { this.aksi = aksi; }

    /** Kembali ke halaman 1 (mis. setelah pencarian diganti). */
    public void reset() { halaman = 1; }

    /** Ambil potongan data untuk halaman aktif dan perbarui tampilan pager. */
    public <T> List<T> potong(List<T> data) {
        total = data.size();
        halaman = Math.min(halaman, jumlahHalaman());
        tampilkan();
        return data.subList(mulai(), akhir());
    }

    /** Indeks data pertama pada halaman aktif (dipakai untuk memetakan baris tabel ke data). */
    public int getMulai() { return mulai(); }

    private int jumlahHalaman() { return Math.max(1, (total + perHalaman - 1) / perHalaman); }

    private int mulai() { return (halaman - 1) * perHalaman; }

    private int akhir() { return Math.min(total, mulai() + perHalaman); }

    private void tampilkan() {
        setVisible(total > 0);
        lblInfo.setText(total == 0 ? " " : "Menampilkan " + (mulai() + 1) + "–" + akhir() + " dari " + total + " " + satuan);
        btnSebelumnya.setEnabled(halaman > 1);
        btnBerikutnya.setEnabled(halaman < jumlahHalaman());
        pnlAngka.removeAll();
        for (int i = 1; i <= jumlahHalaman(); i++) {
            final int h = i;
            JButton b = new JButton(String.valueOf(i));
            UI.tombol(b, h == halaman ? UI.Jenis.PRIMER : UI.Jenis.SEKUNDER, true);
            b.addActionListener(e -> pindah(h));
            pnlAngka.add(b);
        }
        pnlAngka.revalidate();
        pnlAngka.repaint();
        revalidate();
        repaint();
    }

    private void pindah(int h) {
        halaman = Math.max(1, Math.min(h, jumlahHalaman()));
        tampilkan();
        aksi.run();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInfo = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnSebelumnya = new javax.swing.JButton();
        pnlAngka = new javax.swing.JPanel();
        btnHalaman1 = new javax.swing.JButton();
        btnBerikutnya = new javax.swing.JButton();

        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        setLayout(new java.awt.BorderLayout());

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblInfo.setForeground(new java.awt.Color(94, 108, 132));
        lblInfo.setText("Menampilkan 1\u20138 dari 15 data");
        add(lblInfo, java.awt.BorderLayout.WEST);

        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
        btnSebelumnya.setBackground(new java.awt.Color(255, 255, 255));
        btnSebelumnya.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSebelumnya.setForeground(new java.awt.Color(23, 43, 77));
        btnSebelumnya.setText("\u2039 Sebelumnya");
        btnSebelumnya.setFocusPainted(false);
        btnSebelumnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSebelumnyaActionPerformed(evt);
            }
        });
        pnlTombol.add(btnSebelumnya);
        pnlAngka.setOpaque(false);
        pnlAngka.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
        btnHalaman1.setBackground(new java.awt.Color(33, 97, 213));
        btnHalaman1.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnHalaman1.setForeground(new java.awt.Color(255, 255, 255));
        btnHalaman1.setText("1");
        btnHalaman1.setFocusPainted(false);
        pnlAngka.add(btnHalaman1);
        pnlTombol.add(pnlAngka);
        btnBerikutnya.setBackground(new java.awt.Color(255, 255, 255));
        btnBerikutnya.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnBerikutnya.setForeground(new java.awt.Color(23, 43, 77));
        btnBerikutnya.setText("Berikutnya \u203a");
        btnBerikutnya.setFocusPainted(false);
        btnBerikutnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerikutnyaActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBerikutnya);
        add(pnlTombol, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSebelumnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSebelumnyaActionPerformed
        pindah(halaman - 1);
    }//GEN-LAST:event_btnSebelumnyaActionPerformed

    private void btnBerikutnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerikutnyaActionPerformed
        pindah(halaman + 1);
    }//GEN-LAST:event_btnBerikutnyaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBerikutnya;
    private javax.swing.JButton btnHalaman1;
    private javax.swing.JButton btnSebelumnya;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPanel pnlAngka;
    private javax.swing.JPanel pnlTombol;
    // End of variables declaration//GEN-END:variables
}
