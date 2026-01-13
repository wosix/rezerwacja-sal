package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class BrowsePanel extends JPanel {

    public BrowsePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("przeglodaj wszystkie sale");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        topPanel.add(label);

        JPanel panel = new JPanel();

        String[] kolumny = {"ID", "Nazwa", "Pojemność"};
        Object[][] dane = {
                {"1", "Sala A", 200},
                {"2", "Sala B", 350},
                {"3", "Sala C", 150}
        };

        JTable table = new JTable(dane, kolumny);

        JScrollPane jScrollPane = new JScrollPane(table);

        jScrollPane.setPreferredSize(new Dimension(800, 650));

        panel.add(jScrollPane);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.WEST);

    }


}
