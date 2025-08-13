package com.nhnacademy.cannongame;

// 공-벽 충돌 처리 클래스
public class CollisionDetector {

    public enum Wall {
        LEFT, RIGHT, TOP, BOTTOM
    }

    /**
     * @param wall        충돌한 벽
     * @param penetration 벽 침투 깊이
     */
    public record WallCollision(Wall wall, double penetration) {
    }

    // 각 벽 충돌 검사. 충돌한 벽 이름과 침투 깊이 반환. 충돌 없을시 null
    public static WallCollision checkWallCollision(BoundedBall ball, double minX, double minY, double maxX, double maxY) {
        double ballX = ball.getCenter().x();
        double ballY = ball.getCenter().y();
        double radius = ball.getRadius();

        //// 침투 깊이 계산
        //// 왼쪽 벽: minX - (ballX - radius) 오른쪽 벽: (ballX + radius) - maxX

        // 왼쪽 벽
        double penetration = minX - (ballX - radius);
        if (penetration > 0) {
            return new WallCollision(Wall.LEFT, penetration);
        }

        // 오른쪽 벽
        penetration = (ballX + radius) - maxX;
        if (penetration > 0) {
            return new WallCollision(Wall.RIGHT, penetration);
        }

        // 위쪽 벽
        penetration = minY - (ballY - radius);
        if (penetration > 0) {
            return new WallCollision(Wall.TOP, penetration);
        }

        // 아래쪽 벽
        penetration = (ballY + radius) - maxY;
        if (penetration > 0) {
            return new WallCollision(Wall.BOTTOM, penetration);
        }
        return null;
    }

    // 벽 충돌 처리
    static public void resolveWallCollision(BoundedBall ball, WallCollision collision) {
        //충돌한 벽에 따라 속도 반전 -> LEFT RIGHT: x 속도 반전 , TOP BOTTOM: y 속도 반전
        //// 속도 반전 (이 장에서는 단순화를 위해 반발 계수 1.0 가정)
        //ball.setDx(-ball.getDx());
        //// 추후 반발 계수 적용 시: ball.setDx(-ball.getDx() * restitution);
        final double worldWidth = 800;
        final double worldHeight = 600;

        double restitution = CoefficientOfRestitution();
        Point currentPos = ball.getCenter();
        double x = currentPos.x();
        double y = currentPos.y();

        Vector2D getV = ball.getVelocity();
        double radius = ball.getRadius();

        // 벽 안쪽으로 위치 보정 먼저 하고 속도 조절!!
        switch (collision.wall()) {
            case LEFT:
            case RIGHT:
                ball.moveTo(new Point(
                        collision.wall() == Wall.LEFT ? radius : worldWidth - radius, y));
                double absVelocityX = (collision.wall() == Wall.LEFT)
                        ? Math.abs(getV.getX()) * restitution // 왼쪽 벽 닿으면 x속도 양수로 변환
                        : -Math.abs(getV.getX()) * restitution; // 오른쪽 벽 닿으면 x속도 음수로
                ball.setVelocity(new Vector2D(absVelocityX, getV.getY()));
                break;
            case TOP:
            case BOTTOM:
                ball.moveTo(new Point(x,
                        collision.wall() == Wall.TOP ? radius : worldHeight - radius));
                double absVelocityY = (collision.wall() == Wall.TOP)
                        ? Math.abs(getV.getY()) * restitution   // 위쪽 벽 닿으면 y속도 양수로
                        : -Math.abs(getV.getY()) * restitution; // 아래쪽 벽 닿으면 y속도 음수로
                ball.setVelocity(new Vector2D(getV.getX(), absVelocityY));
                break;
        }
    }

    // 반발 계수
    static public double CoefficientOfRestitution(){
        //0.0: 완전 비탄성 충돌 (끝적이는 효과)
        //1.0: 완전 탄성 충돌 (에너지 손실 없음)
        //0.8: 일반적인 공의 반발
        return 1.0;
    }
}