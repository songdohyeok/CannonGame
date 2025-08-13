package com.nhnacademy.cannongame;

// 5.4 Ball 클래스를 추상 클래스로 리팩터링?
public abstract class AbstractBall {
    private Point center;
    private final double radius;
    private Bounds bounds;
    private Vector2D velocity = new Vector2D(0,0);

    protected AbstractBall(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.bounds = new CircleBounds(center, radius);
    }

    public AbstractBall(double centerX, double centerY, double radius) {
        this(new Point(centerX, centerY), radius);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
        updateBounds();
    }

    public double getRadius() {
        return radius;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
        updateBounds();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Vector2D(dx, dy);
    }

    public double getDx() {
        return velocity.getX();
    }

    public double getDy() {
        return velocity.getY();
    }

    public double getX() {
        return center.getX();
    }

    public double getY() {
        return center.getY();
    }

    public void setX(double x) {
        setCenter(new Point(x, center.getY()));
    }

    public void setY(double y) {
        setCenter(new Point(center.getX(), y));
    }

    // 업데이트 프로세스 정의 (Template Method 패턴)
    public final void update(double deltaTime) {
        beforeUpdate();
        performUpdate(deltaTime);  // 추상 메서드
        afterUpdate();
        updateBounds();
    }

    // 전처리 (기본 구현 비어있음)
    protected void beforeUpdate() {}

    // 핵심 로직 (추상 메서드) -> Ball을 상속받는 모든 하위 클래스에서 반드시 구현
    protected abstract void performUpdate(double deltaTime);

    // 후처리 (기본 구현 비어있음)
    protected void afterUpdate() {}

    // 경계 업데이트
    protected void updateBounds() {
        if (bounds != null) {
            bounds.setCenter(center);
        }
    }
}
