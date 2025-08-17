package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;

public class PaintableMovableBox extends MovableBox{
    private Color color;

    PaintableMovableBox(Point position, double width, double height, Color color) {
        super(position, width, height);
        this.color = color;
    }

    PaintableMovableBox(Point position, int width, int height, Color color) {
        super(position, width, height);
        this.color = color;
    }

    PaintableMovableBox(int x, int y, int width, int height, Color color) {
        super(new Point(x, y), width, height);
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

    public double getDx(){
        return getVelocity().getX();
    }

    public double getDy(){
        return getVelocity().getY();
    }
}
