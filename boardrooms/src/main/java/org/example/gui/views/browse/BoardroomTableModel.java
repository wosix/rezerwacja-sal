package org.example.gui.views.browse;


import org.example.model.Boardroom;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BoardroomTableModel extends AbstractTableModel {

    private List<Boardroom> rooms = new ArrayList<>();
    private final String[] columnNames = {
            "Nr",
            "Typ",
            "Pojemność",
            "Wyposażenie",
            "Status"
    };

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Boardroom room = rooms.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> room.getNumber();
            case 1 -> room.getRoomType();
            case 2 -> room.getRoomSize();
            case 3 -> room.getEquipment().hasProjector();
            case 4 -> room.isAvailable();
            default -> "";
        };
    }

    public void setRooms(List<Boardroom> rooms) {
        this.rooms = rooms;
        fireTableDataChanged();
    }

    public List<Boardroom> getRooms() {
        return rooms;
    }

}
