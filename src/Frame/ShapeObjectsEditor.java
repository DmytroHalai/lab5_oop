package Frame;

import Editor.*;
import javax.swing.*;
import java.awt.*;

public class ShapeObjectsEditor extends JPanel {
    private ShapeEditor currentShape;

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
        // Малюємо фігури
        if (currentShape != null) {
            onPaint(g); // Викликаємо onPaint для малювання поточної фігури
        }
    }

    public void onPaint(Graphics g) {
        if (currentShape != null) {
            currentShape.onPaint(g);
        }
    }

    public void onInitMenuPopup(Graphics g) {
        // Логіка для ініціалізації меню
    }
}
