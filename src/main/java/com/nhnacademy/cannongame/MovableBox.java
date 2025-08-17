package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;

// MovableBall 기능을 그대로 갖다 붙임
public class MovableBox extends Box {
    private Vector2D velocity;

    MovableBox(Point position, double width, double height) {
        super(position, width, height);
        velocity = new Vector2D(0 ,0);
    }


    public MovableBox(int x, int y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    public MovableBox(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        this.velocity = new Vector2D(0, 0);
    }


    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        if (velocity != null) {
            this.velocity = velocity;
        }
    }

    public void move(double deltaTime) {
        Point topLeft = getPosition();
        double newX = topLeft.getX() + velocity.getX() * deltaTime;
        double newY = topLeft.getY() + velocity.getY() * deltaTime;
        moveTo(new Point(newX, newY));
    }

}
