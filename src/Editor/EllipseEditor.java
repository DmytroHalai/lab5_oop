package Editor;

import Shape.EllipseShape;
import java.awt.*;

public class EllipseEditor extends ShapeEditor {
    private EllipseShape currentEllipse;

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentEllipse = new EllipseShape();
        currentEllipse.set(x, y, x, y);
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentEllipse != null) {
            currentEllipse.set(x1, y1, x2, y2);
            addShape(currentEllipse);
            currentEllipse = null;
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentEllipse != null) {
            currentEllipse.set(x1, y1, x, y); // Оновлюємо координати гумового сліду
        }
    }
}
