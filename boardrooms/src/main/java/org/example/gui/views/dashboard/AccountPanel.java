package org.example.gui.views.dashboard;

import org.example.gui.CommonGUI;
import org.example.gui.views.MainFrame;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;

public class AccountPanel extends JPanel {

    private CardLayout cardLayout;
    private JLabel titleLabel;
    private JPanel contentPanel;

    private JPanel menuPanel;
    private JPanel changeDataPanel;
    private JPanel changePasswordPanel;
    private JPanel settingsPanel;

    public AccountPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        titleLabel = CommonGUI.createTitleLabel("Moje konto");
        JPanel northWrapper = CommonGUI.createNorthWrapper(25, titleLabel);
        add(northWrapper, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        createAllPanels();
        contentPanel.add(menuPanel, "MENU");
        contentPanel.add(changeDataPanel, "CHANGE_DATA");
        contentPanel.add(changePasswordPanel, "CHANGE_PASSWORD");
        contentPanel.add(settingsPanel, "SETTINGS");
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "MENU");
    }

    private void createAllPanels() {
        menuPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton changeDetailsBtn = createMenuButton("Zmień dane konta");
        JButton changePasswordBtn = createMenuButton("Zmień hasło");
        JButton myReservationsBtn = createMenuButton("Moje rezerwacje");
        JButton settingsBtn = createMenuButton("Ustawienia aplikacji");

        changeDetailsBtn.addActionListener(e -> showAccountForm());

        changePasswordBtn.addActionListener(e -> showChangePassword());

        myReservationsBtn.addActionListener(e ->
                MainFrame.getInstance().showReservations());

        settingsBtn.addActionListener(e -> showSettings());

        menuPanel.add(changeDetailsBtn);
        menuPanel.add(changePasswordBtn);
        menuPanel.add(myReservationsBtn);
        menuPanel.add(settingsBtn);

        changeDataPanel = createAccountFormPanel();
        changePasswordPanel = createPasswordFormPanel();
        settingsPanel = createSettingsPanel();
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

    private JPanel createAccountFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Login:"), gbc);

        JTextField loginField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(loginField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Email:"), gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton saveButton = new JButton("Zapisz zmiany");
        JButton cancelButton = new JButton("Anuluj");
        JButton backButton = createBackButton();

        saveButton.addActionListener(e -> {
            String login = loginField.getText();
            String email = emailField.getText();

            JOptionPane.showMessageDialog(this,
                    "Zapisano dane!\nLogin: " + login + "\nEmail: " + email);
        });

        cancelButton.addActionListener(e -> {
            loginField.setText("");
            emailField.setText("");
        });

        backButton.addActionListener(e -> {
            loginField.setText("");
            emailField.setText("");
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);

        JPanel southWrapper = CommonGUI.createActionButtonWrapper(saveButton, cancelButton, backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(southWrapper, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPasswordFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Hasło:"), gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Powtórz hasło:"), gbc);

        JPasswordField passwordRepeatField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passwordRepeatField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton saveButton = new JButton("Zapisz nowe hasło");
        JButton cancelButton = new JButton("Anuluj");
        JButton backButton = createBackButton();

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

        backButton.addActionListener(e -> {
            passwordField.setText("");
            passwordRepeatField.setText("");
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);

        JPanel southWrapper = CommonGUI.createActionButtonWrapper(buttonPanel);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(southWrapper, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Motyw:"), gbc);

        JPanel themePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JRadioButton themeLight = new JRadioButton("Jasny");
        JRadioButton themeDark = new JRadioButton("Ciemny");
        JRadioButton themeAuto = new JRadioButton("Automatyczny (system)");

        ButtonGroup themeGroup = new ButtonGroup();
        themeGroup.add(themeLight);
        themeGroup.add(themeDark);
        themeGroup.add(themeAuto);
        themeAuto.setSelected(true);

        themePanel.add(themeLight);
        themePanel.add(themeDark);
        themePanel.add(themeAuto);

        gbc.gridx = 1;
        formPanel.add(themePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Język:"), gbc);

        JPanel languagePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JRadioButton langPolish = new JRadioButton("Polski");
        JRadioButton langEnglish = new JRadioButton("Angielski");
        JRadioButton langGerman = new JRadioButton("Niemiecki");

        ButtonGroup langGroup = new ButtonGroup();
        langGroup.add(langPolish);
        langGroup.add(langEnglish);
        langGroup.add(langGerman);
        langPolish.setSelected(true);

        languagePanel.add(langPolish);
        languagePanel.add(langEnglish);
        languagePanel.add(langGerman);

        gbc.gridx = 1;
        formPanel.add(languagePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Powiadomienia:"), gbc);

        JCheckBox notifyEmail = new JCheckBox("E-mail", true);
        JCheckBox notifySMS = new JCheckBox("SMS", false);
        JCheckBox notifyPush = new JCheckBox("Push", true);

        JPanel notifyPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        notifyPanel.add(notifyEmail);
        notifyPanel.add(notifySMS);
        notifyPanel.add(notifyPush);

        gbc.gridx = 1;
        formPanel.add(notifyPanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Zapisz ustawienia");
        JButton backButton = createBackButton();

        saveButton.addActionListener(e -> {
            String theme = themeLight.isSelected() ? "light" :
                    themeDark.isSelected() ? "dark" : "auto";

            String language = langPolish.isSelected() ? "pl" :
                    langEnglish.isSelected() ? "en" : "de";

            boolean emailNotify = notifyEmail.isSelected();
            boolean smsNotify = notifySMS.isSelected();
            boolean pushNotify = notifyPush.isSelected();

            JOptionPane.showMessageDialog(panel,
                    "Ustawienia zapisane!\n" +
                            "Motyw: " + theme + "\n" +
                            "Język: " + language + "\n" +
                            "Powiadomienia email: " + (emailNotify ? "TAK" : "NIE") + "\n" +
                            "Powiadomienia SMS: " + (smsNotify ? "TAK" : "NIE") + "\n" +
                            "Powiadomienia Push: " + (pushNotify ? "TAK" : "NIE"),
                    "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        JPanel southWrapper = CommonGUI.createActionButtonWrapper(buttonPanel);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(southWrapper, BorderLayout.SOUTH);

        return panel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Powtót");
        backButton.addActionListener(e -> showMenu());
        return backButton;
    }

    private void showMenu() {
        cardLayout.show(contentPanel, "MENU");
        titleLabel.setText("Moje konto");
    }

    private void showAccountForm() {
        cardLayout.show(contentPanel, "CHANGE_DATA");
        titleLabel.setText("Zmiana danych konta");
    }

    private void showChangePassword() {
        cardLayout.show(contentPanel, "CHANGE_PASSWORD");
        titleLabel.setText("Zmiana hasła");
    }

    private void showSettings() {
        cardLayout.show(contentPanel, "SETTINGS");
        titleLabel.setText("Ustawienia");
    }

}
