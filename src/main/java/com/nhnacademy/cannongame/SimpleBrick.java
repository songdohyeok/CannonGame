package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimpleBrick extends StaticObject implements Breakable {
    private int hitPoints;
    private final int points;
    private final BrickType type;

    public SimpleBrick(double x, double y, double width, double height, BrickType type) {
        super(x, y, width, height, Color.LIGHTGRAY);
        this.type = type;
        this.hitPoints = 1;
        this.points = 10;
    }

    public BrickType getType() {
        return type;
    }

    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    @Override
    public void hit(int damage) {
        hitPoints -= damage;
        if (hitPoints < 0) hitPoints = 0;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if (gc == null || isDestroyed()) return;
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other instanceof Ball ball && !isDestroyed()) {
            if (getBounds().intersects(ball.getBounds())) {  // 충돌 판정
                hit(1);
            }
        }
    }
}

