package com.nhnacademy.cannongame;

// 모든 MovableBall을 관리하는 세계
// 매 프레임마다 물리 업데이트 + 움직이는 객체들의 렌더링 최적화
public class MovableWorld extends World {
    public MovableWorld(double width, double height) {
        super(width, height); // 부모 클래스 생성자 호출
    }

    public void update(double deltaTime) {
        // 모든 공들의 위치 업데이트
        // 각 공이 MovableBall인지 확인하고 move() 호출
        for(Ball ball: getBalls()){
            if (ball instanceof MovableBall movableBall){
                movableBall.move(deltaTime);
            }
        }
    }
}
