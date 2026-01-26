package gui;

import entity.Boardroom;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BookingPanel extends JPanel {

    private Boardroom boardroom;

    public BookingPanel() {
        initNoBoardroom();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        assert boardroom != null;
        JPanel northWrapper = CommonGUI.createTitleWrapper("Rezerwacja sali " + boardroom.getId());

        add(northWrapper, BorderLayout.NORTH);


        JLabel typeLabel = new JLabel(String.valueOf(boardroom.getRoomType()));
        JLabel sizeLabel = new JLabel(String.valueOf(boardroom.getRoomSize()));
        JLabel projektorLabel = new JLabel(String.valueOf(boardroom.getEquipment().hasProjector()));

        JPanel detailsPanel = CommonGUI.createWrapper(0, 0,
                typeLabel,
                sizeLabel,
                projektorLabel
        );

        add(detailsPanel, BorderLayout.CENTER);

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

}
