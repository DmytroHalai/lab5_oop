package drawers;

import java.awt.*;

public class LineOOShape extends Shape implements EllipseDrawer, LineDrawer {

    @Override
    public void showEl(Graphics2D g, int x, int y, int width, int height, boolean isMark, boolean isHighlight) {
        g.setColor(isHighlight ? Color.RED : Color.BLACK);
        new StrokeSetter(g, 3, isMark, 10);
        g.drawOval(x, y, width, height);
    }

    @Override
    public void showLine(Graphics2D g, int x, int y, int x2, int y2, boolean isMark, boolean isHighlight) {
        g.setColor(isHighlight ? Color.RED : Color.BLACK);
        new StrokeSetter(g, 3, isMark, 10);
        g.drawLine(xs1, ys1, xs2, ys2);
    }

    private void drawElls(Graphics2D g, int r, boolean isMark, boolean isHighlight) {
        showEl(g, xs1 - r / 2, ys1 - r / 2, r, r, isMark, isHighlight);
        showEl(g, xs2 - r / 2, ys2 - r / 2, r, r, isMark, isHighlight);
    }

    @Override
    public void show(Graphics2D g, boolean isMark, boolean isHighlight) {
        int r = 10;
        showLine(g, xs1, ys1, xs2, ys2, isMark, isHighlight);
        drawElls(g, r, isMark, isHighlight);
    }

    @Override
    public String getType() {
        return "LineOO";
    }
}
