package org.example.gui.views.dashboard;


import org.example.model.entity.Boardroom;
import org.example.model.entity.Equipment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            case 1 -> room.getRoomType().getDisplayName();
            case 2 -> room.getRoomSize().getDisplayName();
            case 3 -> eqtToString(room.getEquipment());
            case 4 -> room.isAvailable();
            default -> "";
        };
    }

    public void setRooms(List<Boardroom> rooms) {
        this.rooms = rooms;
        fireTableDataChanged();
    }

    private String eqtToString(Equipment equipment) {
        Map<String, Boolean> equipmentMap = new LinkedHashMap<>();
        equipmentMap.put("Rzutnik", equipment.hasProjector());
        equipmentMap.put("Tablica", equipment.hasWhiteboard());
        equipmentMap.put("Nagłośnienie", equipment.hasSoundSystem());
        equipmentMap.put("Klimatyzacja", equipment.hasAirConditioning());
        equipmentMap.put("Wideokonferencje", equipment.hasVideoConferenceSystem());
        equipmentMap.put("Tv", equipment.hasTvScreen());

        String result = equipmentMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));

        return result.isEmpty() ? "Brak" : result;
    }

    public List<Boardroom> getRooms() {
        return rooms;
    }

}
