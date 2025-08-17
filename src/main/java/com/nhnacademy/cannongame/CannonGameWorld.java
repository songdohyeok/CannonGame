package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CannonGameWorld {
    private final Cannon cannon;
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Target> targets = new ArrayList<>();
    private int score = 0;
    private int shotsRemaining = 10;


    public CannonGameWorld(double width, double height) {
        cannon = new Cannon(width / 2, height - 50);
        for (int i = 0; i < 10; i++) {
            double w = 40, h = 40;
            Random random = new Random();
            double x = random.nextDouble() * (width - w);
            double y = random.nextDouble() * (height - 200); // 아래 대포 영역 제외
            int points = 10;
            double durability = 1.0;
            targets.add(new Target(x, y, w, h, points, durability));
        }
    }

    public void fire() {
        if (shotsRemaining <= 0) return;
        Projectile p = cannon.fire();
        projectiles.add(p);
        shotsRemaining--;
    }

    public void update(double deltaTime) {
        for (Projectile p : projectiles) {
            p.update(deltaTime);
        }

        for (Projectile p : projectiles) {
            for (Target t : targets) {
                if (!t.isDestroyed() && checkCollision(p, t)) {
                    t.takeDamage(1.0);
                    if (t.isDestroyed()) {
                        score += t.getPoints();  // 타겟 파괴 시 점수 증가
                    }
                }
            }
        }
    }

    private boolean checkCollision(Projectile p, Target t) {
        double px = p.getX();
        double py = p.getY();
        return px >= t.getX() && px <= t.getX() + t.getWidth() &&
                py >= t.getY() && py <= t.getY() + t.getHeight();
    }

    public void render(GraphicsContext gc) {
        cannon.paint(gc);
        for (Projectile p : projectiles) p.paint(gc);
        for (Target t : targets) t.paint(gc);

        // 남은 발사 횟수 표시
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText("잔여 포탄: " + shotsRemaining, 10, 20);

        // 점수 표시
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText("점수: " + score, 10, 40);
    }

    public Cannon getCannon() {
        return cannon;
    }
}
