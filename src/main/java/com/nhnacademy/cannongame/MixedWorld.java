package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

// Ball과 Box를 모두 관리
public class MixedWorld {
    private List<Ball> balls;
    private List<Box> boxes;
    private double width, height;

    public MixedWorld(double width, double height){
        this.width = width;
        this.height = height;
        this.balls = new ArrayList<>();
        this.boxes = new ArrayList<>();
    }

    public void addBall(Ball ball){
        if(ball != null){
            balls.add(ball);
        }

    }

    public void addBox(Box box){
        if(box != null){
            boxes.add(box);
        }
    }

    public void update(double deltaTime){
        for(Ball ball:balls){
            if(ball instanceof MovableBall movableBall){
                movableBall.move(deltaTime);
            }
        }

        for(Box box:boxes){
            if(box instanceof MovableBox movableBox){
                movableBox.move(deltaTime);
            }
        }
    }

    public void render(GraphicsContext gc){
        for(Ball ball:balls){
            if(ball instanceof PaintableBall paintableBall){
                paintableBall.draw(gc);
            }
        }

        for(Box box: boxes){
            if(box instanceof MovableBox){
                Point topLeft = box.getPosition(); //Box의 맨 왼쪽 위의 좌표
                gc.setFill(Color.RED);
                gc.fillRect(topLeft.getX(), topLeft.getY(), box.getWidth(), box.getHeight());
            }
        }

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getBallCount() {
        return balls.size();
    }
    public int getBoxCount() {
        return balls.size();
    }

}
