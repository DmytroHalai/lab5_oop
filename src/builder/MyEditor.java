package builder;

import drawers.Shape;
import utils.ShapeEditor;

import javax.swing.*;
import java.awt.*;

public class MyEditor extends JPanel {
    private final transient ShapeEditor shapeEditor;

    public MyEditor() {
        shapeEditor = new ShapeEditor();
    }

    public void setCurrentShape(Shape shape) {
        shapeEditor.setCurrentShape(shape);
    }

    public ShapeEditor getCurrentShapeEditor() {
        return shapeEditor;
    }

    public void onLBdown(int x, int y) {
        shapeEditor.onLBdown(x, y);
    }

    public void onLBup() {
        shapeEditor.onLBup();
        repaint();
    }

    public void onMouseMove(int x, int y) {
        shapeEditor.onMouseMove(x, y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        shapeEditor.onPaint(g2d);
    }
}