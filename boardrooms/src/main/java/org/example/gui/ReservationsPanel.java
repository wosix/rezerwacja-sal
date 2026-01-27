package org.example.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class ReservationsPanel extends JPanel {

    public ReservationsPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Aktualne oraz historyczne rezerwacje sal");
        label.setFont(new Font("Arial", Font.BOLD, 28));

        topPanel.add(label);

        add(topPanel);

    }

}
