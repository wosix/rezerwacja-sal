package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;

public class AccountPanel extends JPanel {

    public AccountPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Nagłówek
        JLabel titleLabel = new JLabel("Twoje konto", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel accountPanel = createAccountFormPanel();
        add(accountPanel, BorderLayout.WEST);

        JPanel passwordPanel = createPasswordFormPanel();
        add(passwordPanel, BorderLayout.EAST);

    }

    private JPanel createAccountFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Login
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Login:"), gbc);

        JTextField loginField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(loginField, gbc);

        // Email
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Email:"), gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 3;
        gbc.gridx = 0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton saveButton = new JButton("Zapisz zmiany");
        JButton cancelButton = new JButton("Anuluj");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);

        // Action listeners
        saveButton.addActionListener(e -> {
            String login = loginField.getText();
            String email = emailField.getText();

            // Tutaj logika zapisu
            JOptionPane.showMessageDialog(this,
                    "Zapisano dane!\nLogin: " + login + "\nEmail: " + email);
        });

        cancelButton.addActionListener(e -> {
            loginField.setText("");
            emailField.setText("");
        });

        return panel;
    }

    private JPanel createPasswordFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Hasło
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Hasło:"), gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Hasło
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Powtórz hasło:"), gbc);

        JPasswordField passwordRepeatField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordRepeatField, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 3;
        gbc.gridx = 0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton saveButton = new JButton("Zapisz nowe hasło");
        JButton cancelButton = new JButton("Anuluj");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);

        // Action listeners
        saveButton.addActionListener(e -> {
            String first = Arrays.toString(passwordField.getPassword());
            String second = Arrays.toString(passwordRepeatField.getPassword());

            if (first.equals(second)) {
                // Tutaj logika zapisu
                JOptionPane.showMessageDialog(this,
                        "Zapisano nowe hasło!");
            }
        });

        cancelButton.addActionListener(e -> {
            passwordField.setText("");
            passwordRepeatField.setText("");
        });

        return panel;
    }

}


