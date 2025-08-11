package com.nhnacademy.cannongame;

public class Ball {
    private double x;
    private double y;
    private double radius;

    public Ball(double x, double y, double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }
}
