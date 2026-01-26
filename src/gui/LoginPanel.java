package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton toRegisterButton;

    public LoginPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel northWrapper = CommonGUI.createTitleWrapper("Logowanie do Systemu");
        add(northWrapper, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Hasło:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        passwordField.addActionListener(e -> loginButton.doClick());

        createLoginButton();
        createToRegisterButton();

        JPanel southWrapper = CommonGUI.createActionButtonWrapper(
                loginButton,
                toRegisterButton
        );

        add(formPanel, BorderLayout.CENTER);
        add(southWrapper, BorderLayout.SOUTH);
    }

    private void createLoginButton() {
        loginButton = CommonGUI.createButton(180, 40,
                "Zaloguj się");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                MainFrame.getInstance().showDashboard();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Błędny login lub hasło!",
                        "Błąd logowania",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void createToRegisterButton() {
        toRegisterButton = CommonGUI.createButton(180, 40,
                "Przejdz do rejestracji");
        toRegisterButton.addActionListener(e -> {
            MainFrame.getInstance().showRegister();
        });
    }

    private boolean authenticate(String username, String password) {
        // Prosta autoryzacja
        return username.equals("admin") && password.equals("admin123") ||
                username.equals("student") && password.equals("student123") ||
                username.equals("a") && password.equals("a");
    }

    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }
    
}