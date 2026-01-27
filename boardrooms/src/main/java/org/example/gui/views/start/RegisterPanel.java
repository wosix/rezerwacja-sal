package org.example.gui.views.start;

import org.example.exception.RegisterException;
import org.example.gui.CommonGUI;
import org.example.gui.MainFrame;
import org.example.model.Role;
import org.example.model.dto.AccountDTO;
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

public class RegisterPanel extends JPanel {

    private final AccountServiceImpl accountService;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField passwordRepeatField;

    private JButton registerButton;
    private JButton loginButton;

    public RegisterPanel() {
        this.accountService = new AccountServiceImpl();
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
        formPanel.add(new JLabel("Imie:"), gbc);

        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        formPanel.add(firstNameField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Nazwisko:"), gbc);

        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        formPanel.add(lastNameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        formPanel.add(emailField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Hasło:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        gbc.gridy = 4;
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
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String repeatPassword = new String(passwordRepeatField.getPassword());

            try {
                AccountDTO accountDTO = createDTOFromFields(
                        firstName,
                        lastName,
                        email,
                        password,
                        repeatPassword
                );
                accountService.create(accountDTO);

                JOptionPane.showMessageDialog(this,
                        "Rejestracja zakończona sukcesem!",
                        "Sukces!",
                        JOptionPane.INFORMATION_MESSAGE);

                resetFields();
                MainFrame.getInstance().showLogin();

            } catch (RegisterException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Błąd rejestracji",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Wystąpił niespodziewany błąd: " + ex.getMessage(),
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

    public void resetFields() {
        emailField.setText("");
        passwordField.setText("");
        emailField.requestFocus();
    }

    private AccountDTO createDTOFromFields(
            String firstName,
            String lastName,
            String email,
            String password,
            String repeatPassword
    ) {
        return new AccountDTO(
                firstName,
                lastName,
                email,
                password,
                repeatPassword,
                Role.USER
        );
    }

}
