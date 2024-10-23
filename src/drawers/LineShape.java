package drawers;

import java.awt.*;

public class LineShape extends Shape {
    @Override
    public void show(Graphics2D g, boolean isMark) {
        if (isMark) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawLine(xs1, ys1, xs2, ys2);
    }
}
