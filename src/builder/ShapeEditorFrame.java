package builder;

import drawers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeEditorFrame extends JFrame {
    private final MyEditor editor;
    private JButton lastPressedButton;

    public ShapeEditorFrame() {
        editor = new MyEditor(this);
        setTitle("Редактор фігур");
        setSize(800, 600);
        setJMenuBar(createMenuBar());
        add(editor, BorderLayout.CENTER);
        initMouseListeners();
        initKeyBindings();
    }

    private void initMouseListeners() {
        editor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editor.onLBdown(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    editor.onLBup();
                } catch (InstantiationException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                editor.repaint();
            }
        });

        editor.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                editor.onMouseMove(e.getX(), e.getY());
                editor.repaint();
            }
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = new JMenu("Об'єкти");
        JMenu menu1 = new JMenu("Файл");
        JMenu menu2 = new JMenu("Довідка");
        JToolBar toolBar = new JToolBar();
        JPanel panel = new JPanel();
        JMenuItem showTableItem = new JMenuItem("Показати таблицю");

        String ellipse = "Еліпс";
        String point = "Точка";
        String rect = "Прямокутник";
        String line = "Лінія";
        String lineOO = "Лінія з еліпсами";
        String cube = "Куб";

        showTableItem.addActionListener(e -> editor.showTable());

        addToolBarButton(panel, "pic/ellipse.png", e -> {
            editor.setCurrentShape(new EllipseShape());
            setTitle(ellipse);

            changeButtonColor(e);
        }, ellipse);

        addToolBarButton(panel, "pic/rect.png", e -> {
            editor.setCurrentShape(new RectShape());
            setTitle(rect);

            changeButtonColor(e);
        }, rect);

        addToolBarButton(panel, "pic/line.png", e -> {
            editor.setCurrentShape(new LineShape());
            setTitle(line);

            changeButtonColor(e);
        }, line);

        addToolBarButton(panel, "pic/point.png", e -> {
            editor.setCurrentShape(new PointShape());
            setTitle(point);

            changeButtonColor(e);
        }, point);

        addToolBarButton(panel, "pic/lineOO.png", e -> {
            editor.setCurrentShape(new LineOOShape());
            setTitle(lineOO);

            changeButtonColor(e);
        }, lineOO);

        addToolBarButton(panel, "pic/cube.png", e -> {
            editor.setCurrentShape(new CubeShape());
            setTitle(cube);

            changeButtonColor(e);
        }, cube);

        addMenuItem(shapeMenu, point, e -> {
            editor.setCurrentShape(new PointShape());
            setTitle(point);
        });

        addMenuItem(shapeMenu, line, e -> {
            editor.setCurrentShape(new LineShape());
            setTitle(line);
        });

        addMenuItem(shapeMenu, rect, e -> {
            editor.setCurrentShape(new RectShape());
            setTitle(rect);
        });

        addMenuItem(shapeMenu, ellipse, e -> {
            editor.setCurrentShape(new EllipseShape());
            setTitle(ellipse);
        });

        addMenuItem(shapeMenu, lineOO, e -> {
            editor.setCurrentShape(new LineOOShape());
            setTitle(lineOO);
        });

        addMenuItem(shapeMenu, cube, e -> {
            editor.setCurrentShape(new CubeShape());
            setTitle(cube);
        });

        toolBar.add(panel);
        menu1.add(showTableItem);
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

    private void initKeyBindings() {
        InputMap inputMap = editor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = editor.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ctrl Z"), "undo");
        actionMap.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.getCurrentShapeEditor().undoLastShape();
                editor.repaint();
            }
        });
    }
}