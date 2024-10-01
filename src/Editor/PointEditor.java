package Editor;

import Shape.PointShape;

import java.awt.*;

public class PointEditor extends ShapeEditor{
    private PointShape currentPoint;

    @Override
    public void onLBdown(Graphics g, int x, int y){
        super.onLBdown(g, x, y);
        currentPoint = new PointShape();
        currentPoint.set(x, y, x, y);
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentPoint != null) {
            addShape(currentPoint); // Додаємо еліпс до списку фігур через метод батьківського класу
            currentPoint = null; // Очищуємо поточний еліпс
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
    }
}
