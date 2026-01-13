package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Tytuł
        JLabel titleLabel = new JLabel("Logowanie do Systemu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Hasło:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Przycisk logowania
        gbc.gridwidth = 2;
        gbc.gridy = 3;
        gbc.gridx = 0;
        loginButton = new JButton("Zaloguj się");
        loginButton.setPreferredSize(new Dimension(150, 40));
        add(loginButton, gbc);

        // Listener dla przycisku
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                // Przejdź do dashboard
                MainFrame.getInstance().showDashboard();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Błędny login lub hasło!",
                        "Błąd logowania",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Przycisk rejstracji
        gbc.gridwidth = 2;
        gbc.gridy = 5;
        gbc.gridx = 0;
        registerButton = new JButton("Przejdz do rejestracji");
        registerButton.setPreferredSize(new Dimension(150, 40));
        add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            MainFrame.getInstance().showRegister();
        });

        // Enter też loguje
        passwordField.addActionListener(e -> loginButton.doClick());
    }

    private boolean authenticate(String username, String password) {
        // Prosta autoryzacja dla demonstracji
        // W prawdziwym projekcie sprawdzaj z bazą danych
        return username.equals("admin") && password.equals("admin123") ||
                username.equals("student") && password.equals("student123") ||
                username.equals("a") && password.equals("a");
    }

    // Resetuj pola (przy wylogowaniu)
    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }
}