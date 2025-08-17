package com.nhnacademy.cannongame;

import java.util.ArrayList;
import java.util.List;

public class BreakoutGame {

    // 상수
    public static final int INITIAL_LIVES = 3;
    public static final int POINTS_PER_BRICK = 10;
    public static final double BALL_SPEED_INCREMENT = 1.1;

    // 필드
    private BreakoutPaddle paddle;
    private List<BreakoutBall> balls;
    private List<Breakable> bricks;

    private GameState gameState;
    private int score;
    private int lives;
    private int level;

    public BreakoutGame() {
        this.paddle = null;
        this.balls = new ArrayList<>();
        this.bricks = new ArrayList<>();
        this.gameState = GameState.MENU;
        this.score = 0;
        this.lives = INITIAL_LIVES;
        this.level = 1;
    }

    // --- 게임 상태 관리 ---
    public void startGame() {
        this.gameState = GameState.PLAYING;
        this.score = 0;
        this.lives = INITIAL_LIVES;
        this.level = 1;
        this.balls.clear();
        this.bricks.clear();
    }

    public void pauseGame() {
        this.gameState = GameState.PAUSED;
    }

    public void resumeGame() {
        this.gameState = GameState.PLAYING;
    }

    public void gameOver() {
        this.gameState = GameState.GAME_OVER;
    }

    public void nextLevel() {
        this.level++;
        // 레벨업 시 공 속도 증가
        for (BreakoutBall ball : balls) {
            ball.setDx(ball.getDx() * BALL_SPEED_INCREMENT);
            ball.setDy(ball.getDy() * BALL_SPEED_INCREMENT);
        }
    }

    public void loseLife() {
        lives--;
        if (lives <= 0) {
            gameOver();
        }
    }

    public void addScore(int points) {
        score += points;
    }

    // --- 필드 접근자 ---
    public BreakoutPaddle getPaddle() {
        return paddle;
    }

    public void setPaddle(BreakoutPaddle paddle) {
        this.paddle = paddle;
    }

    public List<BreakoutBall> getBalls() {
        return balls;
    }

    public void addBall(BreakoutBall ball) {
        this.balls.add(ball);
    }

    public List<Breakable> getBricks() {
        return bricks;
    }

    public void addBrick(Breakable brick) {
        this.bricks.add(brick);
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }
}
