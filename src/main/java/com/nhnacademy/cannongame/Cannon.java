package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cannon {
    private final double x;
    private final double y;
    private double angle; // -PI/2 ~ 0
    private double power;
    private final double barrelLength = 50;
    private boolean isCharging;
    private long chargeStartTime;

    private static final double MAX_CHARGE_TIME = 2.0; // 초

    public Cannon(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = -Math.PI / 4; // 초기 -45도
        this.power = 300;
        this.isCharging = false;
    }

    private double lastChargeLevel = 0;
    private boolean maxCharged = false;

    public void startCharging() {
        if (!isCharging) {
            isCharging = true;
            chargeStartTime = System.currentTimeMillis();
            maxCharged = false;
        }
    }

    public void stopCharging() {
        double chargeLevel = getChargeLevel();
        power = 100 + chargeLevel * 900;
        isCharging = false;
        maxCharged = false;
        lastChargeLevel = 0; // 스페이스바 떼면 초기화
    }

    public double getChargeLevel() {
        // 충전 중이 아니면 마지막 레벨 유지
        if (isCharging) {
            if (!maxCharged) {
                double elapsed = (System.currentTimeMillis() - chargeStartTime) / 1000.0;
                if (elapsed >= MAX_CHARGE_TIME) {
                    lastChargeLevel = 1.0; // 최대치 도달
                    maxCharged = true;     // 이제 고정
                } else {
                    lastChargeLevel = elapsed / MAX_CHARGE_TIME;
                }
            }
        }
        return lastChargeLevel;
    }

    public void adjustAngle(double delta) {
        angle += delta;
        if (angle < -Math.PI) {
            angle = -Math.PI; // 9시
        }
        if (angle > 0) {
            angle = 0; // 3시
        }
    }

    public Projectile fire() {
        double endX = x + barrelLength * Math.cos(angle);
        double endY = y + barrelLength * Math.sin(angle);

        double vx = power * Math.cos(angle);
        double vy = power * Math.sin(angle);

        return new Projectile(endX, endY, vx, vy);
    }

    public void paint(GraphicsContext gc) {
        // 포신
        gc.save();
        gc.translate(x, y);
        gc.rotate(Math.toDegrees(angle));
        gc.setFill(Color.DARKGRAY);
        double barrelWidth = 10;
        gc.fillRect(0, -barrelWidth / 2, barrelLength, barrelWidth);
        gc.restore();

        // 대포 본체
        gc.setFill(Color.BLACK);
        gc.fillOval(x - 15, y - 15, 30, 30);

        // 파워 게이지 (충전 색상)
        double chargeLevel = getChargeLevel();
        gc.setFill(Color.color(chargeLevel, 1 - chargeLevel, 0)); // 초록 → 빨강
        gc.fillRect(x - 50, y + 30, 100 * chargeLevel, 10);
    }
}
