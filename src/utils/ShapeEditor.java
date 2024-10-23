package utils;

import drawers.Shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeEditor implements Editor {
    protected boolean isDragging = false;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    protected static final List<Shape> shapes = new ArrayList<>(104);

    @Override
    public void onLBdown(Graphics2D g, int x, int y) {
        isDragging = true;
        x1 = x;
        y1 = y;
    }

    @Override
    public void onLBup(Graphics2D g) {
        isDragging = false;
    }

    @Override
    public void onMouseMove(Graphics2D g, int x, int y) {
        if (isDragging) {
            x2 = x;
            y2 = y;
        }
    }

    @Override
    public void onPaint(Graphics2D g) {
        for (Shape shape : shapes) {
            shape.show(g, false);
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
}