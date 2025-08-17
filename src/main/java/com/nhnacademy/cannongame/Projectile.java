package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Projectile {
    private double x, y;
    private final double vx;
    private double vy;
    private final List<double[]> trajectory = new ArrayList<>();

    private static final double GRAVITY = 200;

    public Projectile(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void update(double deltaTime) {
        trajectory.add(new double[]{x, y});
        if (trajectory.size() > 100) trajectory.removeFirst();

        vy += GRAVITY * deltaTime;
        x += vx * deltaTime;
        y += vy * deltaTime;
    }

    public void paint(GraphicsContext gc) {
        gc.setStroke(Color.color(0, 0, 0, 0.1));
        gc.setLineWidth(2);
        for (int i = 1; i < trajectory.size(); i++) {
            double[] p1 = trajectory.get(i - 1);
            double[] p2 = trajectory.get(i);
            gc.strokeLine(p1[0], p1[1], p2[0], p2[1]);
        }

        gc.setFill(Color.BLACK);
        double radius = 8;
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
