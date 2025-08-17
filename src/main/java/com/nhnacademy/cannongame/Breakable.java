package com.nhnacademy.cannongame;

public interface Breakable {
    void hit(int damage);         // 벽돌이 맞았을 때 처리
    boolean isDestroyed();        // 벽돌이 파괴되었는지 확인
    int getPoints();              // 벽돌 파괴 시 점수
}
