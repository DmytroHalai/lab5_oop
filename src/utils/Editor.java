package utils;

import java.awt.*;

public abstract class Editor  {
    public abstract void onLBdown(Graphics g, int x, int y);
    public abstract void onLBup(Graphics g);
    public abstract void onMouseMove(Graphics g, int x, int y);
    public abstract void onPaint(Graphics g);
}
