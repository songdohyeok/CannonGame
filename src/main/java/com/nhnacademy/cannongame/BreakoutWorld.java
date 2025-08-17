package com.nhnacademy.cannongame;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BreakoutWorld {
    private List<UnbreakableBrick> walls = new ArrayList<>();
    private List<Breakable> bricks = new ArrayList<>();
    private List<BreakoutBall> balls = new ArrayList<>();
    private BreakoutPaddle paddle;

    private GameState gameState = GameState.MENU;
    private int score = 0;
    private int lives;
    private int level = 1;

    private Label scoreLabel;
    private Label livesLabel;

    private AnimationTimer gameLoop;

    public BreakoutWorld(int initialLives, Label scoreLabel, Label livesLabel) {
        this.lives = initialLives;
        this.scoreLabel = scoreLabel;
        this.livesLabel = livesLabel;
        initGameLoop();
    }

    private void initGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) { lastTime = now; return; }
                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                if (gameState == GameState.PLAYING) {
                    update(deltaTime);
                    updateUI();
                    checkGameOver();
                }
            }
        };
    }

    public void startGame() { gameState = GameState.PLAYING; gameLoop.start(); }
    public void pauseGame() { gameState = GameState.PAUSED; gameLoop.stop(); }
    public void resumeGame() { gameState = GameState.PLAYING; gameLoop.start(); }

    private void updateUI() {
        if (scoreLabel != null) scoreLabel.setText("Score: " + score);
        if (livesLabel != null) livesLabel.setText("Lives: " + lives);
    }

    private void checkGameOver() {
        if (lives <= 0) {
            gameState = GameState.GAME_OVER;
            gameLoop.stop();
        }
    }

    public void loseLife() {
        lives--;
        if (lives > 0) {
            balls.clear();   // 기존 공 제거
            resetBall();     // 공 재배치
        } else {
            checkGameOver(); // 생명 0 → 게임오버 처리
        }
    }

    public void update(double deltaTime) {
        for (int i = 0; i < balls.size(); i++) {
            BreakoutBall ball = balls.get(i);
            ball.move(deltaTime);

            // 하단 바닥 감지
            if (ball.getY() - ball.getRadius() > 600) {
                loseLife();   // 생명 감소 및 공 재배치
                return;       // 이미 공 제거 후 재배치했으므로 loop 종료
            }
            // 벽과 충돌 (상/좌/우 벽만 반사)
            for (UnbreakableBrick wall : walls) {
                if (ball.isColliding(wall)) {
                    if (wall.getY() == 0 || wall.getX() == 0 || wall.getX() + wall.getWidth() == 800) {
                        ball.handleCollision(wall);
                    }
                }
            }

            // 브릭과 충돌
            for (int j = 0; j < bricks.size(); j++) {
                Breakable brick = bricks.get(j);
                if (brick instanceof Collidable collidable && ball.isColliding(collidable)) {
                    ball.handleCollision(collidable);
                    brick.hit(1);
                    if (brick.isDestroyed()) {
                        score += brick.getPoints();
                        bricks.remove(j);
                        j--;
                    }
                }
            }

            // 패들과 충돌
            if (paddle != null && ball.isColliding(paddle)) {
                ball.handleCollision(paddle);
                paddle.reflectBall(ball);
            }
        }
    }

    private void resetBall() {
        if (paddle == null) return;
        BreakoutBall newBall = new BreakoutBall(
                (int) (paddle.getX() + paddle.getWidth() / 2),
                (int) (paddle.getY() - 10), 10, Color.RED);
        newBall.setDx(150);
        newBall.setDy(-150);
        balls.add(newBall);
    }
    public void addBrick(Breakable brick) { if (brick != null) bricks.add(brick); }
    public void addBall(BreakoutBall ball) { if (ball != null) balls.add(ball); }
    public void setPaddle(BreakoutPaddle paddle) { this.paddle = paddle; }

    private UnbreakableBrick bottomWall; // 클래스 멤버로 선언

    public void createWalls(double worldWidth, double worldHeight) {
        walls.add(new UnbreakableBrick(0, 0, worldWidth, 20, null)); // 상
        walls.add(new UnbreakableBrick(0, 0, 20, worldHeight, null)); // 좌
        walls.add(new UnbreakableBrick(worldWidth - 20, 0, 20, worldHeight, null)); // 우
        bottomWall = new UnbreakableBrick(0, worldHeight - 20, worldWidth, 20, null); // 하단 (충돌 제외)
    }


    public GameState getGameState() { return gameState; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    public List<Breakable> getBricks() { return bricks; }

    public List<BreakoutBall> getBalls() { return balls; }
    public BreakoutPaddle getPaddle() { return paddle; }
    public List<UnbreakableBrick> getWalls() { return walls; }
}
