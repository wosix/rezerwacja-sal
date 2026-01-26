import gui.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.setVisible(true);
            mainFrame.showLogin();
        });
    }
}