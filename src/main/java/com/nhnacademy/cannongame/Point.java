package com.nhnacademy.cannongame;

//레코드 클래스
public record Point(double x, double y) {

    // Vector2D와의 연산을 위한 메서드 (위치와 속도를 자연스럽게 다루기 위함)
    public Point add(Vector2D vector) {
        return new Point(x + vector.getX(), y + vector.getY());
    }

    public Vector2D subtract(Point other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public double distanceTo(Point other) {
        // TODO: 피타고라스 정리를 사용하여 거리 계산
        double dx = other.x() - this.x;
        double dy = other.y() - this.y;

        // sqrt((x2-x1)² + (y2-y1)²)
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Shape 추상클래스에서 사용하기위해 추가
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}