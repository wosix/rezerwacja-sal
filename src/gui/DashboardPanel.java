package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

public class DashboardPanel extends JPanel {
    private JLabel welcomeLabel;
    private JButton browseButton;
    private JButton reservationsButton;

    public DashboardPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        welcomeLabel = new JLabel("Witaj w Systemie rezerwacji sal!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);

        // Panel centralny z przyciskami
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        browseButton = createMenuButton("Przeglądaj sale");
        reservationsButton = createMenuButton("Moje rezerwacje");

        centerPanel.add(browseButton);
        centerPanel.add(reservationsButton);

        add(centerPanel, BorderLayout.CENTER);

        // Panel dolny
        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);

        browseButton.addActionListener(e ->
                MainFrame.getInstance().showBrowse());

        reservationsButton.addActionListener(e ->
                MainFrame.getInstance().showReservastions());

    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

}