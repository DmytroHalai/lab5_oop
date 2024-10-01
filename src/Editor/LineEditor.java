package Editor;

import Shape.LineShape;

import java.awt.*;

public class LineEditor extends ShapeEditor{
    private LineShape currentLine;

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentLine = new LineShape(); // Створюємо новий еліпс
        currentLine.set(x, y, x, y); // Встановлюємо початкові координати
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentLine != null) {
            addShape(currentLine); // Додаємо еліпс до списку фігур через метод батьківського класу
            currentLine = null; // Очищуємо поточний еліпс
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentLine != null) {
            currentLine.set(x1, y1, x, y); // Оновлюємо координати гумового сліду
        }
    }
}
