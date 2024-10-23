package builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeEditorFrame extends JFrame {
    private final ShapeObjectsEditor editor;
    private JButton lastPressedButton;

    public ShapeEditorFrame() {
        editor = new ShapeObjectsEditor();
        setTitle("Редактор фігур");
        setSize(800, 600);
        setJMenuBar(createMenuBar());
        add(editor);
        initMouseListeners();
    }

    private void initMouseListeners() {
        editor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editor.onLBdown(editor.getGraphics(), e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                editor.onLBup(editor.getGraphics());
                editor.repaint();
            }
        });

        editor.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                editor.onMouseMove(editor.getGraphics(), e.getX(), e.getY());
                editor.repaint();
            }
        });
    }

    private JMenuBar createMenuBar() {
        String ellipse = "Еліпс";
        String point = "Точка";
        String rect = "Прямокутник";
        String line = "Лінія";

        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = new JMenu("Об'єкти");
        JMenu menu1 = new JMenu("Файл");
        JMenu menu2 = new JMenu("Довідка");
        JToolBar toolBar = new JToolBar();
        JPanel panel = new JPanel();

        addToolBarButton(panel, "pic/ellipse.png", e -> {
            editor.startEllipseEditor();
            setTitle(ellipse);

            changeButtonColor(e);
        }, "Еліпс");

        addToolBarButton(panel, "pic/rect.png", e -> {
            editor.startRectEditor();
            setTitle(rect);

            changeButtonColor(e);
        }, "Прямокутник");

        addToolBarButton(panel, "pic/line.png", e -> {
            editor.startLineEditor();
            setTitle(line);

            changeButtonColor(e);
        }, "Лінія");

        addToolBarButton(panel, "pic/point.png", e -> {
            editor.startPointEditor();
            setTitle(point);

            changeButtonColor(e);
        }, "Точка");

        addMenuItem(shapeMenu, point, e -> {
            editor.startPointEditor();
            setTitle(point);
        });

        addMenuItem(shapeMenu, line, e -> {
            editor.startLineEditor();
            setTitle(line);
        });

        addMenuItem(shapeMenu, rect, e -> {
            editor.startRectEditor();
            setTitle(rect);
        });

        addMenuItem(shapeMenu, ellipse, e -> {
            editor.startEllipseEditor();
            setTitle(ellipse);
        });

        toolBar.add(panel);
        menuBar.add(menu1);
        menuBar.add(shapeMenu);
        menuBar.add(menu2);
        menuBar.add(toolBar);

        return menuBar;
    }

    private void addToolBarButton(JPanel panel, String iconPath, ActionListener action, String toolTipText) {
        JButton button = createButtonWithIcon(iconPath);
        button.setBackground(Color.WHITE);
        button.setToolTipText(toolTipText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addMenuItem(JMenu menu, String name, ActionListener action) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(action);
        menu.add(item);
    }

    private JButton createButtonWithIcon(String iconPath) {
        java.net.URL imgURL = ShapeEditorFrame.class.getResource(iconPath);
        assert imgURL != null;
        ImageIcon icon = new ImageIcon(imgURL);
        Image scaledImage = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        return new JButton(new ImageIcon(scaledImage));
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
