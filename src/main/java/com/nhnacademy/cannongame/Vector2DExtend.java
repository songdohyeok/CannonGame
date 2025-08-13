package com.nhnacademy.cannongame;

// 2차원 벡터 구현
public class Vector2DExtend extends Vector {
    private double x;
    private double y;

    public Vector2DExtend(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Factory Method
    @Override
    public Vector createNew() {
        return new Vector2DExtend(0, 0);
    }

    @Override
    public double get(int index) {
        if (index == 0) return x;
        if (index == 1) return y;
        throw new IndexOutOfBoundsException("Vector2D index must be 0 or 1");
    }

    @Override
    public void set(int index, double value) {
        if (index == 0) x = value;
        else if (index == 1) y = value;
        else throw new IndexOutOfBoundsException("Vector2D index must be 0 or 1");
    }

    @Override
    public int dimension() {
        return 2;
    }

    @Override
    public Vector2DExtend normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vector2DExtend(0, 0);
        return new Vector2DExtend(getX() / mag, getY() / mag);
    }


    // 추가 편의 메서드
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    // 벡터 각도 (라디안)
    public double angle() {
        return Math.atan2(y, x);
    }

    // 벡터 연산
    public Vector2DExtend add(Vector2DExtend v) {
        return new Vector2DExtend(this.x + v.x, this.y + v.y);
    }

    public Vector2DExtend subtract(Vector2DExtend v) {
        return new Vector2DExtend(this.x - v.x, this.y - v.y);
    }

    public Vector2DExtend multiply(double scalar) {
        return new Vector2DExtend(this.x * scalar, this.y * scalar);
    }

    public Vector2DExtend divide(double scalar) {
        return new Vector2DExtend(this.x / scalar, this.y / scalar);
    }

    public double dot(Vector2DExtend v) {
        return this.x * v.x + this.y * v.y;
    }

    public double cross(Vector2DExtend v) {
        return this.x * v.y - this.y * v.x;
    }

    public double distance(Vector2DExtend v) {
        return this.subtract(v).magnitude();
    }

    public Vector2DExtend project(Vector2DExtend v) {
        double scalar = this.dot(v) / v.dot(v);
        return v.multiply(scalar);
    }

    public Vector2DExtend rotate(double radians) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return new Vector2DExtend(x * cos - y * sin, x * sin + y * cos);
    }

    @Override
    public String toString() {
        return "Vector2DExtend{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector2DExtend)) return false;
        Vector2DExtend v = (Vector2DExtend) o;
        return Double.compare(v.x, x) == 0 && Double.compare(v.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }

    // 영벡터, 단위벡터 생성 편의 메서드
    public static Vector2DExtend zero() { return new Vector2DExtend(0, 0); }
    public static Vector2DExtend unitX() { return new Vector2DExtend(1, 0); }
    public static Vector2DExtend unitY() { return new Vector2DExtend(0, 1); }

    // 극좌표 생성
    public static Vector2DExtend fromPolar(double magnitude, double angle) {
        return new Vector2DExtend(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }
}
