package com.nhnacademy.cannongame;

// Boundable을 확장
public interface Collidable extends Boundable {
    // 추가 메서드
    void handleCollision(Collidable other);
    CollisionAction getCollisionAction();

    enum CollisionAction{
        BOUNCE, DESTROY, STOP, PASS, CUSTOM;
    };
}
