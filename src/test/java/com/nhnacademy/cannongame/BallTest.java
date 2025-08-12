package com.nhnacademy.cannongame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BallTest {
    private Ball ball;

    @BeforeEach
    public void setUp() {
        ball = new Ball(100, 100, 20);
    }

    @Test
    public void testBallCreation() {
        Ball ball = new Ball(new Point(100, 200), 30);
        assertEquals(100, ball.getCenter().getX());
        assertEquals(200, ball.getCenter().getY());
        assertEquals(30, ball.getRadius());
    }

    @Test
    public void testBallArea() {
        Ball ball = new Ball(new Point(0, 0), 10);
        assertEquals(Math.PI * 100, ball.getArea(), 0.001);
    }


    @Test
    public void testInvalidRadius() {
        assertThrows(IllegalArgumentException.class,
                () -> new Ball(0, 0, -5));
    }
}