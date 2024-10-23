package utils;

import drawers.RectShape;
import java.awt.*;

public class RectEditor extends ShapeEditor{
    private RectShape currentRect;

    @Override
    public void onLBdown(Graphics2D g, int x, int y) {
        super.onLBdown(g, x, y);
        currentRect = new RectShape();
        currentRect.set(x, y, x, y);
    }

    @Override
    public void onLBup(Graphics2D g) {
        super.onLBup(g);
        if (currentRect != null) {
            currentRect.set(x1, y1, x2, y2);
            addShape(currentRect);
            currentRect = null;
        }
    }

    @Override
    public void onMouseMove(Graphics2D g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentRect != null) {
            currentRect.set(x1, y1, x, y);
        }
    }

    @Override
    public void onPaint(Graphics2D g){
        super.onPaint(g);
        if (isDragging) currentRect.show(g, true);
    }
}
