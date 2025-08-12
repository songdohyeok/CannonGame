package com.nhnacademy.cannongame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class BallWorldApp extends Application {

    Canvas canvas = new Canvas(800, 600); // 1. 도화지를 생성한다.
    GraphicsContext gc = canvas.getGraphicsContext2D(); // 2. 그림 도구를 생성한다.
    World world = new World(800, 600);
    Random random = new Random();

    @Override
    public void start(Stage stage) {
        createRandomBalls(5);
        handleMouseClick();
        draw();

        // 레이아웃 + 도화지(Canvas) 설정
        StackPane root = new StackPane(canvas);

        // 화면(Scene) 생성 - 화면에 레이아웃과 도화지를 포함시킨다.
        Scene scene = new Scene(root, world.getWidth(), world.getHeight());

        // javafx 프로그램이 실행될 윈도우 창(Stage) 설정
        stage.setTitle("BallWorld"); // 윈도우 창 제목
        stage.setScene(scene); // 윈도우 창의 화면을 설정
        stage.show(); // 윈도우 창 표시하기

    }

    public void createRandomBalls(int count){
        for (int i = 0; i < count; i++) {
            double radius = random.nextDouble() * 40 + 10;
            // 공의 반지름의 최대가 50, 월드의 가로가 800이라 치면 공 생성 범위가 50 ~ 750이어야 공이 잘리지 않음
            double x = random.nextDouble() * (world.getWidth() - radius * 2) + radius;
            double y = random.nextDouble() * (world.getHeight() - radius * 2) + radius;

            // 랜덤 RGB 색상
            Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

            PaintableBall ball = new PaintableBall(new Point(x, y), (int) radius, color);
            world.add(ball);
        }
    }

    public void handleMouseClick(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double radius = random.nextDouble() * 40 + 10;
            // 클릭한 위치의 x, y 좌표
            double x = event.getX();
            double y = event.getY();
            Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

            try {
                PaintableBall ball = new PaintableBall(new Point(x, y), (int) radius, color);
                world.add(ball);
                world.draw(gc);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void draw(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for(Ball ball: world.getBalls()){
            if (ball instanceof PaintableBall paintableBall){
                paintableBall.draw(gc);
            }
        }
    }
}
