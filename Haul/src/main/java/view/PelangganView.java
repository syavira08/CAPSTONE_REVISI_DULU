package view;

import controller.AuthController;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Flowchart Menu Pelanggan: Katalog, Keranjang, Riwayat Pesanan, Profil, Logout (navigasi atas).
 * Tampilan dibuat dengan GUI Form Builder (PelangganView.form). Setiap menu adalah satu halaman
 * (JPanel form yang mengimplementasikan Halaman) yang ditukar memakai CardLayout.
 */
public class PelangganView extends javax.swing.JFrame {

    // halaman-halaman pelanggan
    final KatalogPanel katalog = new KatalogPanel(this);
    final KeranjangPanel keranjang = new KeranjangPanel(this);
    final CheckoutPanel checkout = new CheckoutPanel(this);
    final RiwayatPanel riwayat = new RiwayatPanel(this);
    final LacakPanel lacak = new LacakPanel(this);
    final ProfilPanel profil = new ProfilPanel(this);

    private final Map<JLabel, Halaman[]> menu = new LinkedHashMap<>();

    public PelangganView() {
        initComponents();
        UI.logo(lblLogo);
        UI.ikon(lblPengguna, "pengguna", 16, UI.TEKS);
        lblPengguna.setIconTextGap(6);

        for (Halaman h : new Halaman[]{katalog, keranjang, checkout, riwayat, lacak, profil}) {
            pnlWadah.add(UI.gulir((JComponent) h), h.getClass().getSimpleName());
        }
        // setiap menu juga menyorot halaman turunannya (mis. Checkout -> Keranjang)
        menu.put(lblMenuKatalog, new Halaman[]{katalog});
        menu.put(lblMenuKeranjang, new Halaman[]{keranjang, checkout});
        menu.put(lblMenuRiwayat, new Halaman[]{riwayat, lacak});
        menu.put(lblMenuProfil, new Halaman[]{profil});
        for (JLabel l : new JLabel[]{lblMenuKatalog, lblMenuKeranjang, lblMenuRiwayat, lblMenuProfil, lblMenuLogout}) {
            l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        UI.tema(getContentPane());
        pack();
        setLocationRelativeTo(null);
        buka(katalog);
    }

    /** Pindah ke halaman lain, muat datanya, dan sorot menu yang sesuai. */
    public void buka(Halaman h) {
        lblPengguna.setText(AuthController.getPenggunaLogin().getKeterangan());
        h.bersihkanPesan();
        h.muat();
        ((CardLayout) pnlWadah.getLayout()).show(pnlWadah, h.getClass().getSimpleName());
        menu.forEach((label, halaman) -> {
            boolean aktif = Arrays.asList(halaman).contains(h);
            label.setForeground(aktif ? UI.BIRU : UI.PUDAR);
            label.setFont(aktif ? UI.TEBAL : UI.BIASA);
        });
    }

    private void logout() {
        new AuthController().logout();
        dispose();
        new LoginView().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNav = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblMenuKatalog = new javax.swing.JLabel();
        lblMenuKeranjang = new javax.swing.JLabel();
        lblMenuRiwayat = new javax.swing.JLabel();
        lblMenuProfil = new javax.swing.JLabel();
        lblMenuLogout = new javax.swing.JLabel();
        lblPengguna = new javax.swing.JLabel();
        pnlWadah = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HAULECTRA \u2022 Pelanggan");
        setPreferredSize(new java.awt.Dimension(1280, 820));

        pnlNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlNav.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(220, 227, 237)), javax.swing.BorderFactory.createEmptyBorder(14, 10, 14, 32)));
        pnlNav.setLayout(new java.awt.BorderLayout());
        pnlMenu.setOpaque(false);
        pnlMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 22, 0));
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblLogo.setForeground(new java.awt.Color(23, 43, 77));
        lblLogo.setText("HAULECTRA");
        pnlMenu.add(lblLogo);
        lblMenuKatalog.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMenuKatalog.setForeground(new java.awt.Color(94, 108, 132));
        lblMenuKatalog.setText("Katalog");
        lblMenuKatalog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuKatalogMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMenuKatalog);
        lblMenuKeranjang.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMenuKeranjang.setForeground(new java.awt.Color(94, 108, 132));
        lblMenuKeranjang.setText("Keranjang");
        lblMenuKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuKeranjangMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMenuKeranjang);
        lblMenuRiwayat.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMenuRiwayat.setForeground(new java.awt.Color(94, 108, 132));
        lblMenuRiwayat.setText("Riwayat Pesanan");
        lblMenuRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuRiwayatMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMenuRiwayat);
        lblMenuProfil.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMenuProfil.setForeground(new java.awt.Color(94, 108, 132));
        lblMenuProfil.setText("Profil");
        lblMenuProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuProfilMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMenuProfil);
        lblMenuLogout.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMenuLogout.setForeground(new java.awt.Color(94, 108, 132));
        lblMenuLogout.setText("Logout");
        lblMenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuLogoutMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMenuLogout);
        pnlNav.add(pnlMenu, java.awt.BorderLayout.WEST);
        lblPengguna.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPengguna.setForeground(new java.awt.Color(23, 43, 77));
        lblPengguna.setText("Alif Anugrah");
        pnlNav.add(lblPengguna, java.awt.BorderLayout.EAST);
        getContentPane().add(pnlNav, java.awt.BorderLayout.NORTH);

        pnlWadah.setBackground(new java.awt.Color(243, 246, 250));
        pnlWadah.setLayout(new java.awt.CardLayout());
        getContentPane().add(pnlWadah, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMenuKatalogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuKatalogMouseClicked
        buka(katalog);
    }//GEN-LAST:event_lblMenuKatalogMouseClicked

    private void lblMenuKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuKeranjangMouseClicked
        buka(keranjang);
    }//GEN-LAST:event_lblMenuKeranjangMouseClicked

    private void lblMenuRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuRiwayatMouseClicked
        buka(riwayat);
    }//GEN-LAST:event_lblMenuRiwayatMouseClicked

    private void lblMenuProfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuProfilMouseClicked
        buka(profil);
    }//GEN-LAST:event_lblMenuProfilMouseClicked

    private void lblMenuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuLogoutMouseClicked
        logout();
    }//GEN-LAST:event_lblMenuLogoutMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuKatalog;
    private javax.swing.JLabel lblMenuKeranjang;
    private javax.swing.JLabel lblMenuLogout;
    private javax.swing.JLabel lblMenuProfil;
    private javax.swing.JLabel lblMenuRiwayat;
    private javax.swing.JLabel lblPengguna;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlWadah;
    // End of variables declaration//GEN-END:variables
}
