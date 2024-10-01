package Editor;

import Shape.RectShape;
import java.awt.*;

public class RectEditor extends ShapeEditor{
    private RectShape currentRect;

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentRect = new RectShape();
        currentRect.set(x, y, x, y);
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentRect != null) {
            currentRect.set(x1, y1, x2, y2);
            addShape(currentRect);
            currentRect = null;
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentRect != null) {
            currentRect.set(x1, y1, x, y);
        }
    }
}
