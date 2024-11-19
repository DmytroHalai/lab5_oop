package utils;

import builder.MainEditor;
import drawers.Shape;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.List;

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

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteMenuItem = new JMenuItem("Видалити");

        deleteMenuItem.addActionListener(e -> {
            int row = myJTable.getSelectedRow();
            if (row >= 0) {
                deleteRow(row, editor);
            }
        });

        popupMenu.add(deleteMenuItem);

        myJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = myJTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < myJTable.getRowCount()) {
                        myJTable.setRowSelectionInterval(row, row);
                        popupMenu.show(myJTable, e.getX(), e.getY());
                    }
                }
            }
        });

        jbtSave.addActionListener(e -> saveTable(myJFileChooser));

        jbtLoad.addActionListener(e -> loadAndRepaint(editor, myJFileChooser));

        JScrollPane scrollPane = new JScrollPane(myJTable);
        add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(owner);
    }

    private void deleteRow(int row, MainEditor editor) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.removeRow(row);
            editor.getCurrentShapeEditor().removeShape(row);
            editor.repaintShapes();
        }
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


    public void saveTable(JFileChooser owner) {
        if (owner.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = owner.getSelectedFile();
            if (!selectedFile.getName().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }
            saveTable(selectedFile);
        }
    }

    private void saveTable(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < myJTable.getColumnCount(); i++) {
                writer.write(myJTable.getColumnName(i));
                if (i < myJTable.getColumnCount() - 1) {
                    writer.write("\t");
                }
            }
            writer.newLine();

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

    public void loadAndRepaint(MainEditor editor, JFileChooser myJFileChooser) {
        loadTable(myJFileChooser);
        editor.getCurrentShapeEditor().updateShapesArrayFromTable(tableModel);
        editor.repaintShapes();
    }

    public void loadTable(JFileChooser owner) {
        if (owner.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadTable(owner.getSelectedFile());

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
