package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeEditorFrame extends JFrame {
    private final ShapeObjectsEditor editor;
    public ShapeEditorFrame() {
        editor = new ShapeObjectsEditor();
        setTitle("Редактор фігур");
        setSize(800, 600);
        setJMenuBar(createMenuBar());
        add(editor);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = new JMenu("Об'єкти");

        JMenuItem pointItem = new JMenuItem("Точка");
        pointItem.addActionListener(e -> {
            editor.startPointEditor();
            shapeMenu.setText("Точка");
        });

        JMenuItem lineItem = new JMenuItem("Лінія");
        lineItem.addActionListener(e -> {
            editor.startLineEditor();
            shapeMenu.setText("Лінія");
        });

        JMenuItem rectItem = new JMenuItem("Прямокутник");
        rectItem.addActionListener(e -> {
            editor.startRectEditor();
            shapeMenu.setText("Прямокутник");
        });

        JMenuItem ellipseItem = new JMenuItem("Еліпс");
        ellipseItem.addActionListener(e -> {
            editor.startEllipseEditor();
            shapeMenu.setText("Еліпс");
        });

        shapeMenu.add(pointItem);
        shapeMenu.add(lineItem);
        shapeMenu.add(rectItem);
        shapeMenu.add(ellipseItem);
        menuBar.add(shapeMenu);

        return menuBar;
    }
}
