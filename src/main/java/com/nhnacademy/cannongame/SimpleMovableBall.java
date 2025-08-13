package com.nhnacademy.cannongame;

public class SimpleMovableBall extends AbstractBall{


    protected SimpleMovableBall(Point center, double radius) {
        super(center, radius);
    }

    @Override
    protected void performUpdate(double deltaTime) {
        //현재 위치 + 속도(Vector2D) × 시간 = 새 위치
        if(getVelocity() == null){
            return;
        }

        Point old = getCenter();
        double newX = old.getX() + getDx() * deltaTime;
        double newY = old.getY() + getDy() * deltaTime;

        setCenter(new Point(newX, newY));
    }

}
