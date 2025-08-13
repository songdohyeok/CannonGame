package com.nhnacademy.cannongame;

public class Rectangle extends Shape{
    private final double width;
    private final double height;

    public Rectangle(Point position, double width, double height){
        super(position);
        this.width = width;
        this.height = height;
    }

    public Rectangle(double x, double y, double width, double height){
        this(new Point(x, y), width, height);
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (height + width);
    }

    @Override
    public String getShapeType() {
        return "Rectangle";
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
