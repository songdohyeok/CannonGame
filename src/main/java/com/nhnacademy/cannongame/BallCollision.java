package com.nhnacademy.cannongame;

//두 공 사이의 충돌을 감지하고 처리하는 클래스
public class BallCollision {

    static public boolean areColliding(Ball ball1, Ball ball2){
        //두 공의 중심 거리 계산
        //거리 < 두 반지름의 합이면 충돌상태

        double distance = Math.hypot(ball2.getCenter().x() - ball1.getCenter().x(),
                ball2.getCenter().y() - ball1.getCenter().y());

        return distance < ball1.getRadius() + ball2.getRadius();
    }

    static public void resolveElasticCollision(Ball ball1, Ball ball2){
        //MovableBall인 경우,튕겨남
        //탄성 충돌 처리 (운동량 보존)
        //충돌 방향 계산
        //상대 속도 계산
        //충격량 계산 및 속도 업데이트
        //겹침 해결

        //물리 원리
        //운동량 보존: m₁v₁ + m₂v₂ = m₁v₁' + m₂v₂'
        //충격량: I = Δp = mΔv
        //탄성 충돌: 에너지 보존

        //// 충돌 방향 벡터 (정규화)
        //n = (ball2 - ball1) / distance
        //
        //// 충돌 감지
        //거리 = √((x₂-x₁)² + (y₂-y₁)²)
        //충돌 조건: 거리 < r₁ + r₂
        //// 충격량 계산
        //impulse = 2 * 상대속도 / (질량합)
        //
        //// 멀어지고 있는지 확인
        //if (상대속도 · 충돌방향 <= 0) return;

        if(!(ball1 instanceof MovableBall) && !(ball2 instanceof MovableBall)){
            return;
        }

        // 중심 좌표
        Point p1 = ball1.getCenter();
        Point p2 = ball2.getCenter();

        // 위치 차이
        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // 충돌 안 했으면 종료
        if (distance == 0 || distance >= ball1.getRadius() + ball2.getRadius()) {
            return;
        }

        // 단위 방향 벡터
        double nx = dx / distance;
        double ny = dy / distance;

        // 속도 벡터
        Vector2D v1 = (ball1 instanceof MovableBall m1) ? m1.getVelocity() : new Vector2D(0, 0);
        Vector2D v2 = (ball2 instanceof MovableBall m2) ? m2.getVelocity() : new Vector2D(0, 0);

        // 상대 속도
        double rvx = v1.getX() - v2.getX();
        double rvy = v1.getY() - v2.getY();

        // 상대 속도가 충돌 방향으로 멀어지고 있으면 무시
        double velAlongNormal = rvx * nx + rvy * ny;
        if (velAlongNormal > 0) {
            return;
        }

        // 질량 (반지름 비례 가정)
        double m1 = ball1.getRadius();
        double m2 = ball2.getRadius();

        // 충격량 크기 (탄성 충돌 계수 e = 1)
        double j = -(1 + 1.0) * velAlongNormal / (1 / m1 + 1 / m2);

        // 충격량 벡터
        double impulseX = j * nx;
        double impulseY = j * ny;

        // 속도 업데이트
        if (ball1 instanceof MovableBall m1b) {
            m1b.setVelocity(new Vector2D(
                    v1.getX() + impulseX / m1,
                    v1.getY() + impulseY / m1
            ));
        }
        if (ball2 instanceof MovableBall m2b) {
            m2b.setVelocity(new Vector2D(
                    v2.getX() - impulseX / m2,
                    v2.getY() - impulseY / m2
            ));
        }
    }

    static void separateBalls(Ball ball1, Ball ball2){

        // 중심 좌표
        Point p1 = ball1.getCenter();
        Point p2 = ball2.getCenter();

        // 위치 차이
        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // 충돌 안 했으면 종료
        if (distance == 0 || distance >= ball1.getRadius() + ball2.getRadius()) {
            return;
        }

        // 단위 방향 벡터
        double nx = dx / distance;
        double ny = dy / distance;

        //겹친 공을 분리
        //각 공을 겹침의 절반만큼 밀어냄
        // 겹침 해소: 각 공을 반씩 이동
        double overlap = (ball1.getRadius() + ball2.getRadius()) - distance;
        double correctionX = nx * overlap / 2;
        double correctionY = ny * overlap / 2;

        if (ball1 instanceof MovableBall movableBall1) {
            movableBall1.moveTo(new Point(
                    p1.x() - correctionX,
                    p1.y() - correctionY
            ));
        }
        if (ball2 instanceof MovableBall movableBall2) {
            movableBall2.moveTo(new Point(
                    p2.x() + correctionX,
                    p2.y() + correctionY
            ));
        }

    }
}
