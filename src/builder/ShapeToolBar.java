package builder;

import drawers.*;
import drawers.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeToolBar {
    private final JPanel panel;
    private JButton lastPressedButton;
    private final MainEditor editor;

    public ShapeToolBar(MainEditor editor) {
        this.panel = new JPanel();
        this.editor = editor;
        initButtons();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initButtons() {
        addToolBarButton("pic/ellipse.png", e -> setCurrentShape(new EllipseShape(), e));
        addToolBarButton("pic/rect.png", e -> setCurrentShape(new RectShape(), e));
        addToolBarButton("pic/line.png", e -> setCurrentShape(new LineShape(), e));
        addToolBarButton("pic/point.png", e -> setCurrentShape(new PointShape(), e));
        addToolBarButton("pic/lineOO.png", e -> setCurrentShape(new LineOOShape(), e));
        addToolBarButton("pic/cube.png", e -> setCurrentShape(new CubeShape(), e));
    }

    private void addToolBarButton(String iconPath, ActionListener action) {
        JButton button = createButtonWithIcon(iconPath);
        button.addActionListener(action);
        button.setBackground(Color.WHITE);
        panel.add(button);
    }

    private JButton createButtonWithIcon(String iconPath) {
        java.net.URL imgURL = ShapeToolBar.class.getResource(iconPath);
        assert imgURL != null;
        ImageIcon icon = new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        return new JButton(icon);
    }

    private void setCurrentShape(Shape shape, ActionEvent e) {
        editor.setCurrentShape(shape);
        changeButtonColor(e);
    }

    private void changeButtonColor(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        if (lastPressedButton != null && lastPressedButton != sourceButton) {
            lastPressedButton.setBackground(Color.WHITE);
        }
        sourceButton.setBackground(Color.PINK);
        lastPressedButton = sourceButton;
    }
}
