package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class CommonGUI {

    public static JPanel createTitleWrapper(String title) {
        JLabel titleLabel = CommonGUI.createTitleLabel(title);
        return createNorthWrapper(50, titleLabel);
    }

    public static JPanel createNorthWrapper(int top, Component... components) {
        return createWrapper(top, 0, components);
    }

    public static JPanel createActionButtonWrapper(Component... components) {
        return createWrapper(0, 50, components);
    }

    public static JPanel createSouthWrapper(int bottom, Component... components) {
        return createWrapper(0, bottom, components);
    }

    public static JPanel createWrapper(int top, int bottom, Component... components) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        for (Component comp : components) {
            panel.add(comp);
        }

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(top, 0, bottom, 0));
        wrapper.add(panel, BorderLayout.CENTER);

        return wrapper;
    }

    public static JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        return titleLabel;
    }

    public static JButton createButton(int width, int height, String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

}
