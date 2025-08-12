package com.nhnacademy.cannongame;

// 불변 클래스
public final class Vector2D {
    private final double x;
    private final double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        x = 0;
        y = 0;
    }

    // 모든 연산은 새 Vector2D 객체 반환 (immutable)
    // normalize에서 magnitude가 0일 때 처리 필요
    // 내적 = x₁×x₂ + y₁×y₂

    public double dot(Vector2D other){
        return this.x * other.x + this.y * other.y;
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(double scalar){
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // 벡터의 크기 (√(x² + y²))
    public double magnitude(){
        return Math.hypot(x, y);
    }

    // 벡터의 정규화 (크기를 1로)
    public Vector2D normalize(){
        double mag = magnitude();
        if(mag == 0){
            return new Vector2D(0, 0);
        }
        return new Vector2D(x / mag, y / mag);
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
