package utils;

import drawers.LineOOShape;
import drawers.LineShape;

import java.awt.*;

public class LineOOEditor extends ShapeEditor{
    private LineOOShape currentLine;

    @Override
    public void onLBdown(Graphics2D g, int x, int y) {
        super.onLBdown(g, x, y);
        currentLine = new LineOOShape();
        currentLine.set(x, y, x, y);
    }

    @Override
    public void onLBup(Graphics2D g) {
        super.onLBup(g);
        if (currentLine != null) {
            addShape(currentLine);
            currentLine = null;
        }
    }

    @Override
    public void onMouseMove(Graphics2D g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentLine != null) {
            currentLine.set(x1, y1, x, y);
        }
    }
    @Override
    public void onPaint(Graphics2D g){
        super.onPaint(g);
        if (isDragging) currentLine.show(g, true);
    }
}
