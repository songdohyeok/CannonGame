package com.nhnacademy.cannongame;
import javafx.scene.paint.Color;

// PaintableBall과 동일한 코드
public class PaintableBox extends Box{

    private Color color;

    PaintableBox(Point position, double width, double height) {
        super(position, width, height);
        this.color = Color.RED;
    }

    PaintableBox(Point position, double width, double height, Color color) {
        super(position, width, height);
        this.color = color;
    }

    public Color getColor() {
        if(color == null){
            color = Color.RED;
        }
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("null 색상 설정은 불가능 합니다.");
        }
        this.color = color;
    }

    public double getX(){
        return getPosition().getX();
    }

    public double getY(){
        return getPosition().getY();
    }
}
