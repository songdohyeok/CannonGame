package com.nhnacademy.cannongame;

public class RectangleBounds extends Bounds {
    private double minX;
    private double minY;
    private final double width;
    private final double height;

    public RectangleBounds(double minX, double minY, double width, double height){
        if(width <= 0 || height <= 0){
            throw new IllegalArgumentException("크기가 유효하지 않습니다.");
        }
        this.minX = minX;
        this.minY = minY;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMaxX() {
        return minX + width;
    }

    @Override
    public double getMaxY() {
        return minY + height;
    }

    @Override
    public void setCenter(Point newCenter) {
        this.minX = newCenter.getX() - width / 2;
        this.minY = newCenter.getY() - height / 2;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public double getX() {
        return minX;
    }
    public double getY() {
        return minY;
    }
}
