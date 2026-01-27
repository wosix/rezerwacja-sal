package org.example.gui;

import org.example.model.Account;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

public class DashboardPanel extends JPanel {

    private final Account account;

    private JButton browseButton;
    private JButton reservationsButton;

    public DashboardPanel(Account currentUser) {
        this.account = currentUser;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel northWrapper = CommonGUI.createTitleWrapper("Witaj w Systemie rezerwacji sal!");
        add(northWrapper, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        createBrowseButton();
        createMyReservationsButton();

        centerPanel.add(browseButton);
        centerPanel.add(reservationsButton);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);

    }

    private void createBrowseButton() {
        browseButton = createMenuButton("Przeglądaj sale");
        browseButton.addActionListener(e ->
                MainFrame.getInstance().showBrowse());
    }

    private void createMyReservationsButton() {
        reservationsButton = createMenuButton("Moje rezerwacje");
        reservationsButton.addActionListener(e ->
                MainFrame.getInstance().showReservations());
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