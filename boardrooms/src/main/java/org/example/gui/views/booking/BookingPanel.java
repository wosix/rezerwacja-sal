package org.example.gui.views.booking;

import org.example.gui.CommonGUI;
import org.example.model.Boardroom;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import static org.example.gui.CommonGUI.createBoldLabel;

public class BookingPanel extends JPanel {

    private Boardroom boardroom;

    public BookingPanel() {
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

        assert boardroom != null;
        JLabel label = CommonGUI.createTitleLabel("Rezerwacja sali " + boardroom.getId());
        JPanel northWrapper = CommonGUI.createWrapper(10, 0, label);
        add(northWrapper, BorderLayout.NORTH);

        JDatePicker datePicker = new JDatePicker();

        JDatePanel datePanel = new JDatePanel();
        datePanel.setMaximumSize(new Dimension());

        datePanel.getModel().getDay();


        JPanel timePanel = createTimelinePanel();

        JScrollPane scrollPane = new JScrollPane(timePanel);
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setHorizontalScrollBar(null);

        add(datePanel, BorderLayout.CENTER);

        add(scrollPane, BorderLayout.EAST);

        JPanel southWrapper = CommonGUI.createSouthWrapper(10,
                CommonGUI.createButton(180, 40, "Rezerwuj"),
                CommonGUI.createButton(180, 40, "Powrót")
        );

        add(southWrapper, BorderLayout.SOUTH);

    }

    public static JPanel createTimelinePanel() {
        JPanel timelinePanel = new JPanel();
        timelinePanel.setLayout(new BoxLayout(timelinePanel, BoxLayout.Y_AXIS));
        timelinePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String[] hours = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
                "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};

        for (int i = 0; i < hours.length; i++) {
            timelinePanel.add(Box.createVerticalStrut(2));
            JPanel hourRow = createHourRow(hours[i], i % 2 == 0 ? "wolne" : "zajęte");
            timelinePanel.add(hourRow);

            if (i < hours.length - 1) {
                timelinePanel.add(Box.createVerticalStrut(2));
                timelinePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
                timelinePanel.add(Box.createVerticalStrut(2));
            }
        }

        return timelinePanel;
    }

    private static JPanel createHourRow(String time, String status) {
        JPanel rowPanel = new JPanel(new BorderLayout(0, 0));
        rowPanel.setPreferredSize(new Dimension(200, 30));

        JLabel timeLabel = createBoldLabel(time);
        timeLabel.setPreferredSize(new Dimension(60, 30));

        rowPanel.add(timeLabel, BorderLayout.WEST);

        JLabel statusLabel = createBoldLabel(status);
        JPanel rightPanel = new JPanel();
        rightPanel.add(statusLabel);

        if ("wolne".equals(status)) {
            Color lightGreen = new Color(144, 238, 144);
            rightPanel.setBackground(lightGreen);
        } else {
            Color lightRed = new Color(255, 182, 193);
            rightPanel.setBackground(lightRed);
        }

        rowPanel.add(rightPanel, BorderLayout.CENTER);

        return rowPanel;
    }


}
