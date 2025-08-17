package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SimpleWorld {
    private final double width;
    private final double height;
    private final List<Object> gameObjects = new ArrayList<>();
    private final List<Box> boundaries = new ArrayList<>();

    public SimpleWorld(double width, double height) {
        this.width = width;
        this.height = height;
        createBoundaries();
    }

    // 경계 4개 생성
    private void createBoundaries() {
        // 상, 하, 좌, 우 경계 Box
        Box top    = new Box(new Point(0, -10), width, 10);   // 위쪽, 화면 밖
        Box bottom = new Box(new Point(0, height), width, 10); // 아래쪽
        Box left   = new Box(new Point(-10, 0), 10, height);
        Box right  = new Box(new Point(width, 0), 10, height);

        for (Box b : List.of(top, bottom, left, right)) {
            b.setCollisionAction(Collidable.CollisionAction.BOUNCE); // 경계는 튕김
            boundaries.add(b);
        }
    }

    // 객체 추가
    public void addObject(Object obj) {
        if (obj != null) {
            gameObjects.add(obj);
        }
    }

    // 월드 업데이트
    public void update(double deltaTime) {
        // 1) Movable 이동
        for (Object obj : gameObjects) {
            if (obj instanceof Movable movable) {
                movable.move(deltaTime);
            }
        }

        // 2) 경계 충돌 처리
        for (Object obj : gameObjects) {
            if (obj instanceof Collidable collidable) {
                for (Box boundary : boundaries) {
                    if (collidable.isColliding(boundary)) {
                        collidable.handleCollision(boundary);
                    }
                }
            }
        }

        // 3) 객체 간 충돌 처리 (이중 루프)
        int size = gameObjects.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Object o1 = gameObjects.get(i);
                Object o2 = gameObjects.get(j);

                if (o1 instanceof Collidable c1 && o2 instanceof Collidable c2) {
                    if (c1.isColliding(c2)) {
                        c1.handleCollision(c2);
                        c2.handleCollision(c1);
                    }
                }
            }
        }
    }

    // 그리기
    public void render(GraphicsContext gc) {
        if (gc == null) return;

        // 배경 초기화
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        // Paintable 객체만 그리기
        for (Object obj : gameObjects) {
            if (obj instanceof Paintable paintable) {
                paintable.paint(gc);
            }
        }
    }
}
