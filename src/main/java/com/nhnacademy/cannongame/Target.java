package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Target {
    private final double x, y, width, height;
    private double health;
    private boolean destroyed = false;
    private final int points;

    public Target(double x, double y, double width, double height, int points, double durability) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.points = points;
        this.health = durability;
    }

    public void takeDamage(double damage) {
        if (destroyed) return;
        health -= damage;
        if (health <= 0) destroyed = true;
    }

    public void paint(GraphicsContext gc) {
        if (destroyed) return;

        gc.setFill(Color.LIGHTGREEN); // 초록색으로 표시
        gc.fillRect(x, y, width, height);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1); // 선 굵기 고정
        gc.strokeRect(x, y, width, height);
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }

    public int getPoints() {
        return points;
    }
}
