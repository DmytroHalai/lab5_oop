package Shape;

public abstract class Shape {
    protected long xs1, ys1, xs2, ys2;
    public void Set (long x1, long y1, long x2, long y2) {
        xs1 = x1;
        xs2 = x2;
        ys1 = y1;
        ys2 = y2;
    }
    public abstract void Show();

}
