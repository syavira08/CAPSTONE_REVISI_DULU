package main;

import javax.swing.SwingUtilities;
import view.LoginView;
import view.UI;

/** Titik awal aplikasi HAULECTRA. */
public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI.pasangTema();
            new LoginView().setVisible(true);
        });
    }
}
