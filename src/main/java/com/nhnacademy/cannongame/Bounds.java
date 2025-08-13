package com.nhnacademy.cannongame;

public abstract class Bounds {

    public abstract double getMinX();
    public abstract double getMinY();
    public abstract double getMaxX();
    public abstract double getMaxY();

    public abstract void setCenter(Point newCenter);
    public abstract double getArea();


    //  너비 계산 (maxX - minX)
    public double getWidth(){
        return getMaxX() - getMinX();
    }

    //  높이 계산 (maxY - minY)
    public double getHeight(){
        return getMaxY() - getMinY();
    }

    // 중심의 x 좌표
    public double getCenterX(){
        return (getMinX() + getMaxX()) / 2;
    }

    // 중심의 y 좌표
    public double getCenterY(){
        return (getMinY() + getMaxY()) / 2;
    }

    // 점 포함 여부
    public boolean contains(Point point){
        return point.getX() >= getMinX() && point.getX() <= getMaxX()
                && point.getY() >= getMinY() && point.getY() <= getMaxY();
    }

    // 다른 Bounds 포함 여부
    public boolean contains(Bounds other){
        return this.contains(new Point(other.getMinX(), other.getMinY())) &&
                this.contains(new Point(other.getMaxX(), other.getMaxY()));
    }

    public boolean contains(double x, double y) {
        return contains(new Point(x, y));
    }

    // 교차 여부
    public boolean intersects(Bounds other){
        return !(other.getMinX() > this.getMaxX() ||
                other.getMaxX() < this.getMinX() ||
                other.getMinY() > this.getMaxY() ||
                other.getMaxY() < this.getMinY());
    }
}
