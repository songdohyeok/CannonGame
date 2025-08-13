package com.nhnacademy.cannongame;

public class CircleBounds extends Bounds {
    private Point center;
    private final double radius;

    public CircleBounds(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public CircleBounds(double centerX, double centerY, double radius) {
        this.center = new Point(centerX, centerY);
        this.radius = radius;
    }

    @Override
    public double getMinX() {
        return center.getX() - radius;
    }

    @Override
    public double getMinY() {
        return center.getY() - radius;
    }

    @Override
    public double getMaxX() {
        return center.getX() + radius;
    }

    @Override
    public double getMaxY() {
        return center.getY() + radius;
    }

    @Override
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
}
