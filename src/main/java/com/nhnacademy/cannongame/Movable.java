package com.nhnacademy.cannongame;

// 움직일 수 있는 객체를 위한 인터페이스
public interface Movable {
    void move(double deltaTime);
    double getDx();
    double getDy();
    void setDx(double dx);
    void setDy(double dy);
}
