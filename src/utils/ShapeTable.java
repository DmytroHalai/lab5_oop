package utils;

import builder.MainEditor;
import drawers.Shape;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ShapeTable extends JDialog {
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Назва", "x1", "y1", "x2", "y2"}, 0);
    private final JTable myJTable = new JTable(tableModel);
    private final JFileChooser myJFileChooser = new JFileChooser(new File("."));
    public ShapeTable(Frame owner, MainEditor editor) {
        super(owner, "Список об'єктів", false);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new java.awt.GridLayout(1, 2));

        JButton jbtSave = new JButton("Зберегти");
        JButton jbtLoad = new JButton("Завантажити");

        panel.add(jbtSave);
        panel.add(jbtLoad);

        add(panel, BorderLayout.SOUTH);

        myJTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = myJTable.getSelectedRow();
            if (selectedRow >= 0) {
                Shape selectedShape = editor.getCurrentShapeEditor().getShapes().get(selectedRow);
                editor.highlightShape(selectedShape);
            }
        });

        jbtSave.addActionListener(e -> saveTable());

        jbtLoad.addActionListener(e -> {
            loadTable();
            editor.getCurrentShapeEditor().updateShapesArrayFromTable(tableModel);
            editor.repaintShapes();
        });

        JScrollPane scrollPane = new JScrollPane(myJTable);
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

    private Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        for (int i = 0; i < myJTable.getColumnCount(); i++)
            columnNames.add(myJTable.getColumnName(i));
        return columnNames;
    }


    private void saveTable() {
        if (myJFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = myJFileChooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }
            saveTable(selectedFile);
        }
    }

    private void saveTable(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Записуємо заголовки таблиці
            for (int i = 0; i < myJTable.getColumnCount(); i++) {
                writer.write(myJTable.getColumnName(i));
                if (i < myJTable.getColumnCount() - 1) {
                    writer.write("\t");
                }
            }
            writer.newLine();

            // Записуємо дані таблиці
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadTable() {
        if (myJFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadTable(myJFileChooser.getSelectedFile());
        }
    }

    private void loadTable(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("Файл порожній або пошкоджений");
            }

            tableModel.setRowCount(0);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\t");
                tableModel.addRow(values);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
