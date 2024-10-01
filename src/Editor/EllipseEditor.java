package Editor;

import Shape.EllipseShape;
import java.awt.*;

public class EllipseEditor extends ShapeEditor {
    private EllipseShape currentEllipse; // Поточний еліпс, що малюється

    @Override
    public void onLBdown(Graphics g, int x, int y) {
        super.onLBdown(g, x, y);
        currentEllipse = new EllipseShape(); // Створюємо новий еліпс
        currentEllipse.set(x, y, x, y); // Встановлюємо початкові координати
    }

    @Override
    public void onLBup(Graphics g) {
        super.onLBup(g);
        if (currentEllipse != null) {
            currentEllipse.set(x1, y1, x2, y2); // Завершуємо налаштування поточного еліпса
            addShape(currentEllipse); // Додаємо еліпс до списку фігур
            currentEllipse = null; // Очищуємо поточний еліпс
//            addShape(currentEllipse); // Додаємо еліпс до списку фігур через метод батьківського класу
//            currentEllipse = null; // Очищуємо поточний еліпс
        }
    }

    @Override
    public void onMouseMove(Graphics g, int x, int y) {
        super.onMouseMove(g, x, y);
        if (currentEllipse != null) {
            currentEllipse.set(x1, y1, x, y); // Оновлюємо координати гумового сліду
        }
    }
}

//package Editor;
//
//import Shape.EllipseShape;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EllipseEditor extends ShapeEditor {
////    private List<EllipseShape> ellipses; // Список для зберігання еліпсів
//    private EllipseShape currentEllipse; // Поточний еліпс, що малюється
//    public EllipseEditor() {
////        this.ellipses = new ArrayList<>();
//    }
//
//    @Override
//    public void onLBdown(Graphics g, int x, int y) {
//        super.onLBdown(g, x, y);
//        currentEllipse = new EllipseShape(); // Створюємо новий еліпс
//        currentEllipse.set(x, y, x, y); // Встановлюємо початкові координати
//    }
//
//    @Override
//    public void onLBup(Graphics g) {
//        super.onLBup(g);
//        if (currentEllipse != null) {
//            shapeList.add(currentEllipse); // Додаємо поточний еліпс до списку
//            currentEllipse = null; // Очищуємо поточний еліпс
//        }
//    }
//
//
//    @Override
//    public void onMouseMove(Graphics g, int x, int y) {
//        super.onMouseMove(g, x, y);
//        if (currentEllipse != null) {
//            currentEllipse.set(x1, y1, x, y); // Оновлюємо координати гумового сліду
//        }
//    }
//
//    @Override
//    public void onPaint(Graphics g) {
//        super.onPaint(g);
//        // Спочатку малюємо всі збережені еліпси
//        for (EllipseShape ellipse : ellipses) {
//            ellipse.show(g); // Малюємо всі збережені еліпси
//        }
//    }
//}
