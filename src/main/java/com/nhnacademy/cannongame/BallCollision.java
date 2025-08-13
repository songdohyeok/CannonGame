package com.nhnacademy.cannongame;

// 공-공 충돌 처리 클래스
public class BallCollision {
    //두 공의 중심 거리 계산: 거리 < 두 반지름의 합 = 충돌상태
    static public boolean areColliding(Ball ball1, Ball ball2){
        double distance = Math.hypot(ball2.getCenter().x() - ball1.getCenter().x(),
                ball2.getCenter().y() - ball1.getCenter().y());

        return distance < ball1.getRadius() + ball2.getRadius();
    }

    static public void resolveElasticCollision(Ball ball1, Ball ball2){

        //MovableBall인 경우에만 팅기니 아닐시 무시
        if(!(ball1 instanceof MovableBall) && !(ball2 instanceof MovableBall)){
            return;
        }

        // 중심 좌표 구하기
        Point p1 = ball1.getCenter();
        Point p2 = ball2.getCenter();

        // 위치 차이 및 거리 계산
        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // 충돌 안 했으면 종료
        if (distance == 0 || distance >= ball1.getRadius() + ball2.getRadius()) {
            return;
        }

        // 충돌 방향 벡터 단위화(정규화)
        double nx = dx / distance;
        double ny = dy / distance;

        // 속도 벡터
        Vector2D v1 = (ball1 instanceof MovableBall m1) ? m1.getVelocity() : new Vector2D(0, 0);
        Vector2D v2 = (ball2 instanceof MovableBall m2) ? m2.getVelocity() : new Vector2D(0, 0);

        // 상대 속도
        double rvx = v1.getX() - v2.getX();
        double rvy = v1.getY() - v2.getY();

        // 상대 속도가 충돌하는 방향으로 얼마나 움직이는지 계산
        // 상대 속도가 충돌 방향과 일치하면 내적 결과는 양수 반대는 음수
        double velAlongNormal = rvx * nx + rvy * ny;

        // 두 공이 서로 멀어지면 무시 (상대 속도가 충돌 방향으로 멀어지는 상황)
        if (velAlongNormal > 0) {
            return;
        }

        // 질량 (반지름을 질량으로 가정)
        double m1 = ball1.getRadius();
        double m2 = ball2.getRadius();

        // 충격량 크기 계산 (탄성 충돌 계수 e = 1.0 기준)
        double j = -(1 + 1.0) * velAlongNormal / (1 / m1 + 1 / m2);

        // 충격량 벡터 계산
        double impulseX = j * nx;
        double impulseY = j * ny;

        // 속도 업데이트 (운동량 보존 법칙)
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

        Point p1 = ball1.getCenter();
        Point p2 = ball2.getCenter();

        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance == 0 || distance >= ball1.getRadius() + ball2.getRadius()) {
            return;
        }

        double nx = dx / distance;
        double ny = dy / distance;

        //각 공을 반씩 이동함으로써 겹침 분리
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
