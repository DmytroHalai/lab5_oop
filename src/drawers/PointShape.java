package drawers;

import java.awt.*;

public class PointShape extends Shape {
    @Override
    public void show(Graphics2D g, boolean isMark) {
        g.setStroke(new BasicStroke(3));
        g.fillOval(xs1, ys1, 8, 8);
    }
}
