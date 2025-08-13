package com.nhnacademy.cannongame;

public class Circle extends Shape{
    private final double radius;
    public Circle(Point position, double radius){
        super(position);
        this.radius = radius;
    }

    public  Circle(double x, double y, double radius){
        this(new Point(x, y), radius);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public String getShapeType() {
        return "Circle";
    }

    public double getRadius() {
        return radius;
    }
}
