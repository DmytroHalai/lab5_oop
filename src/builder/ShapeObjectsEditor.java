package builder;

import utils.*;

import javax.swing.*;
import java.awt.*;

public class ShapeObjectsEditor extends JPanel {
    private transient ShapeEditor currentShape;

    public ShapeObjectsEditor() {
        currentShape = null;
    }

    public void startPointEditor() {
        currentShape = new PointEditor();
    }

    public void startLineEditor() {
        currentShape = new LineEditor();
    }

    public void startRectEditor() {
        currentShape = new RectEditor();
    }

    public void startEllipseEditor() {
        currentShape = new EllipseEditor();
    }

    public void startLineOOEditor() {
        currentShape = new LineOOEditor();
    }

    public void onLBdown(Graphics2D g, int x, int y) {
        if (isNotEmpty(currentShape)) currentShape.onLBdown(g, x, y);
    }

    public void onLBup(Graphics2D g) {
        if (isNotEmpty(currentShape)) currentShape.onLBup(g);
    }

    public void onMouseMove(Graphics2D g, int x, int y) {
        if (isNotEmpty(currentShape)) currentShape.onMouseMove(g, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        if (isNotEmpty(currentShape)) onPaint(g2d);
    }

    public void onPaint(Graphics2D g) {
        if (isNotEmpty(currentShape)) currentShape.onPaint(g);
    }

    private boolean isNotEmpty(ShapeEditor el) {
        return el != null;
    }
}
