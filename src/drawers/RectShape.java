package drawers;

import java.awt.*;

public class RectShape extends Shape {
    @Override
    public void show(Graphics2D g, boolean isMark, boolean isHighlight) {
        int x = Math.min(xs1, xs2);
        int y = Math.min(ys1, ys2);
        int width = Math.abs(xs2 - xs1);
        int height = Math.abs(ys2 - ys1);

        g.setColor(isHighlight ? Color.RED : Color.BLACK);
        new StrokeSetter(g, 3, isMark, 10);
        g.drawRect(x, y, width, height);
    }

    @Override
    public String getType() {
        return "Rectangle";
    }

}
