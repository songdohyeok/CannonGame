package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;

public class BreakoutPaddle extends Box {
    private static final double DEFAULT_WIDTH = 100;
    private static final double DEFAULT_HEIGHT = 20;
    private static final Color DEFAULT_COLOR = Color.BLUE;

    public BreakoutPaddle(double x, double y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
    }

    // 마우스 이동 목표 위치 지정
    public void setTargetX(double x, double worldWidth) {
        if (x < 0) x = 0;
        if (x + getWidth() > worldWidth) x = worldWidth - getWidth();
        setPosition(new Point(x, getPosition().getY()));
    }

    // 공 반사 각도 계산
    public void reflectBall(Ball ball) {
        if (ball == null) return;

        // 패들에 맞은 위치 0~1
        double hitPos = (ball.getX() - getPosition().getX()) / getWidth();
        // -60° ~ +60° 반사
        double angle = (hitPos - 0.5) * Math.PI / 3;

        double speed = Math.sqrt(ball.getDx() * ball.getDx() + ball.getDy() * ball.getDy());
        ball.setDx(speed * Math.sin(angle));
        ball.setDy(-Math.abs(speed * Math.cos(angle))); // 항상 위로 반사
    }

    // Ball과 충돌 여부 체크
    public boolean isColliding(Ball ball) {
        if (ball == null) return false;

        double paddleLeft = getPosition().getX();
        double paddleRight = paddleLeft + getWidth();
        double paddleTop = getPosition().getY();
        double paddleBottom = paddleTop + getHeight();

        double ballLeft = ball.getX() - ball.getRadius();
        double ballRight = ball.getX() + ball.getRadius();
        double ballTop = ball.getY() - ball.getRadius();
        double ballBottom = ball.getY() + ball.getRadius();

        return paddleLeft < ballRight &&
                paddleRight > ballLeft &&
                paddleTop < ballBottom &&
                paddleBottom > ballTop;
    }

    // Ball 충돌 처리
    public void handleCollision(Ball ball) {
        if (ball != null && isColliding(ball)) {
            reflectBall(ball);
        }
    }

    // 좌표 getter
    public double getX() { return getPosition().getX(); }
    public double getY() { return getPosition().getY(); }
}
