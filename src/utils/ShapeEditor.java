package utils;

import builder.MyEditor;
import drawers.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShapeEditor {
    protected final List<Shape> shapes = new ArrayList<>();
    private final MyTable shapeTable;
    protected boolean isDragging = false;
    protected Shape currentShape;
    private Shape highlightedShape;

    public ShapeEditor(MyEditor editor, Frame owner) {
        shapeTable = new MyTable(owner, editor);
    }

    public void onLBdown(int x, int y) {
        isDragging = true;
        if (currentShape != null) {
            currentShape.set(x, y, x, y);
        }
    }

    public void onLBup() throws InstantiationException, IllegalAccessException {
        isDragging = false;
        Shape temp = currentShape.getClass().newInstance();
        if (currentShape != null) {
            shapes.add(currentShape);
            updateTable();
            currentShape = temp;
        }
    }

    public void onMouseMove(int x, int y) {
        if (isDragging && currentShape != null) {
            currentShape.set(currentShape.xs1, currentShape.ys1, x, y);
        }
    }

    public void onPaint(Graphics2D g) {
        for (Shape shape : shapes) {
            shape.show(g, false, shape == highlightedShape);
        }
        if (isDragging) {
            currentShape.show(g, true, true);
        }
    }

    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    public void undoLastShape() {
        if (!shapes.isEmpty()) {
            shapes.removeLast();
            updateTable();
        }
    }

    public void highlightShape(Shape shape) {
        if (this.highlightedShape != shape) {
            this.highlightedShape = shape;
        }
    }

    private void updateTable() {
        shapeTable.updateTable(shapes);
    }

    public void showTable() {
        shapeTable.setVisible(true);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
