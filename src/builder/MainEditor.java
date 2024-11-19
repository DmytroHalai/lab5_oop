package builder;

import drawers.Shape;
import utils.ShapeEditor;

import javax.swing.*;
import java.awt.*;

public class MainEditor extends JPanel {
    private final transient ShapeEditor shapeEditor;
    private static MainEditor instance;

    private MainEditor(Frame owner) {
        shapeEditor = new ShapeEditor(this, owner);
    }

    public static MainEditor getInstance(Frame owner) {
        if (instance == null) {
            instance = new MainEditor(owner);
        }
        return instance;
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

    public void onLBup() throws InstantiationException, IllegalAccessException {
        shapeEditor.onLBup();
        repaintShapes();
    }

    public void onMouseMove(int x, int y) {
        shapeEditor.onMouseMove(x, y);
        repaintShapes();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        shapeEditor.onPaint(g2d);
    }

    public void showTable() {
        shapeEditor.showTable();
    }

    public void highlightShape(Shape shape) {
        shapeEditor.highlightShape(shape);
        repaintShapes();
    }

    public void repaintShapes(){
        repaint();
    }

    public Dimension getCanvasSize() {
        return this.getSize();
    }

    public void renderScene(Graphics2D g2d) {
        shapeEditor.onPaint(g2d);
    }

    public void saveTable(JFileChooser owner){
        shapeEditor.saveTable(owner);
    }

    public void loadAndRepaint(MainEditor editor, JFileChooser myJFileChooser) {
        shapeEditor.loadAndRepaint(editor, myJFileChooser);
    }
}