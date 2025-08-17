package com.nhnacademy.cannongame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BreakoutTest {

    @Test
    public void testPaddleBallCollision() {
        BreakoutPaddle paddle = new BreakoutPaddle(400, 550);
        Ball ball = new Ball(450, 540, 10);
        ball.setDy(100); // 아래로 이동

        assertTrue(paddle.isColliding(ball));

        paddle.handleCollision(ball);

        // 공이 위로 반사됨
        assertTrue(ball.getDy() < 0);
    }

    @Test
    public void testBrickDestruction() {
        SimpleBrick brick = new SimpleBrick(100, 100, 70, 20, BrickType.NORMAL);
        Ball ball = new Ball(135, 110, 10);

        assertFalse(brick.isDestroyed());

        brick.handleCollision(ball);

        assertTrue(brick.isDestroyed());
        assertEquals(10, brick.getPoints());
    }

    @Test
    public void testGameStateTransitions() {
        BreakoutGame game = new BreakoutGame();

        assertEquals(GameState.MENU, game.getGameState());

        game.startGame();
        assertEquals(GameState.PLAYING, game.getGameState());

        game.pauseGame();
        assertEquals(GameState.PAUSED, game.getGameState());

        // 모든 생명을 잃으면
        for (int i = 0; i < 3; i++) {
            game.loseLife();
        }
        assertEquals(GameState.GAME_OVER, game.getGameState());
    }
}