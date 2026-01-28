package org.example.gui.views.booking;

import org.example.exception.ValidationException;
import org.example.gui.CommonGUI;
import org.example.gui.MainFrame;
import org.example.model.Boardroom;
import org.example.service.ReservationServiceImpl;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePanel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDateTime;

public class BookingPanel extends JPanel {

    private final ReservationServiceImpl reservationService;

    private Boardroom boardroom;
    private JDatePanel datePanel;
    private JPanel timePanel;

    private LocalDateTime selectedDateTime;

    private JButton reserveButton;
    private JButton backButton;

    public BookingPanel() {
        this.reservationService = new ReservationServiceImpl();
        initNoBoardroom();
    }

    private void initNoBoardroom() {
        setLayout(new BorderLayout());
        JPanel emptyPanel = CommonGUI.createTitleWrapper("Nie wybrano sali");
        add(emptyPanel, BorderLayout.CENTER);
    }

    public void setBoardroom(Boardroom boardroom) {
        this.boardroom = boardroom;
        removeAll();
        initComponents();
        repaint();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel label = CommonGUI.createTitleLabel("Rezerwacja sali " + boardroom.getNumber());
        JPanel northWrapper = CommonGUI.createWrapper(10, 0, label);
        add(northWrapper, BorderLayout.NORTH);

        createDatePanel();
        createTimelinePanel();

//        JScrollPane scrollPane = new JScrollPane(timePanel);
//        scrollPane.setVerticalScrollBar(new JScrollBar());
//        scrollPane.setHorizontalScrollBar(null);

        JPanel eastWrapper = CommonGUI.createWrapper(0, 25, 0, 25,
                timePanel);

        add(datePanel, BorderLayout.CENTER);

//        add(scrollPane, BorderLayout.EAST);
        add(eastWrapper, BorderLayout.EAST);

        createActionButtons();


        JPanel southWrapper = CommonGUI.createSouthWrapper(10,
                reserveButton,
                backButton
        );

        add(southWrapper, BorderLayout.SOUTH);

    }

    private void createActionButtons() {
        reserveButton = CommonGUI.createButton(180, 40,
                "Rezerwuj");
        backButton = CommonGUI.createButton(180, 40,
                "Powrót");

        reserveButton.addActionListener(e -> {
            handleReserveBoardroom();
        });

        backButton.addActionListener(e -> {
            MainFrame.getInstance().showBrowse();
        });

    }

    private void handleReserveBoardroom() {
        try {
            reservationService.makeReservation(
                    MainFrame.getInstance().getCurrentUser().getId(),
                    boardroom,
                    selectedDateTime
            );

            JOptionPane.showMessageDialog(this,
                    "Rezerwacja zakończona sukcesem!",
                    "Sukces!",
                    JOptionPane.INFORMATION_MESSAGE);

            refreshTimePanel();

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd przy rezerwacji" + ex.getMessage(),
                    "Błąd rezerwacji",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDateTime getSelectedDate() {
        DateModel<?> model = datePanel.getModel();
        if (datePanel.getModel().getValue() == null) {
            return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        }

        return LocalDateTime.of(
                model.getYear(),
                model.getMonth() + 1,
                model.getDay(),
                0, 0, 0
        );
    }

    private void createDatePanel() {
        datePanel = new JDatePanel();
        datePanel.setMaximumSize(new Dimension());

        LocalDateTime today = LocalDateTime.now();
        datePanel.getModel().setDate(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());

        datePanel.getModel().addPropertyChangeListener(evt -> {
            if ("value".equals(evt.getPropertyName())) {
                DateModel<?> model = datePanel.getModel();
                if (model.getValue() != null) {
                    refreshTimePanel();
                }
            }
        });
    }

    public void createTimelinePanel() {
        timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
        timePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        refreshTimePanel();
    }

    private void refreshTimePanel() {
        timePanel.removeAll();
        LocalDateTime selectedDate = getSelectedDate();
        for (int hour = 7; hour <= 20; hour++) {
            JButton hourButton = CommonGUI.createButton(180, 40,
                    String.format("%02d:00", hour));
            hourButton.putClientProperty("hour", hour);

            boolean isBooked = reservationService.isHourBooked(boardroom.getId(), selectedDate, hour);

            if (isBooked) {
                hourButton.setBackground(new Color(255, 182, 193));
                hourButton.setEnabled(false);
                hourButton.setToolTipText("Godzina zajęta");
            } else {
                hourButton.setBackground(new Color(144, 238, 144));
                hourButton.setEnabled(true);
                hourButton.setToolTipText("Kliknij aby zarezerwować");

                hourButton.addActionListener(e -> {
                    int selectedHour = (int) hourButton.getClientProperty("hour");
                    onHourSelected(selectedHour);
//                    this.selectedDateTime = selectedHour;
                    hourButton.setBackground(new Color(33, 133, 200));
                    System.out.println("Wybrano godzinę: " + selectedHour + " dla daty: " + selectedDate);
                });
            }

            timePanel.add(hourButton);
            if (hour < 20) {
                timePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            }
        }

        timePanel.revalidate();
        timePanel.repaint();
    }

    private void onHourSelected(int selectedHour) {
        this.selectedDateTime = getSelectedDate().withHour(selectedHour);
    }


}
