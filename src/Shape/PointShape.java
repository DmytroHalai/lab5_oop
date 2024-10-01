package Shape;

import java.awt.*;

public class PointShape extends Shape{
    @Override
    public void show(Graphics g) {
        g.fillOval(xs1, ys1, 5, 5);
    }
}
