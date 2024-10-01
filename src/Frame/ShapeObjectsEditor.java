package Frame;

import Editor.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Shape.Shape;

public class ShapeObjectsEditor extends JPanel {
    private ShapeEditor currentShape;

    private final ArrayList<Shape> shapeList;

    public ShapeObjectsEditor() {
        currentShape = null;
        shapeList = new ArrayList<>();
    }

    public void startPointEditor() {currentShape = new PointEditor();}

    public void startLineEditor() {currentShape = new LineEditor();}

    public void startRectEditor() {
        currentShape = new RectEditor();
    }

    public void startEllipseEditor() {
        currentShape = new EllipseEditor();
    }

    public void onLBdown(Graphics g, int x, int y) {
        if (currentShape != null) {
            currentShape.onLBdown(g, x, y);
        }
    }

    public void onLBup(Graphics g) {
        if (currentShape != null) {
            currentShape.onLBup(g);
        }
    }

    public void onMouseMove(Graphics g, int x, int y) {
        if (currentShape != null) {
            currentShape.onMouseMove(g, x, y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape ignored : shapeList) {
            onPaint(g);
        }
        if (currentShape != null) {
            onPaint(g);
        }
    }

    public void onPaint(Graphics g) {
        if (currentShape != null) {
            currentShape.onPaint(g);
        }
    }
}
