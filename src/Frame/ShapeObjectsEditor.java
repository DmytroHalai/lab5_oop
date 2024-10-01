package Frame;

import Editor.*;

import java.awt.*;

public class ShapeObjectsEditor {
    private ShapeEditor currentShape;

    public ShapeObjectsEditor(){

    }
    public void startPointEditor(){
        currentShape = new PointEditor();
    }

    public void startLineEditor(){
        currentShape = new LineEditor();
    }

    public void startRectEditor(){
        currentShape = new RectEditor();
    }

    public void startEllipseEditor(){
        currentShape = new EllipseEditor();
    }

    public void onLBdown(Graphics g){

    }

    public void onLBup(Graphics g){

    }

    public void onMouseMove(Graphics g){

    }

    public void onPaint(Graphics g){

    }

    public void onInitMenuPopup(Graphics g){

    }

}
