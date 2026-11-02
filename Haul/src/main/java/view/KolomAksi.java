package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Helper tabel: kolom "Aksi" berisi tombol per baris (Ubah, Hapus, Lacak, ...).
 * Hanya renderer + editor sel; tampilan tabelnya sendiri dibuat di Form Builder.
 */
public class KolomAksi extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    /** Aksi yang dijalankan saat tombol diklik; baris = indeks baris pada tabel. */
    public interface Aksi { void jalankan(int baris); }

    /** Definisi satu tombol: teks & jenis (boleh berbeda tiap baris), kapan tampil, dan aksinya. */
    public static class Tombol {
        final Function<Integer, String> teks;
        final Function<Integer, UI.Jenis> jenis;
        final IntPredicate tampil;
        final Aksi aksi;

        public Tombol(String teks, UI.Jenis jenis, IntPredicate tampil, Aksi aksi) {
            this(b -> teks, b -> jenis, tampil, aksi);
        }

        public Tombol(Function<Integer, String> teks, Function<Integer, UI.Jenis> jenis, IntPredicate tampil, Aksi aksi) {
            this.teks = teks;
            this.jenis = jenis;
            this.tampil = tampil;
            this.aksi = aksi;
        }
    }

    private final List<Tombol> daftar = new ArrayList<>();

    public KolomAksi(JTable tabel, int kolom, Tombol... tombol) {
        for (Tombol t : tombol) daftar.add(t);
        tabel.getColumnModel().getColumn(kolom).setCellRenderer(this);
        tabel.getColumnModel().getColumn(kolom).setCellEditor(this);
    }

    private JPanel bangun(int baris, boolean aktif) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 7));
        p.setOpaque(true);
        boolean ada = false;
        for (Tombol t : daftar) {
            if (!t.tampil.test(baris)) continue;
            ada = true;
            JButton b = new JButton(t.teks.apply(baris));
            UI.tombol(b, t.jenis.apply(baris), true);
            if (aktif) {
                b.addActionListener(e -> {
                    fireEditingStopped();
                    SwingUtilities.invokeLater(() -> t.aksi.jalankan(baris));
                });
            }
            p.add(b);
        }
        if (!ada) {
            JLabel strip = new JLabel("\u2014");
            strip.setFont(UI.KECIL);
            strip.setForeground(UI.PUDAR);
            strip.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 6, 0, 0));
            p.add(strip);
        }
        return p;
    }

    @Override
    public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
        JPanel p = bangun(r, false);
        p.setBackground(s ? UI.BIRU_MUDA : r % 2 == 0 ? UI.PUTIH : UI.BARIS_GANJIL);
        return p;
    }

    @Override
    public Component getTableCellEditorComponent(JTable t, Object v, boolean s, int r, int c) {
        JPanel p = bangun(r, true);
        p.setBackground(UI.BIRU_MUDA);
        return p;
    }

    @Override
    public Object getCellEditorValue() { return ""; }
}
