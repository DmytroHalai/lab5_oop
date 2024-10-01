package Editor;

import Shape.EllipseShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EllipseEditor extends ShapeEditor {
    private List<EllipseShape> ellipses; // Список для зберігання еліпсів
    private EllipseShape currentEllipse; // Поточний еліпс, що малюється
    public EllipseEditor() {
        this.ellipses = new ArrayList<>();
    }

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentEllipse = new EllipseShape(); // Створюємо новий еліпс
        currentEllipse.Set(x, y, x, y); // Встановлюємо початкові координати
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentEllipse != null) {
            ellipses.add(currentEllipse); // Додаємо поточний еліпс до списку
            currentEllipse = null; // Очищуємо поточний еліпс
        }
    }


    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentEllipse != null) {
            currentEllipse.Set(x1, y1, x, y); // Оновлюємо координати гумового сліду
        }
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        // Спочатку малюємо всі збережені еліпси
        for (EllipseShape ellipse : ellipses) {
            ellipse.Show(g); // Малюємо всі збережені еліпси
        }
        // Тепер малюємо гумовий слід поверх
//        if (currentEllipse != null) {
//            g.setColor(Color.BLACK); // Встановлюємо колір гумового сліду
//            g.fillOval((int) currentEllipse.xs1, (int) currentEllipse.ys1,
//                    (int) Math.abs(currentEllipse.xs2 - currentEllipse.xs1),
//                    (int) Math.abs(currentEllipse.ys2 - currentEllipse.ys1));
//        }
    }
}
