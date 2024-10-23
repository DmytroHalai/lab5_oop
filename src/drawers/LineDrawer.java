package drawers;

import java.awt.*;

public interface LineDrawer {
    void showLine(Graphics2D g, int x, int y, int width, int height, boolean isMark);
}
