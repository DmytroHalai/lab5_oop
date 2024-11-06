package builder;

import drawers.Shape;
import utils.ShapeEditor;

import javax.swing.*;
import java.awt.*;

public class MyEditor extends JPanel {
    private final transient ShapeEditor shapesEditor;

    public MyEditor() {
        shapesEditor = new ShapeEditor();
    }

    public void setCurrentShape(Shape shape) {
        shapesEditor.setCurrentShape(shape);
    }

    public ShapeEditor getCurrentShapeEditor() {
        return shapesEditor;
    }

    public void onLBdown(int x, int y) {
        shapesEditor.onLBdown(x, y);
    }

    public void onLBup() {
        shapesEditor.onLBup();
        repaint();
    }

    public void onMouseMove(int x, int y) {
        shapesEditor.onMouseMove(x, y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        shapesEditor.onPaint(g2d);
    }
}