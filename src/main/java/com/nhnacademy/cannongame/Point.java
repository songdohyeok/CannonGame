package com.nhnacademy.cannongame;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Point other) {
        // TODO: 피타고라스 정리를 사용하여 거리 계산
        double dx = other.getX() - this.x;
        double dy = other.getY() - this.y;

        // sqrt((x2-x1)² + (y2-y1)²)
        return Math.sqrt(dx * dx + dy * dy);
    }
}