package utils;

import builder.MainEditor;
import drawers.Shape;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        int columnCount = table.getRowCount();
        Vector<Vector> dataVector = table.getDataVector();
        for (int i = 0; i < columnCount; i++){
            createShapeFromRow(dataVector.get(i));
        }
        updateTable();

    }

    private void createShapeFromRow(Vector row) {
        try {
            String name = (String) row.get(0);
            int x1 = Integer.parseInt((String) row.get(1));
            int y1 = Integer.parseInt((String) row.get(2));
            int x2 = Integer.parseInt((String) row.get(3));
            int y2 = Integer.parseInt((String) row.get(4));

            Shape shape = ShapeFactory.createShape(name);
            shape.set(x1, y1, x2, y2);
            shapes.add(shape);
        } catch (NumberFormatException e) {
            System.err.println("Помилка конвертації координат: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Некоректний рядок у таблиці: " + e.getMessage());
        }
    }

    /**
     * Зберігає таблицю з фігурами у файл.
     *
     * @param file файл, у який потрібно зберегти дані
     * @throws IOException якщо виникла помилка вводу/виводу
     */
    public void saveTableToFile(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Shape shape : shapes) {
                // Формат: назва_фігури,x1,y1,x2,y2
                String line = String.format("%s,%d,%d,%d,%d",
                        shape.getType(), // Припускаємо, що Shape має метод getName()
                        shape.getXs1(), shape.getYs1(), shape.getXs2(), shape.getYs2());
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Завантажує таблицю з фігурами з файлу.
     *
     * @param file файл, з якого потрібно завантажити дані
     * @throws IOException якщо виникла помилка вводу/виводу
     */
    public void loadTableFromFile(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            shapes.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.err.println("Некоректний формат рядка: " + line);
                    continue;
                }

                try {
                    String name = parts[0];
                    int x1 = Integer.parseInt(parts[1]);
                    int y1 = Integer.parseInt(parts[2]);
                    int x2 = Integer.parseInt(parts[3]);
                    int y2 = Integer.parseInt(parts[4]);

                    Shape shape = ShapeFactory.createShape(name);
                    shape.set(x1, y1, x2, y2);
                    shapes.add(shape);
                } catch (NumberFormatException | NullPointerException e) {
                    System.err.println("Помилка у рядку: " + line);
                }
            }
            updateTable();
        }
    }
}
