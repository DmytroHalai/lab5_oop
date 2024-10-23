package drawers;

import java.awt.*;

public interface LineDrawer {
    default void showLine(Graphics2D g, int x, int y, int x2, int y2, boolean isMark){
        new LineShape().show(g, isMark);
    }
}