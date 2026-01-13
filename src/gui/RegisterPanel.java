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

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordRepeatField;
    private JButton registerButton;
    private JButton loginButton;

    public RegisterPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Tytuł
        JLabel titleLabel = new JLabel("Rejestracja do Systemu");
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

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Powtórz hasło:"), gbc);

        gbc.gridx = 1;
        passwordRepeatField = new JPasswordField(15);
        add(passwordRepeatField, gbc);

        // Przycisk rejstracji
        gbc.gridwidth = 2;
        gbc.gridy = 4;
        gbc.gridx = 0;
        registerButton = new JButton("Zarejestruj się");
        registerButton.setPreferredSize(new Dimension(150, 40));
        add(registerButton, gbc);

        // Listener dla przycisku
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String passwordRepeat = new String(passwordRepeatField.getPassword());

            if (authenticate(username, password, passwordRepeat)) {
                // Przejdź do dashboard
                MainFrame.getInstance().showDashboard();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Błędne powtórzenie hasła!",
                        "Błąd rejestracji",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Przycisk logowania
        gbc.gridwidth = 2;
        gbc.gridy = 5;
        gbc.gridx = 0;
        loginButton = new JButton("Przejdź do logowania");
        loginButton.setPreferredSize(new Dimension(150, 40));
        add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            MainFrame.getInstance().showLogin();
        });

        // Enter też loguje
        passwordField.addActionListener(e -> loginButton.doClick());
    }

    private boolean authenticate(String username, String password, String passwordRepeat) {
        // Prosta autoryzacja dla demonstracji
        // W prawdziwym projekcie sprawdzaj z bazą danych
        return password.equals(passwordRepeat);
    }

    // Resetuj pola (przy wylogowaniu)
    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }

}
