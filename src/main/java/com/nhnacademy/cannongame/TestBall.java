package com.nhnacademy.cannongame;

// AbstractBall을 상속받는 테스트용 구현체
class TestBall extends AbstractBall {
    private boolean updateCalled = false;
    private double lastDeltaTime;

    public TestBall(Point center, double radius) {
        super(center.getX(), center.getY(), radius);
    }

    @Override
    protected void performUpdate(double deltaTime) {
        this.updateCalled = true;
        this.lastDeltaTime = deltaTime;
        // 간단한 이동 로직
        setX(getX() + getDx() * deltaTime);
        setY(getY() + getDy() * deltaTime);
    }

    public boolean isUpdateCalled() {
        return updateCalled;
    }

    public double getLastDeltaTime() {
        return lastDeltaTime;
    }

    public void resetUpdateFlag() {
        updateCalled = false;
    }
}
