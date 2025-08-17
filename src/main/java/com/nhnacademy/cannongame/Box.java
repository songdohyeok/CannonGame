package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Box implements Paintable, Collidable{
    private Point position; // 각 모서리 좌표
    private double height, width, x, y;
    private Color color;
    private CollisionAction collisionAction;

    Box(Point position, double width, double height){
        if(position == null || height <= 0 || width <= 0){
            throw new IllegalArgumentException();
        }
        this.position = position;
        this.width = width;
        this.height = height;
    }

    Box(double x, double y, double width, double height) {
        this.position = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    public Box(double x, double y, double width, double height, Color color) {
        this.position = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = Color.BLUE;
    }


    public Point getCenter() {
        double centerX = getPosition().getX() + getWidth() / 2.0;
        double centerY = getPosition().getY() + getHeight() / 2.0;
        return new Point(centerX, centerY);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        if(position == null){
            return;
        }
        this.position = position;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {

        this.height = height;
    }

    public void moveTo(Point newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("위치 좌표는 null일 수 없습니다.");
        }
        this.position = newPosition;
    }

    public boolean contains(Point point) {
        return point.x() >= position.getX()  && point.x() <= position.getX() + width
                && point.y() >= position.getY() && point.y() <= position.getY() + height;
    }

    @Override
    public RectangleBounds getBounds(){
        return new RectangleBounds(position.getX(), position.getY(), width, height);
    }

    @Override
    public boolean isColliding(Boundable other) {
        if(other == null) return false;
        return getBounds().intersects(other.getBounds());
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other == null || collisionAction == null) return;
        switch (collisionAction) {
            case BOUNCE -> {
                if (other instanceof Movable movable) {
                    Bounds otherBounds = other.getBounds();
                    Bounds boxBounds = this.getBounds();

                    double overlapLeft = otherBounds.getMaxX() - boxBounds.getMinX();
                    double overlapRight = boxBounds.getMaxX() - otherBounds.getMinX();
                    double overlapTop = otherBounds.getMaxY() - boxBounds.getMinY();
                    double overlapBottom = boxBounds.getMaxY() - otherBounds.getMinY();

                    double minOverlapX = Math.min(overlapLeft, overlapRight);
                    double minOverlapY = Math.min(overlapTop, overlapBottom);

                    if (minOverlapX < minOverlapY) {
                        movable.setDx(-movable.getDx()); // 좌우 반사
                    } else {
                        movable.setDy(-movable.getDy()); // 상하 반사
                    }
                }
            }
            case DESTROY -> {
                this.collisionAction = null;
            }
            case STOP -> {
                if (other instanceof Movable movable) {
                    movable.setDx(0);
                    movable.setDy(0);
                }
            }
            case PASS -> {

            }
            case CUSTOM -> {
                // 필요시 추후 추가
            }
        }
    }

    @Override
    public CollisionAction getCollisionAction() {
        return collisionAction;
    }

    public void setCollisionAction(CollisionAction action) {
        this.collisionAction = action;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(gc == null) return;
        gc.setFill(color != null ? color : Color.GRAY);
        gc.fillRect(position.getX(), position.getY(), width, height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(position.getX(), position.getY(), width, height);
    }
}
