package com.nhnacademy.cannongame;

import java.awt.*;

public class Ball {
    private Point center;
    private final double radius;

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
        return !(ballDistance < radius + other.getRadius());
    }
}
