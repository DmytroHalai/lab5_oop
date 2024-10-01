package Shape;

import java.awt.*;

public abstract class Shape {
    protected int xs1, ys1, xs2, ys2;
    public void set(int x1, int y1, int x2, int y2) {
        xs1 = x1;
        xs2 = x2;
        ys1 = y1;
        ys2 = y2;
    }
    public abstract void show(Graphics g);
}
