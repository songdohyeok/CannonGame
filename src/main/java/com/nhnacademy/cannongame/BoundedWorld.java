package com.nhnacademy.cannongame;

//MovableWorld를 상속받아 충돌 처리 기능이 추가된 클래스
public class BoundedWorld extends MovableWorld{
    public BoundedWorld(double width, double height) {
        super(width, height);
    }

    //add(Ball ball): 공 추가될때 영역 설정
    @Override
    public void add(Ball ball){
        if(ball == null){
            throw new IllegalArgumentException("공이 null일 수 없습니다.");
        }
        super.add(ball);
        if (ball instanceof BoundedBall boundedBall) {
            boundedBall.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    //1.이동 단계: 모든 MovableBall의 move() 호출 (모든 공 이동)
    @Override
    public void update(double deltaTime){
        for(Ball ball: getBalls()){
            if (ball instanceof MovableBall movableBall){
                movableBall.move(deltaTime);
            }
        }

        //2.벽 충돌 단계: (벽과의 충돌 검사 및 처리)
        for (Ball ball : getBalls()) {
            if (ball instanceof BoundedBall boundedBall) {
                // 충돌 체크
                CollisionDetector.WallCollision collision =
                        CollisionDetector.checkWallCollision(boundedBall, 0, 0, getWidth(), getHeight());
                // 충돌시
                if (collision != null) {
                    CollisionDetector.resolveWallCollision(boundedBall, collision);
                }
            }
        }
        //3.공 충돌 단계: (공 간의 충돌 검사 및 처리)
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