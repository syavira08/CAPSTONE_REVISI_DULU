package view;

import controller.AuthController;
import controller.PesananController;

/** Menu utama pelanggan: Beranda, Keranjang, Riwayat Pesanan, Profil, dan Logout. */
public class PelangganView extends javax.swing.JFrame {

    private final BerandaPanel beranda = new BerandaPanel(this);
    private final KeranjangPanel keranjang = new KeranjangPanel(this);
    private final CheckoutPanel checkout = new CheckoutPanel(this);
    private final RiwayatPanel riwayat = new RiwayatPanel(this);
    private final ProfilPanel profil = new ProfilPanel(this);
    private final java.util.Map<String, Halaman> halaman = new java.util.LinkedHashMap<>();

    public PelangganView() {
        initComponents();
        // logika layout controller: setiap halaman (JPanel Form) dipasang ke CardLayout
        daftarkan("beranda", beranda);
        daftarkan("keranjang", keranjang);
        daftarkan("checkout", checkout);
        daftarkan("riwayat", riwayat);
        daftarkan("profil", profil);
        perbaruiNama();
        buka("beranda");
    }

    private void daftarkan(String nama, javax.swing.JPanel panel) {
        halaman.put(nama, (Halaman) panel);
        pnlHalaman.add(panel, nama);
    }

    /** Buka halaman lalu muat ulang datanya (POLYMORPHISM: Halaman.muat()). */
    public void buka(String nama) {
        ((java.awt.CardLayout) pnlHalaman.getLayout()).show(pnlHalaman, nama);
        halaman.get(nama).muat();
        String menu = nama.equals("checkout") ? "keranjang" : nama;
        tandai(btnBeranda, "beranda", menu);
        tandai(btnKeranjang, "keranjang", menu);
        tandai(btnRiwayat, "riwayat", menu);
        tandai(btnProfil, "profil", menu);
        perbaruiKeranjang();
    }

    private void tandai(javax.swing.JButton tombol, String nama, String aktif) {
        boolean ya = nama.equals(aktif);
        tombol.setForeground(ya ? Gaya.BIRU : Gaya.REDUP);
        tombol.setIcon(Gaya.ikon(ya ? nama + "_aktif" : nama));
        tombol.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, ya ? Gaya.BIRU : java.awt.Color.WHITE),
                javax.swing.BorderFactory.createEmptyBorder(3, 12, 0, 12)));
    }

    /** Jumlah barang di menu Keranjang. */
    public void perbaruiKeranjang() {
        int n = new PesananController().getKeranjang().getJumlahBarang();
        btnKeranjang.setText(n == 0 ? "Keranjang" : "Keranjang (" + n + ")");
    }

    public void perbaruiNama() {
        lblNama.setText(AuthController.getPenggunaLogin().getNama());
    }

    /**
     * Kode di bawah ini dibuat oleh GUI Form Builder NetBeans (jangan diubah manual).
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlAtas = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlNav = new javax.swing.JPanel();
        btnBeranda = new javax.swing.JButton();
        btnKeranjang = new javax.swing.JButton();
        btnRiwayat = new javax.swing.JButton();
        btnProfil = new javax.swing.JButton();
        pnlAkun = new javax.swing.JPanel();
        lblNama = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlHalaman = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1180, 720));
        setTitle("HAULECTRA");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlAtas.setBackground(new java.awt.Color(255, 255, 255));
        pnlAtas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(228, 231, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 28)));
        pnlAtas.setPreferredSize(new java.awt.Dimension(1280, 64));
        pnlAtas.setLayout(new java.awt.BorderLayout());
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(16, 24, 40));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/logo.png"))); // NOI18N
        lblLogo.setIconTextGap(10);
        lblLogo.setText("HAULECTRA");
        pnlAtas.add(lblLogo, java.awt.BorderLayout.WEST);
        pnlNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlNav.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 32, 0, 0));
        pnlNav.setOpaque(false);
        pnlNav.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
        btnBeranda.setBackground(new java.awt.Color(255, 255, 255));
        btnBeranda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnBeranda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeranda.setFocusPainted(false);
        btnBeranda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBeranda.setForeground(new java.awt.Color(102, 112, 133));
        btnBeranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/beranda.png"))); // NOI18N
        btnBeranda.setIconTextGap(10);
        btnBeranda.setPreferredSize(new java.awt.Dimension(130, 64));
        btnBeranda.setText("Beranda");
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerandaActionPerformed(evt);
            }
        });
        pnlNav.add(btnBeranda);
        btnKeranjang.setBackground(new java.awt.Color(255, 255, 255));
        btnKeranjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnKeranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeranjang.setFocusPainted(false);
        btnKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKeranjang.setForeground(new java.awt.Color(102, 112, 133));
        btnKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/keranjang.png"))); // NOI18N
        btnKeranjang.setIconTextGap(10);
        btnKeranjang.setPreferredSize(new java.awt.Dimension(160, 64));
        btnKeranjang.setText("Keranjang");
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });
        pnlNav.add(btnKeranjang);
        btnRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        btnRiwayat.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnRiwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRiwayat.setFocusPainted(false);
        btnRiwayat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRiwayat.setForeground(new java.awt.Color(102, 112, 133));
        btnRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/riwayat.png"))); // NOI18N
        btnRiwayat.setIconTextGap(10);
        btnRiwayat.setPreferredSize(new java.awt.Dimension(190, 64));
        btnRiwayat.setText("Riwayat Pesanan");
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });
        pnlNav.add(btnRiwayat);
        btnProfil.setBackground(new java.awt.Color(255, 255, 255));
        btnProfil.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btnProfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfil.setFocusPainted(false);
        btnProfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProfil.setForeground(new java.awt.Color(102, 112, 133));
        btnProfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/profil.png"))); // NOI18N
        btnProfil.setIconTextGap(10);
        btnProfil.setPreferredSize(new java.awt.Dimension(110, 64));
        btnProfil.setText("Profil");
        btnProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfilActionPerformed(evt);
            }
        });
        pnlNav.add(btnProfil);
        pnlAtas.add(pnlNav, java.awt.BorderLayout.CENTER);

        pnlAkun.setBackground(new java.awt.Color(255, 255, 255));
        pnlAkun.setOpaque(false);
        pnlAkun.setLayout(new java.awt.GridBagLayout());
        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(52, 64, 84));
        lblNama.setText("Nama Pelanggan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlAkun.add(lblNama, gridBagConstraints);
        btnLogout.setBackground(new java.awt.Color(217, 45, 32));
        btnLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ikon/logout_putih.png"))); // NOI18N
        btnLogout.setIconTextGap(8);
        btnLogout.setPreferredSize(new java.awt.Dimension(112, 36));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlAkun.add(btnLogout, gridBagConstraints);
        pnlAtas.add(pnlAkun, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlAtas, java.awt.BorderLayout.NORTH);

        pnlHalaman.setBackground(new java.awt.Color(245, 247, 251));
        pnlHalaman.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlHalaman.setLayout(new java.awt.CardLayout());
        getContentPane().add(pnlHalaman, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerandaActionPerformed
        buka("beranda");
    }//GEN-LAST:event_btnBerandaActionPerformed

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        buka("keranjang");
    }//GEN-LAST:event_btnKeranjangActionPerformed

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiwayatActionPerformed
        buka("riwayat");
    }//GEN-LAST:event_btnRiwayatActionPerformed

    private void btnProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfilActionPerformed
        buka("profil");
    }//GEN-LAST:event_btnProfilActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        if (KonfirmasiDialog.tanya(this, "Logout", "Yakin ingin keluar dari akun Anda?", "Logout",
                "dialog_logout", true)) {
            new AuthController().logout();
            dispose();
            new LoginView().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProfil;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNama;
    private javax.swing.JPanel pnlAkun;
    private javax.swing.JPanel pnlAtas;
    private javax.swing.JPanel pnlHalaman;
    private javax.swing.JPanel pnlNav;
    // End of variables declaration//GEN-END:variables
}
