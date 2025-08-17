package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;

public class BreakoutBall extends Ball {
    public BreakoutBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color != null ? color : Color.RED);
        setDx(150);
        setDy(-150);
    }


    @Override
    public void handleCollision(Collidable other) {
        if (other == null) return;

        Bounds ballBounds = this.getBounds();
        Bounds otherBounds = other.getBounds();

        // 공과 다른 객체가 겹쳤는지 확인
        if (!ballBounds.intersects(otherBounds)) return;

        double overlapLeft = ballBounds.getMaxX() - otherBounds.getMinX();
        double overlapRight = otherBounds.getMaxX() - ballBounds.getMinX();
        double overlapTop = ballBounds.getMaxY() - otherBounds.getMinY();
        double overlapBottom = otherBounds.getMaxY() - ballBounds.getMinY();

        double minOverlapX = Math.min(overlapLeft, overlapRight);
        double minOverlapY = Math.min(overlapTop, overlapBottom);

        if (minOverlapX < minOverlapY) {
            setDx(-getDx()); // 좌우 반사
        } else {
            setDy(-getDy()); // 상하 반사
        }
    }

}
