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

        setLayout(new BorderLayout(10, 10));

        JPanel northWrapper = CommonGUI.createTitleWrapper("Rejestracja do Systemu");
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

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Powtórz hasło:"), gbc);

        gbc.gridx = 1;
        passwordRepeatField = new JPasswordField(15);
        formPanel.add(passwordRepeatField, gbc);

        passwordField.addActionListener(e -> loginButton.doClick());

        createRegisterButton();
        createGoToLoginButton();


        JPanel southWrapper = CommonGUI.createActionButtonWrapper(
                registerButton,
                loginButton
        );

        add(formPanel, BorderLayout.CENTER);
        add(southWrapper, BorderLayout.SOUTH);
    }

    private void createRegisterButton() {
        registerButton = CommonGUI.createButton(180, 40,
                "Zarejestruj się");
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String passwordRepeat = new String(passwordRepeatField.getPassword());

            if (authenticate(username, password, passwordRepeat)) {
                MainFrame.getInstance().showDashboard();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Błędne powtórzenie hasła!",
                        "Błąd rejestracji",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void createGoToLoginButton() {
        loginButton = CommonGUI.createButton(180, 40,
                "Wróc do logowania");
        loginButton.addActionListener(e -> {
            MainFrame.getInstance().showLogin();
        });
    }

    private boolean authenticate(String username, String password, String passwordRepeat) {
        // Prosta autoryzacja
        return password.equals(passwordRepeat);
    }

    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }

}
