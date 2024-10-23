package drawers;

import java.awt.*;

public class LineShape extends Shape {
    @Override
    public void show(Graphics2D g, boolean isMark) {
        g.setColor(Color.BLACK);
        new StrokeSetter(g, 3, isMark, 10);
        g.drawLine(xs1, ys1, xs2, ys2);
    }
}
