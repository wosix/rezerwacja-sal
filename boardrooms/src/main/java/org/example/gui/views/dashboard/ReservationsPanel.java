package org.example.gui.views.dashboard;

import org.example.exception.NotFoundException;
import org.example.gui.CommonGUI;
import org.example.model.dto.ReservationTableDTO;
import org.example.model.entity.Account;
import org.example.model.entity.Reservation;
import org.example.model.enums.ReservationStatus;
import org.example.service.ReservationServiceImpl;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.BorderFactory.createEmptyBorder;

public class ReservationsPanel extends JPanel {

    private final Account account;

    private final ReservationServiceImpl reservationService;

    private JTable table;
    private ReservationsTableModel tableModel;
    private JScrollPane scrollPane;

    public ReservationsPanel(Account user) {
        this.account = user;
        this.reservationService = new ReservationServiceImpl();
        initComponents();
        loadReservations();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel northWrapper = CommonGUI.createNorthWrapper(25,
                CommonGUI.createTitleLabel("Moje rezerwacje"));
        add(northWrapper);

        tableModel = new ReservationsTableModel();
        scrollPane = createTable(tableModel);
        add(scrollPane, BorderLayout.CENTER);


        JButton selectButton = createCancelButton();

        JPanel southWrapper = CommonGUI.createSouthWrapper(30,
                selectButton
        );
        add(southWrapper, BorderLayout.SOUTH);
    }

    private JButton createCancelButton() {
        JButton cancelButton = CommonGUI.createButton(180, 40,
                "Anuluj rezerwacje");
        cancelButton.addActionListener(e -> cancelReservation());
        return cancelButton;
    }

    private void cancelReservation() {
        try {
            ReservationTableDTO reservation = selectRow();
            if (reservation == null) return;
            System.out.println("id : " + reservation.getId());

            reservationService.cancel(reservation.getId());

            reservation.setStatus(ReservationStatus.CANCELLED);
            tableModel.fireTableDataChanged();
            repaint();

            JOptionPane.showMessageDialog(this,
                    "Udało się anulować rezerwację sali - " + reservation.getBoardroomNumber(),
                    "Sukces!",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (NotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Nie znaleziono!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JScrollPane createTable(AbstractTableModel tableModel) {
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(createEmptyBorder(20, 20, 20, 20));
        scrollPane.setColumnHeaderView(table.getTableHeader());

        return scrollPane;
    }

    private void loadReservations() {
        table.removeAll();
        List<Reservation> reservations = reservationService.getByUserId(account.getId());

        List<ReservationTableDTO> reservationTableDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationTableDTOS.add(reservationService.mapToTableDto(reservation));
        }

        tableModel.setReservations(reservationTableDTOS);
    }

    private ReservationTableDTO selectRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = table.convertRowIndexToModel(selectedRow);

            return tableModel.getReservations().get(modelRow);

        } else {
            JOptionPane.showMessageDialog(this,
                    "Nie wybrano żadnej sali!",
                    "Uwaga",
                    JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

}
