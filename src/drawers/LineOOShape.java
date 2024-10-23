package drawers;

import java.awt.*;

public class LineOOShape extends Shape implements EllipseDrawer, LineDrawer{

    @Override
    public void showEl(Graphics2D g, int x, int y, int width, int height, boolean isMark) {
        if (isMark) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillOval(x, y, width, height);
    }

    @Override
    public void showLine(Graphics2D g, int x, int y, int width, int height, boolean isMark) {
        if (isMark) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.setStroke(new BasicStroke(3));
        g.drawLine(xs1, ys1, xs2, ys2);
    }

    @Override
    public void show(Graphics2D g, boolean isMark) {
        showLine(g, xs1, ys1, xs2, ys2, isMark);
        int ellipseWidth = 10;
        int ellipseHeight = 10;
        showEl(g, xs1 - ellipseWidth / 2, ys1 - ellipseHeight / 2, ellipseWidth, ellipseHeight, isMark);
        showEl(g, xs2 - ellipseWidth / 2, ys2 - ellipseHeight / 2, ellipseWidth, ellipseHeight, isMark);
    }
}
