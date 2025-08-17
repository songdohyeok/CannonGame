package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


// 7.3 Ball 클래스 - 인터페이스 구현 -> 기존 Ball클래스에 인터페이스 추가
public class Ball implements Paintable, Movable, Collidable{
    private Point center;
    private final double radius;

    private double x, y, dx, dy;
    private Color color;
    private CollisionAction collisionAction;

    // 생성자 - 위치 지정 필수
    public Ball(Point center, double radius) {
        // TODO: null 체크, 유효성 검사
        if (center == null){
            throw new IllegalArgumentException("중심점은 null일 수 없습니다.");
        }
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 양수여야 합니다");
        }
        this.center = center;
        this.radius = radius;
    }

    public Ball(double x, double y, double radius) {
        // TODO: Point 생성하여 다른 생성자 호출
        this(new Point(x, y),radius);
    }

    public Ball(int x, int y, int radius, Color color) {
        this(new Point(x, y), radius);
        this.color = color;
    }

    // Getter 메서드
    public Point getCenter() {
        // TODO: center 반환
        return center;
    }

    public double getRadius() {
        // TODO: radius 반환
        return radius;
    }

    // 공 위치 이동 메서드
    public void moveTo(Point newCenter) {
        // TODO: null 체크 후 center 업데이트
        if(newCenter == null){
            throw new IllegalArgumentException("중심점은 null일 수 없습니다.");
        }
        center = newCenter;
    }

    // 공 면적 계산 메서드
    public double getArea(){
        return Math.PI * radius * radius;
    }

    // contains 메서드
    public boolean contains(Point p) {
        // TODO: center.distanceTo(p) 활용
        //center와 점 p 간의 거리가 반지름보다 작거나 같으면 반지름 크기 이내에(공 내부에) 포함
        return center.distanceTo(p) <= radius;
    }

    // contains 메서드
    public boolean contains(double x, double y) {
        return center.distanceTo(new Point(x,y)) <= radius;
    }

    // 두 공이 겹치는지 확인하는 메서드
    public boolean isColliding(Ball other){
        if (other == null) {
            return false;
        }
        // 두 공의 중심 사이 거리 계산
        double ballDistance = center.distanceTo(other.getCenter());

        // 두 반지름의 합과 비교 (거리가 두 반지름의 합보다 작으면 충돌)
        return ballDistance <= radius + other.getRadius();
    }

    public double getX() {
        return center.x();
    }

    public void setX(double x) {
        center = new Point(x, center.y());
    }

    public double getY() {
        return center.y();
    }

    public void setY(double y) {
        center = new Point(center.x(), y);
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other == null || collisionAction == null) {
            return;
        }
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

            }
            case CUSTOM -> {
                // 필요시 추후 추가
            }
        }
    }

    public void setCollisionAction(CollisionAction collisionAction) {
        this.collisionAction = collisionAction;
    }

    @Override
    public CollisionAction getCollisionAction() {
        return collisionAction;
    }

    @Override
    public Bounds getBounds() {
        return new CircleBounds(center, radius);
    }

    @Override
    public boolean isColliding(Boundable other) {
        if (other == null) return false;
        return getBounds().intersects(other.getBounds());
    }

    @Override
    public void move(double deltaTime) {
        center = new Point(center.x() + dx * deltaTime, center.y() + dy * deltaTime);
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
    public void paint(GraphicsContext gc) {
        if (gc == null) return;
        gc.setFill(color != null ? color : Color.BLACK);
        double topLeftX = center.x() - radius;
        double topLeftY = center.y() - radius;
        double diameter = 2 * radius;
        gc.fillOval(topLeftX, topLeftY, diameter, diameter);
    }
}
