package drawers;

import java.awt.*;

public class LineOOShape extends Shape implements EllipseDrawer, LineDrawer {

    @Override
    public void showEl(Graphics2D g, int x, int y, int width, int height, boolean isMark) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawOval(x, y, width, height);
    }

    @Override
    public void showLine(Graphics2D g, int x, int y, int x2, int y2, boolean isMark) {
        g.setColor(Color.BLACK);
        new StrokeSetter(g, 3, isMark, 10);
        g.drawLine(xs1, ys1, xs2, ys2);
    }

    private void drawElls(Graphics2D g, int r, boolean isMark) {
        showEl(g, xs1 - r / 2, ys1 - r / 2, r, r, isMark);
        showEl(g, xs2 - r / 2, ys2 - r / 2, r, r, isMark);
    }

    @Override
    public void show(Graphics2D g, boolean isMark) {
        int r = 10;
        showLine(g, xs1, ys1, xs2, ys2, isMark);
        drawElls(g, r, isMark);
    }
}
