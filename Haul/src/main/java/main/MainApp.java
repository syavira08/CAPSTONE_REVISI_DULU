package main;

import javax.swing.SwingUtilities;
import view.Gaya;
import view.LoginView;

/** Titik awal aplikasi HAULECTRA. */
public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gaya.pasangTema();
            new LoginView().setVisible(true);
        });
    }
}
