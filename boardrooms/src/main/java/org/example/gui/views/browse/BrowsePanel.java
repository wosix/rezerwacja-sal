package org.example.gui.views.browse;

import org.example.gui.CommonGUI;
import org.example.gui.MainFrame;
import org.example.model.Boardroom;

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

public class BrowsePanel extends JPanel {

    private JTable table;
    private BoardroomTableModel tableModel;
    private JScrollPane scrollPane;

    public BrowsePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        tableModel = new BoardroomTableModel();
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

        loadTestData();

        return scrollPane;
    }

    private void loadTestData() {
        List<Boardroom> testRooms = createDumy();
        tableModel.setRooms(testRooms);
    }

    private void showSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Konwertuj indeks (bo tabela może być posortowana)
            int modelRow = table.convertRowIndexToModel(selectedRow);

            Boardroom room = tableModel.getRooms().get(modelRow);
//            JOptionPane.showMessageDialog(this,
//                    "Wybrano: " + room.getId() + "\n" +
//                            "Typ: " + room.getRoomType() + "\n" +
//                            "Status: " + room.isActive(),
//                    "Informacja",
//                    JOptionPane.INFORMATION_MESSAGE);

            MainFrame.getInstance().showBooking(room);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Nie wybrano żadnej sali!",
                    "Uwaga",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private List<Boardroom> createDumy() {
        List<Boardroom> boardroomArrayList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
//            boardroomArrayList.add(
//                    new Boardroom((long) i,
//                            i % 3 == 0 ? RoomType.MEETING : RoomType.CONFERENCE,
//                            i % 2 == 0 ? RoomSize.SMALL : RoomSize.MEDIUM,
//                            new Equipment(), true
//                    )
//            );
        }
        return boardroomArrayList;
    }

}
