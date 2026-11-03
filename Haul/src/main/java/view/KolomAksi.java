package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.EventObject;
import java.util.Set;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Utilitas tabel (renderer + editor): kolom "Aksi" berisi tombol yang bisa berbeda per baris,
 * mis. Bayar + Batalkan, Lacak, atau Lihat.
 */
public class KolomAksi extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    /** Tombol apa saja yang muncul pada baris tertentu. */
    public interface Tombol {
        String[] untuk(int baris);
    }

    /** Dijalankan ketika tombol diklik. */
    public interface Aksi {
        void klik(int baris, String tombol);
    }

    private static final Set<String> MERAH = Set.of("Hapus", "Batalkan");
    private static final Set<String> BIRU = Set.of("Bayar", "Kirim", "Lacak");

    private final Tombol tombol;
    private final Aksi aksi;

    private KolomAksi(Tombol tombol, Aksi aksi) {
        this.tombol = tombol;
        this.aksi = aksi;
    }

    public static void pasang(JTable tabel, int kolom, Tombol tombol, Aksi aksi) {
        KolomAksi k = new KolomAksi(tombol, aksi);
        tabel.getColumnModel().getColumn(kolom).setCellRenderer(k);
        tabel.getColumnModel().getColumn(kolom).setCellEditor(k);
    }

    @Override
    public Component getTableCellRendererComponent(JTable t, Object v, boolean pilih, boolean fokus, int baris, int kolom) {
        return panel(t, baris, pilih, false);
    }

    @Override
    public Component getTableCellEditorComponent(JTable t, Object v, boolean pilih, int baris, int kolom) {
        return panel(t, baris, true, true);
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    private JPanel panel(JTable t, int baris, boolean pilih, boolean aktif) {
        int jarakAtas = Math.max(0, (t.getRowHeight() - 28) / 2);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, jarakAtas));
        p.setBackground(pilih ? t.getSelectionBackground() : t.getBackground());
        for (String label : tombol.untuk(baris)) {
            JButton b = new JButton(label);
            b.setFont(t.getFont().deriveFont(Font.BOLD, 12f));
            b.setFocusPainted(false);
            b.setPreferredSize(new java.awt.Dimension(Math.max(64, b.getFontMetrics(b.getFont()).stringWidth(label) + 26), 28));
            if (MERAH.contains(label)) {
                b.setBackground(new Color(253, 236, 236));
                b.setForeground(Gaya.MERAH);
                b.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            } else if (BIRU.contains(label)) {
                b.setBackground(Gaya.BIRU);
                b.setForeground(Color.WHITE);
                b.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            } else {
                b.setBackground(Color.WHITE);
                b.setForeground(Gaya.TEKS);
                b.setBorder(BorderFactory.createLineBorder(Gaya.GARIS));
            }
            if (aktif) {
                b.addActionListener(e -> {
                    fireEditingStopped();
                    SwingUtilities.invokeLater(() -> aksi.klik(baris, label));
                });
            }
            p.add(b);
        }
        return p;
    }
}
