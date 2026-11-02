package view;

import controller.ValidasiException;
import java.awt.*;
import java.awt.geom.Path2D;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * UTILITAS TAMPILAN HAULECTRA (bukan pembuat layar).
 *
 * Semua layar dibuat dengan GUI Form Builder NetBeans (file .form). Kelas ini hanya berisi
 * "styler": warna, font, border bulat, gaya tombol/input/tabel, ikon, dan format teks yang
 * dipanggil dari konstruktor setiap form setelah initComponents().
 *
 * Fondasi desain: biru #2161D5, teks #172B4D, latar #F3F6FA, garis #DCE3ED, radius 12 px.
 */
public final class UI {

    private UI() { }

    // ===================== WARNA & FONT =====================
    public static final Color BIRU = new Color(0x2161D5), BIRU_GELAP = new Color(0x1A4FB0), BIRU_MUDA = new Color(0xEAF1FC);
    public static final Color TEKS = new Color(0x172B4D), PUDAR = new Color(0x5E6C84);
    public static final Color LATAR = new Color(0xF3F6FA), GARIS = new Color(0xDCE3ED), PUTIH = Color.WHITE;
    public static final Color MERAH = new Color(0xC0343F), MERAH_MUDA = new Color(0xFDECEC);
    public static final Color HIJAU = new Color(0x2E7D32), HIJAU_MUDA = new Color(0xEAF6EC);
    public static final Color KUNING = new Color(0x8A5A00), KUNING_MUDA = new Color(0xFFF4D6);
    public static final Color BARIS_GANJIL = new Color(0xF9FAFC), KEPALA_TABEL = new Color(0xF7F9FC);

    private static final String HURUF = pilihHuruf();

    public static Font font(int gaya, int ukuran) { return new Font(HURUF, gaya, ukuran); }

    public static final Font TEBAL = font(Font.BOLD, 13), BIASA = font(Font.PLAIN, 13), KECIL = font(Font.PLAIN, 12);

    private static String pilihHuruf() {
        java.util.List<String> ada = java.util.Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        for (String f : new String[]{"Inter", "Segoe UI", "SansSerif"}) if (ada.contains(f)) return f;
        return "SansSerif";
    }

    /** Dipanggil sekali di MainApp. */
    public static void pasangTema() {
        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
        } catch (Exception ignored) { }
        for (Object k : UIManager.getDefaults().keySet().toArray()) {
            if (k.toString().endsWith(".font")) UIManager.put(k, BIASA);
        }
        UIManager.put("Panel.background", LATAR);
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
        UIManager.put("List.selectionBackground", BIRU_MUDA);
        UIManager.put("List.selectionForeground", TEKS);
        UIManager.put("Table.selectionBackground", BIRU_MUDA);
        UIManager.put("Table.selectionForeground", TEKS);
        UIManager.put("ScrollBarUI", PitaGulir.class.getName());
        UIManager.put("OptionPane.messageFont", BIASA);
    }

    /** Samakan jenis huruf seluruh komponen (gaya & ukuran dari form tetap dipakai). */
    public static void tema(Component c) {
        Font f = c.getFont();
        if (f != null && !f.getFamily().equals(HURUF)) c.setFont(new Font(HURUF, f.getStyle(), f.getSize()));
        if (c instanceof Container k) for (Component a : k.getComponents()) tema(a);
    }

    // ===================== FORMAT =====================
    private static final Locale ID = Locale.forLanguageTag("id-ID");

    public static String rupiah(BigDecimal n) {
        return n == null ? "-" : "Rp" + String.format(ID, "%,d", n.longValue());
    }

    public static String tanggal(LocalDate t) {
        return t == null ? "-" : t.format(DateTimeFormatter.ofPattern("dd MMM yyyy", ID));
    }

    public static String tanggal(LocalDateTime t) {
        return t == null ? "-" : t.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH.mm", ID));
    }

    /** 081234567801 -> 0812 3456 7801 */
    public static String telepon(String t) {
        return t == null ? "-" : t.replaceAll("(\\d{4})(?=\\d)", "$1 ");
    }

    /** Teks panjang yang turun baris pada lebar tertentu (label HTML). */
    public static String bungkus(String t, int lebar) {
        return "<html><div width=" + lebar + ">" + t + "</div></html>";
    }

    static Graphics2D halus(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return g2;
    }

    // ===================== BORDER BULAT (kartu, banner, badge) =====================
    /** Border yang menggambar isi + garis tepi bersudut membulat, lalu memberi padding. */
    static class KotakBorder extends AbstractBorder {
        private final Color isi, garis;
        private final int radius, gapAtas;
        private final Insets padding;

        KotakBorder(Color isi, Color garis, int radius, Insets padding, int gapAtas) {
            this.isi = isi;
            this.garis = garis;
            this.radius = radius;
            this.padding = padding;
            this.gapAtas = gapAtas;
        }

        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = halus(g);
            int t = y + gapAtas, tinggi = h - gapAtas;
            g2.setColor(isi);
            g2.fillRoundRect(x, t, w - 1, tinggi - 1, radius, radius);
            if (garis != null) {
                g2.setColor(garis);
                g2.drawRoundRect(x, t, w - 1, tinggi - 1, radius, radius);
            }
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c) {
            return new Insets(padding.top + gapAtas, padding.left, padding.bottom, padding.right);
        }

        @Override public Insets getBorderInsets(Component c, Insets i) {
            Insets n = getBorderInsets(c);
            i.set(n.top, n.left, n.bottom, n.right);
            return i;
        }
    }

    /** Kartu putih bersudut membulat (padding bawaan 18/20). */
    public static void kartu(JComponent p) { kartu(p, 18, 20, 18, 20); }

    public static void kartu(JComponent p, int atas, int kiri, int bawah, int kanan) {
        p.setOpaque(false);
        p.setBorder(new KotakBorder(PUTIH, GARIS, 12, new Insets(atas, kiri, bawah, kanan), 0));
    }

    /** Kotak biru muda untuk menyorot informasi (mis. banner promo). */
    public static void sorotan(JComponent p, int atas, int kiri, int bawah, int kanan) {
        p.setOpaque(false);
        p.setBorder(new KotakBorder(BIRU_MUDA, null, 12, new Insets(atas, kiri, bawah, kanan), 0));
    }

    // ===================== KOTAK PESAN =====================
    public enum Pesan { INFO, GALAT, SUKSES }

    /** Pesan di bagian atas halaman (jarak 14 px dari judul). Sukses hilang sendiri setelah 4 detik. */
    public static void pesan(JPanel kotak, JLabel teks, Pesan jenis, String isi) {
        terapkanPesan(kotak, teks, jenis, isi, 14);
        Timer lama = (Timer) kotak.getClientProperty("timer");
        if (lama != null) lama.stop();
        if (jenis == Pesan.SUKSES && isi != null) {
            Timer t = new Timer(4000, e -> kotak.setVisible(false));
            t.setRepeats(false);
            t.start();
            kotak.putClientProperty("timer", t);
        }
    }

    /** Kotak informasi tetap di dalam isi halaman (tanpa jarak atas). */
    public static void info(JPanel kotak, JLabel teks, String isi) {
        terapkanPesan(kotak, teks, Pesan.INFO, isi, 0);
    }

    private static void terapkanPesan(JPanel kotak, JLabel teks, Pesan jenis, String isi, int gap) {
        Color depan = jenis == Pesan.GALAT ? MERAH : jenis == Pesan.SUKSES ? HIJAU : BIRU;
        Color latar = jenis == Pesan.GALAT ? MERAH_MUDA : jenis == Pesan.SUKSES ? HIJAU_MUDA : BIRU_MUDA;
        kotak.setOpaque(false);
        kotak.setBorder(new KotakBorder(latar, null, 12, new Insets(10, 14, 10, 14), gap));
        teks.setForeground(depan);
        teks.setFont(KECIL);
        teks.setIconTextGap(10);
        teks.setIcon(new Ikon(jenis == Pesan.SUKSES ? "cek" : jenis == Pesan.GALAT ? "galat" : "info", 16, depan));
        teks.setText("<html>" + (isi == null ? "" : isi) + "</html>");
        kotak.setVisible(isi != null && !isi.isEmpty());
        kotak.revalidate();
        kotak.repaint();
    }

    public static void sembunyi(JComponent c) { c.setVisible(false); }

    // ===================== BADGE =====================
    /** Badge status; warna dipilih dari teksnya. */
    public static void badge(JLabel l, String teks) {
        Color latar, depan;
        if (teks.startsWith("Menunggu") || teks.startsWith("Siap") || teks.equals("Terjadwal")) {
            latar = KUNING_MUDA; depan = KUNING;
        } else if (teks.equals("Selesai") || teks.equals("Diterima") || teks.equals("Aktif")) {
            latar = HIJAU_MUDA; depan = HIJAU;
        } else if (teks.equals("Dibatalkan")) {
            latar = MERAH_MUDA; depan = MERAH;
        } else if (teks.equals("Berakhir")) {
            latar = new Color(0xEEF1F5); depan = PUDAR;
        } else {
            latar = BIRU_MUDA; depan = BIRU;
        }
        l.setText(teks);
        l.setOpaque(false);
        l.setFont(font(Font.BOLD, 11));
        l.setForeground(depan);
        l.setBorder(new KotakBorder(latar, null, 8, new Insets(3, 8, 3, 8), 0));
    }

    /** Kolom status pada tabel tampil sebagai badge. */
    public static void kolomBadge(JTable t, int... kolom) {
        for (int k : kolom) {
            t.getColumnModel().getColumn(k).setCellRenderer(new DefaultTableCellRenderer() {
                @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int c) {
                    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
                    p.setBackground(s ? BIRU_MUDA : r % 2 == 0 ? PUTIH : BARIS_GANJIL);
                    JLabel b = new JLabel();
                    badge(b, String.valueOf(v));
                    p.add(b);
                    return p;
                }
            });
        }
    }

    // ===================== TOMBOL =====================
    public enum Jenis { PRIMER, SEKUNDER, BAHAYA }

    /** Gaya tombol: bersudut membulat, warna sesuai jenis, kursor tangan. */
    public static void tombol(JButton b, Jenis jenis) { tombol(b, jenis, false); }

    public static void tombol(JButton b, Jenis jenis, boolean kecil) {
        b.setUI(new TombolUI(jenis));
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setRolloverEnabled(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setFont(kecil ? font(Font.BOLD, 12) : TEBAL);
        b.setForeground(jenis == Jenis.SEKUNDER ? TEKS : PUTIH);
        b.setBorder(BorderFactory.createEmptyBorder(kecil ? 6 : 9, kecil ? 12 : 16, kecil ? 6 : 9, kecil ? 12 : 16));
    }

    public static void primer(JButton b) { tombol(b, Jenis.PRIMER); }
    public static void sekunder(JButton b) { tombol(b, Jenis.SEKUNDER); }
    public static void bahaya(JButton b) { tombol(b, Jenis.BAHAYA); }

    static class TombolUI extends BasicButtonUI {
        private final Jenis jenis;

        TombolUI(Jenis jenis) { this.jenis = jenis; }

        @Override public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = halus(g);
            boolean hover = b.getModel().isRollover() && b.isEnabled();
            Color latar = switch (jenis) {
                case PRIMER -> hover ? BIRU_GELAP : BIRU;
                case BAHAYA -> hover ? MERAH.darker() : MERAH;
                default -> hover ? LATAR : PUTIH;
            };
            if (!b.isEnabled()) latar = jenis == Jenis.SEKUNDER ? LATAR : new Color(0xA8BCE6);
            g2.setColor(latar);
            g2.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 12, 12);
            if (jenis == Jenis.SEKUNDER) {
                g2.setColor(GARIS);
                g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 12, 12);
            }
            g2.dispose();
            super.paint(g, c);
        }

        @Override protected void paintText(Graphics g, AbstractButton b, Rectangle r, String teks) {
            Graphics2D g2 = halus(g);
            g2.setFont(b.getFont());
            g2.setColor(b.isEnabled() ? b.getForeground() : jenis == Jenis.SEKUNDER ? new Color(0xA0AABA) : PUTIH);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(teks, r.x, r.y + fm.getAscent());
            g2.dispose();
        }
    }

    // ===================== INPUT =====================
    /** Border input bersudut; warnanya berubah merah saat error. */
    static class Bingkai extends AbstractBorder {
        Color warna = GARIS;
        private final Insets padding;

        Bingkai(Insets padding) { this.padding = padding; }

        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = halus(g);
            g2.setColor(warna);
            g2.drawRoundRect(x, y, w - 1, h - 1, 12, 12);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c) { return padding; }
    }

    /** Kolom isian satu baris. placeholder boleh null. (Password tetap memakai UI bawaan.) */
    public static void input(JTextField t, String placeholder) {
        if (!(t instanceof JPasswordField)) t.setUI(new IsianUI());
        t.putClientProperty("placeholder", placeholder);
        t.setOpaque(false);
        t.setFont(BIASA);
        t.setForeground(TEKS);
        t.setBackground(PUTIH);
        t.setCaretColor(TEKS);
        t.setBorder(new Bingkai(new Insets(8, 12, 8, 12)));
        t.setPreferredSize(new Dimension(t.getPreferredSize().width, 38));
    }

    /** Area teks di dalam JScrollPane. */
    public static void area(JTextArea ta, JScrollPane sp) {
        ta.setFont(BIASA);
        ta.setForeground(TEKS);
        ta.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        sp.setBorder(new Bingkai(new Insets(2, 2, 2, 2)));
        sp.setViewportBorder(null);
        sp.getViewport().setBackground(PUTIH);
    }

    static class IsianUI extends BasicTextFieldUI {
        @Override public void update(Graphics g, JComponent c) {
            Graphics2D g2 = halus(g);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 12, 12);
            g2.dispose();
            paint(g, c);
        }

        @Override protected void paintSafely(Graphics g) {
            super.paintSafely(g);
            javax.swing.text.JTextComponent t = getComponent();
            String placeholder = (String) t.getClientProperty("placeholder");
            if (t.getText().isEmpty() && !t.isFocusOwner() && placeholder != null) {
                Graphics2D g2 = halus(g);
                g2.setColor(new Color(0x8993A4));
                g2.setFont(t.getFont());
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(placeholder, t.getInsets().left,
                        (t.getHeight() - fm.getHeight()) / 2 + fm.getAscent());
                g2.dispose();
            }
        }
    }

    /** Gaya dropdown: border bulat + panah garis. */
    public static void combo(JComboBox<?> c) {
        c.setUI(new BasicComboBoxUI() {
            @Override protected JButton createArrowButton() {
                JButton b = new JButton(new Ikon("panah", 14, PUDAR));
                b.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 10));
                b.setContentAreaFilled(false);
                b.setFocusable(false);
                return b;
            }

            @Override public void update(Graphics g, JComponent x) {
                Graphics2D g2 = halus(g);
                g2.setColor(PUTIH);
                g2.fillRoundRect(0, 0, x.getWidth(), x.getHeight(), 12, 12);
                g2.dispose();
                paint(g, x);
            }

            @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) { }
        });
        c.setOpaque(false);
        c.setFont(BIASA);
        c.setForeground(TEKS);
        c.setBackground(PUTIH);
        c.setBorder(new Bingkai(new Insets(3, 6, 3, 2)));
        c.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> l, Object v, int i, boolean s, boolean f) {
                super.getListCellRendererComponent(l, v, i, s, false);
                setBorder(BorderFactory.createEmptyBorder(7, 10, 7, 10));
                setForeground(TEKS);
                setBackground(s && i >= 0 ? BIRU_MUDA : PUTIH);
                return this;
            }
        });
    }

    // ===================== ERROR PER FIELD =====================
    /** Tampilkan / hapus pesan merah di bawah satu input. pesan = null untuk menghapus. */
    public static void setError(JComponent input, JLabel err, String pesan) {
        err.setText(pesan == null ? " " : pesan);
        err.setVisible(pesan != null);
        if (input.getBorder() instanceof Bingkai b) {
            b.warna = pesan == null ? GARIS : MERAH;
            input.repaint();
        }
        Container p = err.getParent();
        if (p != null) {
            p.revalidate();
            p.repaint();
        }
    }

    /**
     * Terapkan ValidasiException ke banyak field sekaligus.
     * Pasangan berulang: nama field (String), input (JComponent), label error (JLabel).
     * e = null berarti hapus semua error.
     */
    public static void tampilkanError(ValidasiException e, Object... trio) {
        for (int i = 0; i + 2 < trio.length; i += 3) {
            setError((JComponent) trio[i + 1], (JLabel) trio[i + 2], e == null ? null : e.getPesan((String) trio[i]));
        }
    }

    // ===================== TABEL =====================
    /** Tabel bergaya desain (header abu, baris selang-seling). */
    public static void tabel(JTable t, JScrollPane sp) {
        t.setRowHeight(44);
        t.setFont(KECIL);
        t.setShowVerticalLines(false);
        t.setShowHorizontalLines(true);
        t.setIntercellSpacing(new Dimension(0, 1));
        t.setGridColor(GARIS);
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.setFillsViewportHeight(true);
        JTableHeader h = t.getTableHeader();
        h.setReorderingAllowed(false);
        h.setPreferredSize(new Dimension(0, 40));
        h.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(tb, v, s, f, r, c);
                setFont(font(Font.BOLD, 12));
                setForeground(PUDAR);
                setBackground(KEPALA_TABEL);
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GARIS),
                        BorderFactory.createEmptyBorder(0, 12, 0, 8)));
                return this;
            }
        });
        t.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(tb, v, s, false, r, c);
                setFont(KECIL);
                setForeground(TEKS);
                setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 8));
                if (!s) setBackground(r % 2 == 0 ? PUTIH : BARIS_GANJIL);
                return this;
            }
        });
        sp.setBorder(BorderFactory.createLineBorder(GARIS));
        sp.getViewport().setBackground(PUTIH);
    }

    /** Tinggi tabel mengikuti jumlah baris (tanpa ruang kosong yang panjang). */
    public static void sesuaikanTinggi(JScrollPane sp, JTable t) {
        int baris = Math.max(t.getRowCount(), 2);
        sp.setPreferredSize(new Dimension(sp.getPreferredSize().width, 42 + 45 * baris));
        sp.revalidate();
    }

    /** Panel pemisah setebal 1 px berwarna garis. */
    public static void garis(JComponent... g) {
        for (JComponent c : g) {
            c.setOpaque(true);
            c.setBackground(GARIS);
            c.setPreferredSize(new Dimension(10, 1));
            c.setMinimumSize(new Dimension(10, 1));
        }
    }

    /** Kolom samping selebar tetap, tinggi mengikuti isinya (dipakai halaman dua kolom). */
    public static void lebarTetap(JPanel p, int lebar) {
        Component isi = p.getComponent(0);
        p.removeAll();
        p.setPreferredSize(null);
        p.setLayout(new BorderLayout() {
            @Override public Dimension preferredLayoutSize(Container c) {
                return new Dimension(lebar, super.preferredLayoutSize(c).height);
            }
        });
        p.add(isi, BorderLayout.NORTH);
    }

    // ===================== DAFTAR DINAMIS =====================
    /** Tambahkan satu komponen ke bawah panel ber-GridBagLayout (daftar baris dinamis). */
    public static void tambahBaris(JPanel wadah, Component c, int jarak) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = wadah.getComponentCount();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        g.anchor = GridBagConstraints.NORTHWEST;
        g.insets = new Insets(g.gridy == 0 ? 0 : jarak, 0, 0, 0);
        wadah.add(c, g);
    }

    // ===================== GULIR =====================
    /** Bungkus halaman agar selalu selebar jendela dan bisa digulir ke bawah. */
    public static JScrollPane gulir(JComponent halaman) {
        JPanel pembungkus = new Pembungkus();
        pembungkus.add(halaman, BorderLayout.CENTER);
        JScrollPane sp = new JScrollPane(pembungkus);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getVerticalScrollBar().setUnitIncrement(18);
        sp.getViewport().setBackground(LATAR);
        return sp;
    }

    private static class Pembungkus extends JPanel implements Scrollable {
        Pembungkus() { super(new BorderLayout()); setBackground(LATAR); }
        @Override public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        @Override public int getScrollableUnitIncrement(Rectangle r, int o, int d) { return 18; }
        @Override public int getScrollableBlockIncrement(Rectangle r, int o, int d) { return r.height; }
        @Override public boolean getScrollableTracksViewportWidth() { return true; }
        @Override public boolean getScrollableTracksViewportHeight() {
            return getParent() != null && getParent().getHeight() > getPreferredSize().height;
        }
    }

    /** Scrollbar tipis tanpa tombol panah. */
    public static class PitaGulir extends BasicScrollBarUI {
        public static ComponentUI createUI(JComponent c) { return new PitaGulir(); }

        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(0xC3CCDA);
            trackColor = LATAR;
        }

        @Override protected JButton createDecreaseButton(int o) { return nol(); }
        @Override protected JButton createIncreaseButton(int o) { return nol(); }

        private JButton nol() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }

        @Override public Dimension getPreferredSize(JComponent c) { return new Dimension(10, 10); }

        @Override protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = halus(g);
            g2.setColor(thumbColor);
            g2.fillRoundRect(r.x + 2, r.y + 2, r.width - 4, r.height - 4, 8, 8);
            g2.dispose();
        }

        @Override protected void paintTrack(Graphics g, JComponent c, Rectangle r) { }
    }

    // ===================== DIALOG =====================
    /** Dialog konfirmasi (mis. "Hapus produk?"). true jika tombol aksi ditekan. */
    public static boolean konfirmasi(Component induk, String judul, String pesan, String labelYa, boolean bahaya) {
        return DialogKonfirmasi.tampilkan(induk, judul, pesan, labelYa, bahaya);
    }

    // ===================== LOGO, IKON, GAMBAR PRODUK =====================
    public static void logo(JLabel l) {
        l.setIcon(new Ikon("logo", 28, BIRU));
        l.setIconTextGap(8);
    }

    public static void ikon(JLabel l, String nama, int ukuran, Color warna) {
        l.setIcon(new Ikon(nama, ukuran, warna));
    }

    /** Ikon garis sederhana yang digambar dengan Java2D. */
    public static class Ikon implements Icon {
        private final String nama;
        private final int ukuran;
        private final Color warna;

        public Ikon(String nama, int ukuran, Color warna) {
            this.nama = nama;
            this.ukuran = ukuran;
            this.warna = warna;
        }

        @Override public int getIconWidth() { return ukuran; }
        @Override public int getIconHeight() { return ukuran; }

        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = halus(g);
            g2.translate(x, y);
            g2.scale(ukuran / 24.0, ukuran / 24.0);
            g2.setColor(warna);
            g2.setStroke(new BasicStroke(1.7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            switch (nama) {
                case "logo" -> {
                    g2.fillRoundRect(0, 0, 24, 24, 7, 7);
                    g2.setColor(PUTIH);
                    g2.drawPolygon(new int[]{14, 7, 12, 11, 18, 13}, new int[]{3, 14, 14, 21, 10, 10}, 6);
                }
                case "panah" -> g2.drawPolyline(new int[]{7, 12, 17}, new int[]{10, 15, 10}, 3);
                case "info" -> { g2.drawOval(3, 3, 18, 18); g2.drawLine(12, 11, 12, 16); g2.fillOval(11, 7, 2, 2); }
                case "galat" -> { g2.drawOval(3, 3, 18, 18); g2.drawLine(12, 8, 12, 13); g2.fillOval(11, 15, 2, 2); }
                case "cek" -> { g2.drawOval(3, 3, 18, 18); g2.drawPolyline(new int[]{8, 11, 16}, new int[]{12, 15, 9}, 3); }
                case "pengguna" -> { g2.drawOval(3, 3, 18, 18); g2.drawOval(9, 7, 6, 6); g2.drawArc(6, 14, 12, 10, 20, 140); }
                case "kotak", "produk" -> {
                    g2.drawPolygon(new int[]{12, 20, 20, 12, 4, 4}, new int[]{3, 7, 16, 21, 16, 7}, 6);
                    g2.drawPolyline(new int[]{4, 12, 20}, new int[]{7, 11, 7}, 3);
                    g2.drawLine(12, 11, 12, 21);
                }
                case "promo" -> { g2.drawRoundRect(3, 6, 18, 12, 3, 3); g2.drawLine(9, 8, 9, 16); }
                case "truk" -> {
                    g2.drawRect(2, 7, 12, 9);
                    g2.drawPolyline(new int[]{14, 18, 21, 21, 14}, new int[]{10, 10, 13, 16, 16}, 5);
                    g2.drawOval(5, 15, 4, 4);
                    g2.drawOval(15, 15, 4, 4);
                }
                case "pesanan" -> { g2.drawRoundRect(5, 4, 14, 17, 3, 3); g2.drawLine(8, 11, 16, 11); g2.drawLine(8, 15, 14, 15); }
                case "pelanggan" -> { g2.drawOval(5, 5, 6, 6); g2.drawArc(2, 13, 12, 10, 20, 140); g2.drawOval(14, 6, 5, 5); }
                case "keluar" -> {
                    g2.drawPolyline(new int[]{10, 5, 5, 10}, new int[]{4, 4, 20, 20}, 4);
                    g2.drawLine(9, 12, 20, 12);
                    g2.drawPolyline(new int[]{16, 20, 16}, new int[]{8, 12, 16}, 3);
                }
                default -> { }
            }
            g2.dispose();
        }
    }

    /** Ilustrasi produk otomatis dari nama produk (database tidak menyimpan foto). */
    public static class GambarProduk implements Icon {
        private final String n;
        private final int lebar, tinggi;

        public GambarProduk(String nama, int lebar, int tinggi) {
            this.n = nama.toLowerCase();
            this.lebar = lebar;
            this.tinggi = tinggi;
        }

        @Override public int getIconWidth() { return lebar; }
        @Override public int getIconHeight() { return tinggi; }

        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = halus(g);
            g2.translate(x, y);
            g2.setPaint(new GradientPaint(0, 0, new Color(0xECEFF3), 0, tinggi, new Color(0xD5DAE1)));
            g2.fillRoundRect(0, 0, lebar, tinggi, 10, 10);
            double s = Math.min(lebar, tinggi) / 100.0;
            g2.translate((lebar - 100 * s) / 2, (tinggi - 100 * s) / 2);
            g2.scale(s, s);
            Color gelap = new Color(0x2B3445), aksen = new Color(0x3D7BE6);
            g2.setColor(gelap);
            g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (n.contains("laptop") || n.contains("notebook")) {
                g2.fillRoundRect(22, 26, 56, 36, 4, 4);
                g2.setColor(aksen); g2.fillRect(25, 29, 50, 30);
                g2.setColor(new Color(0xB9C0CB)); g2.fillPolygon(new int[]{18, 82, 88, 12}, new int[]{64, 64, 72, 72}, 4);
            } else if (n.contains("smartphone") || n.contains("ponsel") || n.contains("galaxy")) {
                g2.fillRoundRect(34, 16, 32, 68, 10, 10);
                g2.setColor(aksen); g2.fillRoundRect(37, 20, 26, 60, 6, 6);
            } else if (n.contains("headphone") || n.contains("headset")) {
                g2.drawArc(24, 18, 52, 56, 0, 180);
                g2.fillRoundRect(17, 46, 16, 30, 10, 10);
                g2.fillRoundRect(67, 46, 16, 30, 10, 10);
            } else if (n.contains("mouse") && !n.contains("pad")) {
                g2.fillRoundRect(34, 22, 32, 56, 30, 34);
            } else if (n.contains("monitor") || n.contains("webcam")) {
                g2.fillRoundRect(14, 20, 72, 46, 4, 4);
                g2.setColor(aksen); g2.fillRect(17, 23, 66, 40);
                g2.setColor(gelap); g2.fillRect(46, 66, 8, 12); g2.fillRect(34, 77, 32, 4);
            } else if (n.contains("keyboard") || n.contains("mousepad")) {
                g2.fillRoundRect(10, 34, 80, 32, 6, 6);
                g2.setColor(new Color(0xC9CFD8));
                for (int r = 0; r < 3; r++) for (int k = 0; k < 10; k++) g2.fillRect(14 + k * 7, 38 + r * 8, 5, 5);
            } else if (n.contains("speaker")) {
                g2.setColor(aksen); g2.fillRoundRect(30, 18, 40, 64, 18, 18);
            } else if (n.contains("kabel") || n.contains("charger")) {
                Path2D p = new Path2D.Double();
                p.moveTo(24, 30); p.curveTo(24, 72, 76, 28, 76, 70);
                g2.draw(p);
            } else {   // flashdisk, harddisk, ssd, ram, dll.
                g2.fillRoundRect(30, 22, 40, 58, 8, 8);
                g2.setColor(aksen); g2.fillRoundRect(40, 34, 20, 6, 3, 3);
            }
            g2.dispose();
        }
    }
}
