package drawers;

import java.awt.*;

public class RectShape extends Shape {
    @Override
    public void show(Graphics g, boolean isMark) {
        int x;
        int y;
        int width;
        int height;

        if (xs1 < xs2) {
            x = 2 * xs1 - xs2;
            width = Math.abs(xs2 - x);
        } else {
            x = xs2;
            width = 2 * (xs1 - x);
        }

        if (ys1 < ys2) {
            y = 2 * ys1 - ys2;
            height = Math.abs(ys2 - y);
        } else {
            y = ys2;
            height = 2 * (ys1 - y);
        }

        if (!isMark) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
        }

        if (isMark) {
            g.setColor(Color.RED);
        } else g.setColor(Color.BLACK);

        g.drawRect(x, y, width, height);
    }

}
