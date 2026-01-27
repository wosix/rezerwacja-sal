package org.example.gui.views.admin;

import org.example.gui.CommonGUI;
import org.example.gui.MainFrame;
import org.example.model.RoomSize;
import org.example.model.RoomType;
import org.example.model.dto.BoardroomDTO;
import org.example.service.BoardroomServiceImpl;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class CreateBoardroomPanel extends JPanel {

    private final BoardroomServiceImpl boardroomService;

    private JPanel formPanel;

    private JTextField roomNumberField;
    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> roomSizeComboBox;
    private JCheckBox availableCheckBox;

    private JCheckBox projectorCheckBox;
    private JCheckBox whiteboardCheckBox;
    private JCheckBox tvCheckBox;
    private JCheckBox videoConferenceCheckBox;
    private JCheckBox ventilationCheckBox;
    private JCheckBox soundSystemCheckBox;

    private JButton createButton;
    private JButton backButton;

    public CreateBoardroomPanel() {
        this.boardroomService = new BoardroomServiceImpl();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel northWrapper = CommonGUI.createTitleWrapper("Dodanie nowej sali");
        add(northWrapper, BorderLayout.NORTH);

        createFormPanel();

        createCreateButton();
        createBackButton();

        JPanel southWrapper = CommonGUI.createActionButtonWrapper(
                createButton,
                backButton
        );

        add(formPanel, BorderLayout.CENTER);
        add(southWrapper, BorderLayout.SOUTH);
    }

    private void createFormPanel() {
        formPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicjalizuj komponenty
        initFormComponents();

        // Lewa kolumna - podstawowe dane
        JPanel leftPanel = createLeftPanel();

        // Prawa kolumna - wyposażenie
        JPanel rightPanel = createRightPanel();

        formPanel.add(leftPanel);
        formPanel.add(rightPanel);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Podstawowe informacje",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13),
                Color.DARK_GRAY
        ));

        // Dodaj pola z etykietami
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(createLabeledField("Numer sali:", roomNumberField));
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(createLabeledField("Typ sali:", roomTypeComboBox));
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(createLabeledField("Pojemność:", roomSizeComboBox));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(availableCheckBox);
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setMaximumSize(new Dimension(400, 35));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setPreferredSize(new Dimension(100, 25));

        field.setPreferredSize(new Dimension(200, 30));

        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Wyposażenie sali",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13),
                Color.DARK_GRAY
        ));

        rightPanel.add(Box.createVerticalStrut(10));

        // Checkboxy w jednej kolumnie
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        checkBoxPanel.add(projectorCheckBox);
        checkBoxPanel.add(Box.createVerticalStrut(8));
        checkBoxPanel.add(whiteboardCheckBox);
        checkBoxPanel.add(Box.createVerticalStrut(8));
        checkBoxPanel.add(tvCheckBox);
        checkBoxPanel.add(Box.createVerticalStrut(8));
        checkBoxPanel.add(videoConferenceCheckBox);
        checkBoxPanel.add(Box.createVerticalStrut(8));
        checkBoxPanel.add(ventilationCheckBox);
        checkBoxPanel.add(Box.createVerticalStrut(8));
        checkBoxPanel.add(soundSystemCheckBox);

        rightPanel.add(checkBoxPanel);
        rightPanel.add(Box.createVerticalGlue());

        return rightPanel;
    }

    private void initFormComponents() {
        roomNumberField = new JTextField(15);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] roomTypes = {
                "Konferencyjna",
                "Szkoleniowa",
                "Warsztatowa",
                "Prezentacyjna",
                "Spotkań biznesowych",
                "Wideokonferencyjna"
        };
        roomTypeComboBox = new JComboBox<>(roomTypes);
        roomTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] roomSizes = {
                "Mała (do 10 osób)",
                "Średnia (10-30 osób)",
                "Duża (30-50 osób)",
                "Bardzo duża (50+ osób)",
                "Audytorium"
        };
        roomSizeComboBox = new JComboBox<>(roomSizes);
        roomSizeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        availableCheckBox = new JCheckBox("Dostępna od razu");
        availableCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        availableCheckBox.setSelected(true);

        // Prawa kolumna
        projectorCheckBox = new JCheckBox("Projektor multimedialny");
        whiteboardCheckBox = new JCheckBox("Tablica suchościeralna");
        tvCheckBox = new JCheckBox("Telewizor");
        videoConferenceCheckBox = new JCheckBox("System wideokonferencji");
        ventilationCheckBox = new JCheckBox("Klimatyzacja/wentylacja");
        soundSystemCheckBox = new JCheckBox("System nagłośnienia");

        // Ustaw czcionki dla checkboxów
        Font checkBoxFont = new Font("Arial", Font.PLAIN, 13);
        projectorCheckBox.setFont(checkBoxFont);
        whiteboardCheckBox.setFont(checkBoxFont);
        tvCheckBox.setFont(checkBoxFont);
        videoConferenceCheckBox.setFont(checkBoxFont);
        ventilationCheckBox.setFont(checkBoxFont);
        soundSystemCheckBox.setFont(checkBoxFont);
    }


    private void createCreateButton() {
        createButton = CommonGUI.createButton(180, 40,
                "Dodaj sale");
        createButton.addActionListener(e -> {
//            MainFrame.getInstance().showLogin();
        });
    }

    private void createBackButton() {
        backButton = CommonGUI.createButton(180, 40,
                "Wróc do logowania");
        backButton.addActionListener(e -> {
            MainFrame.getInstance().showDashboard(MainFrame.getInstance().getCurrentUser());
        });
    }

    private BoardroomDTO createBoardroomDTO() {
        BoardroomDTO dto = new BoardroomDTO();

        dto.setNumber(roomNumberField.getText().trim());
        dto.setRoomType(RoomType.valueOf((String) roomTypeComboBox.getSelectedItem()));
        dto.setRoomSize(RoomSize.valueOf((String) roomSizeComboBox.getSelectedItem()));
        dto.setAvailable(availableCheckBox.isSelected());

        dto.setProjector(projectorCheckBox.isSelected());
        dto.setWhiteboard(whiteboardCheckBox.isSelected());
        dto.setTvScreen(tvCheckBox.isSelected());
        dto.setVideoConferenceSystem(videoConferenceCheckBox.isSelected());
        dto.setAirConditioning(ventilationCheckBox.isSelected());
        dto.setSoundSystem(soundSystemCheckBox.isSelected());

        return dto;
    }

    private void clearForm() {
        roomNumberField.setText("");
        roomTypeComboBox.setSelectedIndex(0);
        roomSizeComboBox.setSelectedIndex(0);
        availableCheckBox.setSelected(true);

        projectorCheckBox.setSelected(false);
        whiteboardCheckBox.setSelected(false);
        tvCheckBox.setSelected(false);
        videoConferenceCheckBox.setSelected(false);
        ventilationCheckBox.setSelected(false);
        soundSystemCheckBox.setSelected(false);

        roomNumberField.requestFocus();
    }

}