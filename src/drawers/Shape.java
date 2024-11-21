package drawers;

import java.awt.*;

public abstract class Shape {
    public int xs1;

    public int ys1;
    protected int xs2;
    protected int ys2;

    public void set(int x1, int y1, int x2, int y2) {
        xs1 = x1;
        xs2 = x2;
        ys1 = y1;
        ys2 = y2;
    }

    public abstract void show(Graphics2D g, boolean isMark, boolean isHighLighted);

    public int getXs1() {
        return xs1;
    }

    public int getYs1() {
        return ys1;
    }

    public int getXs2() {
        return xs2;
    }

    public int getYs2() {
        return ys2;
    }

    public void setXs1(int xs1) {
        this.xs1 = xs1;
    }

    public void setYs1(int ys1) {
        this.ys1 = ys1;
    }

    public void setXs2(int xs2) {
        this.xs2 = xs2;
    }

    public void setYs2(int ys2) {
        this.ys2 = ys2;
    }


    public abstract String getType();
}
