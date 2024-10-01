package Editor;

import Shape.RectShape;
import java.awt.*;

public class RectEditor extends ShapeEditor{
    private RectShape currentRect; // Поточний еліпс, що малюється

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentRect = new RectShape(); // Створюємо новий еліпс
        currentRect.set(x, y, x, y); // Встановлюємо початкові координати
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentRect != null) {
            currentRect.set(x1, y1, x2, y2); // Завершуємо налаштування поточного прямокутника
            addShape(currentRect); // Додаємо прямокутник до списку фігур
            currentRect = null; // Очищуємо поточний еліпс
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentRect != null) {
            currentRect.set(x1, y1, x, y); // Оновлюємо координати гумового сліду
        }
    }
}
