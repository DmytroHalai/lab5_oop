package builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class ShapeEditorFrame extends JFrame {
    private final MainEditor editor;
    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    public ShapeEditorFrame() {
        editor = MainEditor.getInstance(this);
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

        JMenuItem saveTable = new JMenuItem("Зберегти як");
        saveTable.addActionListener(e -> editor.saveTable(fileChooser));
        fileMenu.add(saveTable);

        JMenuItem loadTable = new JMenuItem("Завантажити з");
        loadTable.addActionListener(e -> editor.loadAndRepaint(editor, fileChooser));
        fileMenu.add(loadTable);

        JMenuItem deleteAllShapes = new JMenuItem("Видалити усі елементи");
        deleteAllShapes.addActionListener(e -> {
            editor.getCurrentShapeEditor().deleteShapes();
            editor.repaintShapes();
        });
        fileMenu.add(deleteAllShapes);

        JMenuItem saveSceneItem = new JMenuItem("Зберегти сцену як PNG");
        saveSceneItem.addActionListener(e -> saveSceneAsImage());
        fileMenu.add(saveSceneItem);

        menuBar.add(fileMenu);
        menuBar.add(shapeMenu);

        return menuBar;
    }

    private void saveSceneAsImage() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            try {
                Dimension size = editor.getCanvasSize();
                BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();

                editor.renderScene(g2d);
                g2d.setBackground(Color.WHITE);
                g2d.dispose();

                javax.imageio.ImageIO.write(image, "png", file);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка при збереженні сцени");
            }
        }
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