package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class StaticObject implements Collidable, Paintable {
    protected double x, y, width, height;
    protected Color color;
    protected Collidable.CollisionAction collisionAction;

    public StaticObject(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color != null ? color : Color.GRAY;
        this.collisionAction = Collidable.CollisionAction.BOUNCE;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public void setCollisionAction(Collidable.CollisionAction action){
        this.collisionAction = action;
    }

    @Override
    public Collidable.CollisionAction getCollisionAction(){
        return collisionAction;
    }

    @Override
    public Bounds getBounds() {
        return new RectangleBounds(x, y, width, height);
    }

    @Override
    public boolean isColliding(Boundable other) {
        return getBounds().intersects(other.getBounds());
    }

    @Override
    public abstract void handleCollision(Collidable other);

    @Override
    public abstract void paint(GraphicsContext gc);
}
