package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class BookingPanel extends JPanel {

    public BookingPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Rezerwacja sali");
        label.setFont(new Font("Arial", Font.BOLD, 28));

        topPanel.add(label);

        add(topPanel);

    }
}
