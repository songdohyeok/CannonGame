package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// 상하좌우 안깨지는 벽 아래는 처리 따로 필요
public class UnbreakableBrick extends StaticObject {
    public UnbreakableBrick(double x, double y, double width, double height, Color color){
        super(x, y, width, height, color != null ? color : Color.GRAY);
    }

    @Override
    public void paint(GraphicsContext gc){
        if(gc == null) return;
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void handleCollision(Collidable other){
    }
}
