package utils;

import java.awt.*;

public interface Editor  {
void onLBdown(Graphics2D g, int x, int y);
void onLBup(Graphics2D g);
void onMouseMove(Graphics2D g, int x, int y);
void onPaint(Graphics2D g);
}
