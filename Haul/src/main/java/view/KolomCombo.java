package view;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Helper tabel: kolom berisi dropdown (mis. "Ubah status", "Status pengiriman").
 * Nilai terpilih disimpan di model tabel sehingga tombol Simpan pada baris yang sama bisa membacanya.
 */
public class KolomCombo {

    private KolomCombo() { }

    public static void pasang(JTable tabel, int kolom, String... pilihan) {
        JComboBox<String> editor = new JComboBox<>(pilihan);
        UI.combo(editor);
        DefaultCellEditor de = new DefaultCellEditor(editor);
        de.setClickCountToStart(1);
        tabel.getColumnModel().getColumn(kolom).setCellEditor(de);

        tabel.getColumnModel().getColumn(kolom).setCellRenderer(new TableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                JComboBox<String> cb = new JComboBox<>(pilihan);
                UI.combo(cb);
                cb.setSelectedItem(v);
                cb.setPreferredSize(new java.awt.Dimension(150, 32));
                JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
                p.setBackground(s ? UI.BIRU_MUDA : r % 2 == 0 ? UI.PUTIH : UI.BARIS_GANJIL);
                p.add(cb);
                return p;
            }
        });
    }
}
