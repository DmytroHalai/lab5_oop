package utils;

import builder.MainEditor;
import drawers.Shape;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class ShapeEditor {
    protected final List<Shape> shapes = new ArrayList<>();
    private final ShapeTable shapeTable;
    protected boolean isDragging = false;
    protected Shape currentShape;
    private Shape highlightedShape;

    public ShapeEditor(MainEditor editor, Frame owner) {
        shapeTable = new ShapeTable(owner, editor);
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

    public void deleteShapes(){
        if (!shapes.isEmpty()) {
            shapes.clear();
            updateTable();
        }
    }

    public void updateShapesArrayFromTable(DefaultTableModel table){
        shapes.clear();
        int columnCount = table.getColumnCount();
        Vector<Vector> dataVector = table.getDataVector();
        for (int i = 0; i < columnCount; i++){
            createShapeFromRow(dataVector.get(i));
        }
        updateTable();
    }

    private void createShapeFromRow(Vector row) {
        String name = (String) row.get(0);
        int x1 = (int) row.get(1);
        int y1 = (int) row.get(2);
        int x2 = (int) row.get(3);
        int y2 = (int) row.get(4);

        Shape shape = ShapeFactory.createShape(name);
        shape.set(x1, y1, x2, y2);
        shapes.add(shape);
    }
}
