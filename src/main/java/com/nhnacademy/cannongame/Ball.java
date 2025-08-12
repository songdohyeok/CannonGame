package com.nhnacademy.cannongame;

import java.awt.*;

public class Ball {
    private Point center;
    private double radius;

    // 생성자 - 위치 지정 필수
    public Ball(Point center, double radius) {
        // TODO: null 체크, 유효성 검사
        if (center == null){
            throw new IllegalArgumentException("위치가 null입니다.");
        }

        this.center = center;
        this.radius = radius;
    }

    public Ball(double x, double y, double radius) {
        // TODO: Point 생성하여 다른 생성자 호출
        this(new Point((int) x, (int) y),radius);
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

    // 위치 이동 메서드
    public void moveTo(Point newCenter) {
        // TODO: null 체크 후 center 업데이트
        if(newCenter == null){
            throw new IllegalArgumentException("위치가 null입니다.");
        }
        center = newCenter;
    }

    // contains 메서드
    public boolean contains(Point p) {
        // TODO: center.distanceTo(p) 활용
        return center.distanceTo(p) <= radius; //center와 점p 간의 거리가 반지름보다 작거나 같으면 반지름 크기 이내에(공 내부에) 있는것
    }

    // contains 메서드
    public boolean contains(double x, double y) {
        return center.distanceTo(new Point(x,y)) <= radius;
    }
}
