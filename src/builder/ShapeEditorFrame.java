package builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ShapeEditorFrame extends JFrame {
    private final MainEditor editor;

    public ShapeEditorFrame() {
        editor = new MainEditor(this);
        ShapeToolBar shapeToolBar = new ShapeToolBar(editor);

        setTitle("Редактор фігур");
        setSize(800, 600);
        setJMenuBar(createMenuBar());
        add(editor, BorderLayout.CENTER);
        add(shapeToolBar.getPanel(), BorderLayout.NORTH);

        initMouseListeners();
        initKeyBindings();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = new JMenu("Об'єкти");
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem showTableItem = new JMenuItem("Показати таблицю");
        showTableItem.addActionListener(e -> editor.showTable());
        fileMenu.add(showTableItem);

        JMenuItem saveTableItem = new JMenuItem("Зберегти таблицю у Excel");
        saveTableItem.addActionListener(e -> editor.getCurrentShapeEditor().getShapeTable().saveTableToExcel());
        fileMenu.add(saveTableItem);

        menuBar.add(fileMenu);
        menuBar.add(shapeMenu);

        return menuBar;
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
