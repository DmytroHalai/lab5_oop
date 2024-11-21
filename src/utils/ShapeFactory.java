package utils;

import drawers.*;

public class ShapeFactory {
    public static Shape createShape(String type) {
        return switch (type) {
            case "Ellipse" -> new EllipseShape();
            case "Rectangle" -> new RectShape();
            case "LineOO" -> new LineOOShape();
            case "Line" -> new LineShape();
            case "Point" -> new PointShape();
            case "Cube" -> new CubeShape();
            default -> throw new IllegalArgumentException("Unknown shape type: " + type);
        };
    }
}
