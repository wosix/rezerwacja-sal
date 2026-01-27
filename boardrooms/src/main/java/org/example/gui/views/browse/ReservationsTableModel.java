package org.example.gui.views.browse;

import org.example.model.dto.ReservationTableDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReservationsTableModel extends AbstractTableModel {

    private List<ReservationTableDTO> reservations = new ArrayList<>();
    private final String[] columnNames = {
            "Nr",
            "Typ",
            "Pojemność",
            "Początek",
            "Koniec",
            "Status"
    };

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReservationTableDTO reservationDTO = reservations.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> reservationDTO.getBoardroomNumber();
            case 1 -> reservationDTO.getBoardroomType();
            case 2 -> reservationDTO.getBoardroomSize();
            case 3 -> reservationDTO.getStart();
            case 4 -> reservationDTO.getEnd();
            case 5 -> reservationDTO.getStatus();
            default -> "";
        };
    }

    public void setReservations(List<ReservationTableDTO> reservations) {
        this.reservations = reservations;
        System.out.println("mamy rezerwacji : " + this.reservations);
        fireTableDataChanged();
    }

    public List<ReservationTableDTO> getReservations() {
        return reservations;
    }

}
