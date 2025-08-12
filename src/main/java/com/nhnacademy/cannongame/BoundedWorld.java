package com.nhnacademy.cannongame;

//MovableWorld를 상속받아 충돌 처리 기능을 통합한 클래스
public class BoundedWorld extends MovableWorld{
    public BoundedWorld(double width, double height) {
        super(width, height);
    }

    //재정의 메서드
    //add(Ball ball): 공 추가될때 영역 설정
    @Override
    public void add(Ball ball){
        if(ball == null){
            throw new IllegalArgumentException("공이 null일 수 없습니다.");
        }
        super.add(ball);
    }

    //메서드
    //update(double deltaTime): 매 프레임 업데이트
    //모든 공 이동
    //벽과의 충돌 검사 및 처리
    //공 간의 충돌 검사 및 처리


    //구현 순서
    //1.이동 단계: 모든 MovableBall의 move() 호출
    @Override
    public void update(double deltaTime){
        for(Ball ball: getBalls()){
            if (ball instanceof MovableBall movableBall){
                movableBall.move(deltaTime);
            }
        }

        //2.벽 충돌 단계:
        //BoundedBall인지 확인
        //CollisionDetector.checkWallCollision() 호출
        //충돌 시 resolveWallCollision() 호출
        for (Ball ball : getBalls()) {
            if (ball instanceof BoundedBall boundedBall) {
                // 충돌 확인
                CollisionDetector.WallCollision collision =
                        CollisionDetector.checkWallCollision(boundedBall, 0, 0, getWidth(), getHeight());

                // 충돌시
                if (collision != null) {
                    CollisionDetector.resolveWallCollision(boundedBall, collision);
                }
            }
        }

        //3.공 충돌 단계:
        //이중 루프로 모든 쌍 검사 (i < j)
        //areColliding() 호출
        //공 충돌시 resolveCollision() 호출

        // 이중 충돌 방지
        for (int i = 0; i < getBalls().size(); i++) {
            for (int j = i + 1; j < getBalls().size(); j++) {
                // 각 쌍을 한 번만 검사
                Ball ball1 = getBalls().get(i);
                Ball ball2 = getBalls().get(j);

                if (BallCollision.areColliding(ball1, ball2)) {
                    BallCollision.resolveElasticCollision(ball1, ball2);
                    BallCollision.separateBalls(ball1, ball2);
                }
            }
        }
    }
}
