package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements Paintable, Movable, Collidable {
    private Point p1, p2, p3;
    private double dx, dy;
    private Color color;
    private CollisionAction collisionAction;

    public Triangle(Point p1, Point p2, Point p3, Color color) {
        if (p1 == null || p2 == null || p3 == null) {
            throw new IllegalArgumentException("삼각형 꼭짓점은 null일 수 없습니다.");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = (color != null) ? color : Color.GREEN;
    }

    public Triangle(double centerX, double centerY, double size) {
        // 정삼각형 기준으로 꼭짓점 계산
        double height = Math.sqrt(3) / 2 * size; // 높이
        this.p1 = new Point(centerX, centerY - 2.0 / 3.0 * height);          // 꼭대기
        this.p2 = new Point(centerX - size / 2, centerY + 1.0 / 3.0 * height); // 좌하
        this.p3 = new Point(centerX + size / 2, centerY + 1.0 / 3.0 * height); // 우하
        this.color = Color.GREEN; // 기본 색상
    }

    @Override
    public void paint(GraphicsContext gc) {
        if (gc == null) return;
        double[] xPoints = {p1.x(), p2.x(), p3.x()};
        double[] yPoints = {p1.y(), p2.y(), p3.y()};
        gc.setFill(color);
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    @Override
    public void move(double deltaTime) {
        p1 = new Point(p1.x() + dx * deltaTime, p1.y() + dy * deltaTime);
        p2 = new Point(p2.x() + dx * deltaTime, p2.y() + dy * deltaTime);
        p3 = new Point(p3.x() + dx * deltaTime, p3.y() + dy * deltaTime);
    }

    @Override
    public double getDx() {
        return dx;
    }

    @Override
    public double getDy() {
        return dy;
    }

    @Override
    public void setDx(double dx) {
        this.dx = dx;
    }

    @Override
    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public Bounds getBounds() {
        double minX = Math.min(p1.x(), Math.min(p2.x(), p3.x()));
        double minY = Math.min(p1.y(), Math.min(p2.y(), p3.y()));
        double maxX = Math.max(p1.x(), Math.max(p2.x(), p3.x()));
        double maxY = Math.max(p1.y(), Math.max(p2.y(), p3.y()));
        return new RectangleBounds(minX, minY, maxX - minX, maxY - minY);
    }

    @Override
    public boolean isColliding(Boundable other) {
        if (other == null) return false;
        return getBounds().intersects(other.getBounds());
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other == null || collisionAction == null) return;

        switch (collisionAction) {
            case BOUNCE -> {
                dx = -dx;
                dy = -dy;
            }
            case DESTROY -> {
                this.collisionAction = null;
            }
            case STOP -> {
                dx = 0;
                dy = 0;
            }
            case PASS -> {
                // 아무 처리 없음
            }
            case CUSTOM -> {
                // 필요시 확장
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
}

