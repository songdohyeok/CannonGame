package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;
import java.util.Vector;

public class MovableBall extends PaintableBall {
    private Vector2D velocity;

    public MovableBall(Point center, double radius) {
        super(center, (int) radius);
        // 속도 초기화
        velocity = new Vector2D(0,0);
    }

    public MovableBall(Point center, double radius, Color color) {
        super(center, (int) radius, color);
        // 속도 초기화
        velocity = new Vector2D(0,0);
    }

    public MovableBall(Point center, double radius, Color color, Vector2D velocity) {
        super(center, (int) radius, color);
        // 속도 초기화
        this.velocity = velocity;
    }

    // 시간 기반 이동
    public void move(double deltaTime) {
        Point currentCenter = getCenter();
        Vector2D displacement = new Vector2D(velocity.getX() * deltaTime, velocity.getY() * deltaTime);
        Point newCenter = currentCenter.add(displacement);
        moveTo(newCenter);
    }

    public void move() {
        // 기본 60 FPS 가정
        // 메서드 활용
        double deltaTime = (double) 1 / 60;
        move(deltaTime);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}