package Shape;

import java.awt.*;

public class LineShape extends  Shape{
    @Override
    public void show(Graphics g) {
        g.drawLine(xs1, ys1, xs2, ys2);
    }
}
