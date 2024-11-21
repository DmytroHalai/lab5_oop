package utils;

import builder.MainEditor;
import drawers.Shape;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShapeTable extends JDialog {
    private final DefaultTableModel tableModel;

    public ShapeTable(Frame owner, MainEditor editor) {
        super(owner, "Список об'єктів", false);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Назва", "x1", "y1", "x2", "y2"}, 0);
        JTable table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Shape selectedShape = editor.getCurrentShapeEditor().getShapes().get(selectedRow);
                editor.highlightShape(selectedShape);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(owner);
    }


    public void addRow(String name, int x1, int y1, int x2, int y2) {
        tableModel.addRow(new Object[]{name, x1, y1, x2, y2});
    }

    public void updateTable(List<Shape> shapes) {
        tableModel.setRowCount(0);
        for (Shape shape : shapes) {
            addRow(shape.getType(), shape.getXs1(), shape.getYs1(), shape.getXs2(), shape.getYs2());
        }
    }
}
