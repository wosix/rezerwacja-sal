package org.example.gui.views.start;

import org.example.exception.LoginException;
import org.example.gui.CommonGUI;
import org.example.gui.views.MainFrame;
import org.example.service.AccountServiceImpl;

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

    private final AccountServiceImpl accountService;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton toRegisterButton;

    public LoginPanel() {
        this.accountService = new AccountServiceImpl();
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
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        formPanel.add(emailField, gbc);

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
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                MainFrame.getInstance().handleLogin(email, password);

//                JOptionPane.showMessageDialog(this,
//                        "Udane logowanie!",
//                        "Sukces",
//                        JOptionPane.PLAIN_MESSAGE);

                resetFields();

            } catch (LoginException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
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

    public void resetFields() {
        emailField.setText("");
        passwordField.setText("");
        emailField.requestFocus();
    }

}