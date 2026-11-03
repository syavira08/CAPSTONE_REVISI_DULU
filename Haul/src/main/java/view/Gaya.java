package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import model.Pesanan;

/**
 * Utilitas pendukung tampilan (bukan layar): warna, format rupiah/tanggal, ikon, foto produk,
 * gaya tabel, dan renderer badge status. Semua layar dibuat dengan GUI Form Builder (.form).
 */
public final class Gaya {

    public static final Color BIRU = new Color(31, 94, 219);
    public static final Color BIRU_MUDA = new Color(232, 240, 255);
    public static final Color MERAH = new Color(217, 45, 32);
    public static final Color HIJAU = new Color(18, 128, 92);
    public static final Color TEKS = new Color(16, 24, 40);
    public static final Color REDUP = new Color(102, 112, 133);
    public static final Color GARIS = new Color(228, 231, 236);
    public static final Color LATAR = new Color(245, 247, 251);

    private static final Locale ID = Locale.forLanguageTag("id-ID");
    private static final Map<String, ImageIcon> CACHE_IKON = new HashMap<>();

    private Gaya() { }

    /** Look and feel Metal polos: warna komponen mengikuti properti yang diatur di GUI Form Builder. */
    public static void pasangTema() {
        try {
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            return;   // tetap jalan dengan tampilan bawaan
        }
        Color abu = new Color(208, 213, 221);
        UIManager.put("Button.select", new Color(0, 0, 0, 30));
        UIManager.put("ScrollBar.width", 12);
        for (String k : new String[]{"ScrollBar.thumb", "ScrollBar.thumbHighlight", "ScrollBar.thumbShadow",
            "ScrollBar.thumbDarkShadow"}) {
            UIManager.put(k, abu);
        }
        UIManager.put("ScrollBar.track", Color.WHITE);
        UIManager.put("ScrollBar.shadow", new Color(240, 242, 245));
        UIManager.put("ScrollBar.highlight", Color.WHITE);
    }

    // ===================== format =====================

    /** 7500000 -> "Rp7.500.000" */
    public static String rupiah(BigDecimal nilai) {
        NumberFormat f = NumberFormat.getIntegerInstance(ID);
        return "Rp" + f.format(nilai == null ? 0 : nilai.longValue());
    }

    public static String tanggal(LocalDateTime waktu) {
        return waktu == null ? "-" : waktu.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH.mm", ID));
    }

    public static String tanggal(LocalDate tgl) {
        return tgl == null ? "-" : tgl.format(DateTimeFormatter.ofPattern("dd MMM yyyy", ID));
    }

    /** Status untuk pelanggan: pesanan Diproses ditampilkan sebagai "Sudah Dibayar". */
    public static String statusPelanggan(Pesanan p) {
        return p.getTahap() == Pesanan.Tahap.DIPROSES ? "Sudah Dibayar" : p.getTahap().toString();
    }

    public static String metode(String metode) {
        return "E_WALLET".equals(metode) ? "E-Wallet" : "Transfer";
    }

    /** Pesan error di bawah input; null = kosongkan (tetap menyisakan tinggi baris). */
    public static void error(JLabel label, String pesan) {
        label.setText(pesan == null ? " " : pesan);
    }

    public static Window jendela(Component c) {
        return c instanceof Window w ? w : SwingUtilities.getWindowAncestor(c);
    }

    // ===================== gambar =====================

    /** Ikon PNG dari folder resources /view/ikon. */
    public static ImageIcon ikon(String nama) {
        return CACHE_IKON.computeIfAbsent(nama, n -> new ImageIcon(Gaya.class.getResource("/view/ikon/" + n + ".png")));
    }

    /** Foto produk ukuran w x h (dipotong rapi di tengah). Tanpa foto -> gambar "foto kosong". */
    public static ImageIcon foto(byte[] data, int w, int h) {
        BufferedImage asli = null;
        try {
            if (data != null) asli = ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException e) {
            asli = null;
        }
        if (asli == null) {
            Image kosong = ikon("foto_kosong").getImage();
            asli = new BufferedImage(160, 160, BufferedImage.TYPE_INT_ARGB);
            asli.getGraphics().drawImage(kosong, 0, 0, null);
        }
        double skala = Math.max((double) w / asli.getWidth(), (double) h / asli.getHeight());
        int sw = (int) Math.round(asli.getWidth() * skala);
        int sh = (int) Math.round(asli.getHeight() * skala);
        BufferedImage hasil = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = hasil.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawImage(asli, (w - sw) / 2, (h - sh) / 2, sw, sh, null);
        g.dispose();
        return new ImageIcon(hasil);
    }

    /** Baca file gambar dari komputer, perkecil (maks. 800 px), lalu ubah ke JPEG untuk disimpan. */
    public static byte[] siapkanGambar(File file) throws IOException {
        BufferedImage asli = ImageIO.read(file);
        if (asli == null) throw new IOException("File bukan gambar.");
        double skala = Math.min(1.0, 800.0 / Math.max(asli.getWidth(), asli.getHeight()));
        int w = (int) Math.round(asli.getWidth() * skala);
        int h = (int) Math.round(asli.getHeight() * skala);
        BufferedImage rgb = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = rgb.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(asli, 0, 0, w, h, null);
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(rgb, "jpg", out);
        return out.toByteArray();
    }

    // ===================== tabel =====================

    /** Gaya umum tabel: header, garis, warna pilihan, dan jarak teks. */
    public static void tabel(JTable t) {
        t.setShowVerticalLines(false);
        t.setGridColor(GARIS);
        t.setSelectionBackground(BIRU_MUDA);
        t.setSelectionForeground(TEKS);
        t.setFillsViewportHeight(true);
        t.getTableHeader().setReorderingAllowed(false);
        JTableHeader header = t.getTableHeader();
        header.setPreferredSize(new java.awt.Dimension(0, 40));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tb, Object v, boolean pilih, boolean fokus, int r, int c) {
                super.getTableCellRendererComponent(tb, v, false, false, r, c);
                setBackground(LATAR);
                setForeground(REDUP);
                setFont(tb.getFont().deriveFont(Font.BOLD, 12f));
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, GARIS), BorderFactory.createEmptyBorder(0, 12, 0, 8)));
                return this;
            }
        });
        t.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tb, Object v, boolean pilih, boolean fokus, int r, int c) {
                super.getTableCellRendererComponent(tb, v, pilih, false, r, c);
                setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 8));
                return this;
            }
        });
    }

    public static void lebarKolom(JTable t, int... lebar) {
        for (int i = 0; i < lebar.length && i < t.getColumnCount(); i++) {
            if (lebar[i] > 0) {
                t.getColumnModel().getColumn(i).setPreferredWidth(lebar[i]);
                t.getColumnModel().getColumn(i).setMinWidth(Math.min(lebar[i], 90));
            }
        }
    }

    /** Kolom berisi foto (nilai sel = Icon). */
    public static void kolomFoto(JTable t, int kolom) {
        t.getColumnModel().getColumn(kolom).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tb, Object v, boolean pilih, boolean fokus, int r, int c) {
                super.getTableCellRendererComponent(tb, "", pilih, false, r, c);
                setIcon(v instanceof Icon i ? i : null);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
    }

    /** Warna badge {latar, teks} untuk setiap status pesanan. */
    public static Color[] warnaStatus(String status) {
        return switch (status) {
            case "Menunggu Pembayaran" -> new Color[]{new Color(254, 243, 199), new Color(180, 83, 9)};
            case "Diproses", "Sudah Dibayar" -> new Color[]{BIRU_MUDA, BIRU};
            case "Dikirim" -> new Color[]{new Color(224, 242, 254), new Color(3, 105, 161)};
            case "Selesai" -> new Color[]{new Color(231, 246, 239), HIJAU};
            default -> new Color[]{LATAR, REDUP};
        };
    }

    /** Label status berbentuk badge (dipakai di luar tabel). */
    public static void badge(JLabel label, String status) {
        Color[] w = warnaStatus(status);
        label.setText(status);
        label.setOpaque(true);
        label.setBackground(w[0]);
        label.setForeground(w[1]);
    }

    /** Kolom status ditampilkan sebagai badge berwarna. */
    public static void kolomStatus(JTable t, int kolom) {
        t.getColumnModel().getColumn(kolom).setCellRenderer(new BadgeRenderer());
    }

    /** Renderer badge status (Menunggu Pembayaran, Sudah Dibayar/Diproses, Dikirim, Selesai). */
    static class BadgeRenderer extends DefaultTableCellRenderer {
        private Color latar = LATAR;

        @Override
        public Component getTableCellRendererComponent(JTable tb, Object v, boolean pilih, boolean fokus, int r, int c) {
            super.getTableCellRendererComponent(tb, v, pilih, false, r, c);
            Color[] w = warnaStatus(String.valueOf(v));
            latar = w[0];
            setForeground(w[1]);
            setFont(tb.getFont().deriveFont(Font.BOLD, 12f));
            setHorizontalAlignment(SwingConstants.CENTER);
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int lebar = Math.min(getWidth() - 16, getFontMetrics(getFont()).stringWidth(getText()) + 24);
            int tinggi = 24;
            g2.setColor(latar);
            g2.fillRoundRect((getWidth() - lebar) / 2, (getHeight() - tinggi) / 2, lebar, tinggi, tinggi, tinggi);
            g2.dispose();
            setOpaque(false);
            super.paintComponent(g);
            setOpaque(true);
        }
    }
}
