package org.example.gui.views.dashboard;

import org.example.gui.CommonGUI;
import org.example.gui.views.MainFrame;
import org.example.model.entity.Boardroom;
import org.example.service.BoardroomServiceImpl;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import static javax.swing.BorderFactory.createEmptyBorder;

public class BrowsePanel extends JPanel {

    private final BoardroomServiceImpl boardroomService;

    private JTable table;
    private BoardroomTableModel tableModel;
    private JScrollPane scrollPane;

    private JButton selectButton;

    public BrowsePanel() {
        this.boardroomService = new BoardroomServiceImpl();
        initComponents();
        loadBoardrooms();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        tableModel = new BoardroomTableModel();
        scrollPane = createTable(tableModel);
        add(scrollPane, BorderLayout.CENTER);

        selectButton = CommonGUI.createButton(180, 40,
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

        return scrollPane;
    }

    private void loadBoardrooms() {
        List<Boardroom> boardrooms = boardroomService.getAll();
        tableModel.setRooms(boardrooms);
    }

    private void showSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = table.convertRowIndexToModel(selectedRow);

            Boardroom room = tableModel.getRooms().get(modelRow);

            if (room.isAvailable()) {
                MainFrame.getInstance().showBooking(room);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Wybrano nie aktywną salę!",
                        "Uwaga",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Nie wybrano żadnej sali!",
                    "Uwaga",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
