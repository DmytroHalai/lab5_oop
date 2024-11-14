package builder;

import drawers.Shape;
import utils.ShapeEditor;

import javax.swing.*;
import java.awt.*;

public class MainEditor extends JPanel {
    private final transient ShapeEditor shapesEditor;

    public MainEditor(Frame owner) {
        shapesEditor = new ShapeEditor(this, owner);
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

    public void onLBup() throws InstantiationException, IllegalAccessException {
        shapesEditor.onLBup();
        repaintShapes();
    }

    public void onMouseMove(int x, int y) {
        shapesEditor.onMouseMove(x, y);
        repaintShapes();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        shapesEditor.onPaint(g2d);
    }

    public void showTable() {
        shapesEditor.showTable();
    }

    public void highlightShape(Shape shape) {
        shapesEditor.highlightShape(shape);
        repaintShapes();
    }

    public void repaintShapes(){
        repaint();
    }

}