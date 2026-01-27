package org.example.gui.views.browse;

import org.example.gui.CommonGUI;
import org.example.model.Account;
import org.example.model.Reservation;
import org.example.model.dto.ReservationTableDTO;
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
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel northWrapper = CommonGUI.createNorthWrapper(25,
                CommonGUI.createTitleLabel("Moje rezerwacje"));
        add(northWrapper);

        tableModel = new ReservationsTableModel();
        scrollPane = createTable(tableModel);
        add(scrollPane, BorderLayout.CENTER);


        JButton selectButton = CommonGUI.createButton(180, 40,
                "Wybierz");
        selectButton.addActionListener(e -> showSelectedRow());

        JPanel southWrapper = CommonGUI.createSouthWrapper(30, selectButton);
        add(southWrapper, BorderLayout.SOUTH);
    }

    private JScrollPane createTable(AbstractTableModel tableModel) {
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(createEmptyBorder(20, 20, 20, 20));
        scrollPane.setColumnHeaderView(table.getTableHeader());

        loadReservations();

        return scrollPane;
    }

    private void loadReservations() {
//        List<Reservation> reservations = reservationService.getByUserId(account.getId());
        List<Reservation> reservations = reservationService.getAll();


        List<ReservationTableDTO> reservationTableDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationTableDTOS.add(reservationService.mapToTableDto(reservation));
        }

        System.out.println("przerobilo rezerwacji : " + reservationTableDTOS.size());

        tableModel.setReservations(reservationTableDTOS);
    }

    private void showSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Konwertuj indeks (bo tabela może być posortowana)
            int modelRow = table.convertRowIndexToModel(selectedRow);

            ReservationTableDTO room = tableModel.getReservations().get(modelRow);

//            MainFrame.getInstance().showBooking(room);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Nie wybrano żadnej sali!",
                    "Uwaga",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
