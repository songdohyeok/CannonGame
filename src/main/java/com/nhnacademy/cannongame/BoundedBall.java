package com.nhnacademy.cannongame;

import javafx.scene.paint.Color;

// MovableBall을 상속받아 경계 충돌 처리
public class BoundedBall extends MovableBall{

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    // 생성자에서 경계 초기화 (경계 없음 상태)
    public BoundedBall(Point center, double radius, Color color) {
        super(center, radius, color);
        // 초기값: 경계가 설정되지 않은 상태를 나타냄
        this.minX = Double.MIN_VALUE;
        this.minY = Double.MIN_VALUE;
        this.maxX = Double.MAX_VALUE;
        this.maxY = Double.MAX_VALUE;
    }

    public BoundedBall(Point center, double radius) {
        super(center, radius);
        this.minX = Double.MIN_VALUE;
        this.minY = Double.MIN_VALUE;
        this.maxX = Double.MAX_VALUE;
        this.maxY = Double.MAX_VALUE;
    }

    // 경계 설정 시 공의 중심이 이동 가능한 범위
    public void setBounds(double minX, double minY, double maxX, double maxY) {
        this.minX = minX + getRadius();
        this.maxX = maxX - getRadius();
        this.minY = minY + getRadius();
        this.maxY = maxY - getRadius();
    }

    // move 메서드에서 경계 충돌 처리
    @Override
    public void move(double deltaTime) {
        // 다음 위치 계산
        Point nextPoint = getCenter().add(getVelocity().multiply(deltaTime));

        // 경계가 설정된 경우에만 충돌 검사
        // Double.MIN_VALUE와 Double.MAX_VALUE는 경계가 없음을 의미
        // 화면 왼쪽 위가 0,0 이고 아래로 갈수록 y값이 커짐 x 0 ~ 800 y 0 ~ 600
        setBounds(0,0,800,600);

        if (minX > Double.MIN_VALUE && maxX < Double.MAX_VALUE) {
            // x가 최소 x값인 0 + 반지름 보다 작은 상태
            if (nextPoint.x() <= minX) {
                // 1. 속도 반전
                setVelocity(new Vector2D(-getVelocity().getX(), getVelocity().getY()));
                // 2. 위치 보정 (경계 안쪽으로)
                nextPoint = new Point(minX, nextPoint.y());
            }
            else if (nextPoint.x() >= maxX){
                setVelocity(new Vector2D(-getVelocity().getX(), getVelocity().getY()));
                nextPoint = new Point(maxX, nextPoint.y());
            }
        }

        if (minY > Double.MIN_VALUE && maxY < Double.MAX_VALUE) {
            if (nextPoint.y() <= minY) {
                setVelocity(new Vector2D(getVelocity().getX(), -getVelocity().getY()));
                nextPoint = new Point(nextPoint.x(), minY);

            }
            else if (nextPoint.y() >= maxY){
                setVelocity(new Vector2D(getVelocity().getX(), -getVelocity().getY()));
                nextPoint = new Point(nextPoint.x(), maxY);
            }
        }
        // 부모 클래스의 move 호출
        super.move(deltaTime);
    }

    //Lab 4-2: 다양한 충돌 시나리오
    //
    //다양한 충돌 상황 구현:
    //
    //벽과의 다중 충돌
    //코너 충돌 처리
    //연속적인 공 간 충돌
    //Lab 4-3: 충돌 시뮬레이션
    //
    //여러 공의 충돌 시뮬레이션:
    //
    //10개의 공을 랜덤 위치에 생성
    //다양한 크기와 속도
    //충돌 효과 시각화 (색상 변화, 사운드 등)
    //Lab 4-4: 고급 충돌 처리
    //
    //특수한 충돌 상황 처리:
    //
    //코너 충돌
    //동시 다중 충돌
    //고속 충돌 (터널링 방지)

}
