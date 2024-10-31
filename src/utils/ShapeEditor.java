package utils;

import drawers.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeEditor {
    protected final List<Shape> shapes = new ArrayList<>();
    protected boolean isDragging = false;
    protected Shape currentShape;

    public void onLBdown(int x, int y) {
        isDragging = true;
        if (currentShape != null) {
            currentShape.set(x, y, x, y);
        }
    }

    public void onLBup() {
        isDragging = false;
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
        }
    }

    public void onMouseMove(int x, int y) {
        if (isDragging && currentShape != null) {
            currentShape.set(currentShape.xs1, currentShape.ys1, x, y);
        }
    }

    public void onPaint(Graphics2D g) {
        for (Shape shape : shapes) {
            shape.show(g, false);
        }
        if (currentShape != null) {
            currentShape.show(g, true);
        }
    }

    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }
}
