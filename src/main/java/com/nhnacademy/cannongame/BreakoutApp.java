package com.nhnacademy.cannongame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BreakoutApp extends Application {

    private BreakoutWorld world;

    @Override
    public void start(Stage primaryStage) {
        double worldWidth = 800;
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        // Canvas 생성
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 점수 / 생명 UI
        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        Label livesLabel = new Label("Lives: 3");
        livesLabel.setLayoutX(700);
        livesLabel.setLayoutY(10);
        root.getChildren().addAll(canvas, scoreLabel, livesLabel);

        // BreakoutWorld 초기화
        world = new BreakoutWorld(3, scoreLabel, livesLabel);

        // 패들, 공, 벽, 브릭 생성
        BreakoutPaddle paddle = new BreakoutPaddle(350, 550);
        world.setPaddle(paddle);

        BreakoutBall ball = new BreakoutBall(400, 500, 10, null);
        world.addBall(ball);

        world.createWalls(800, 600);

        // 브릭 추가
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                world.addBrick(new SimpleBrick(60 + j * 70, 50 + i * 30, 60, 20, BrickType.NORMAL));
            }
        }

        // Scene 설정
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Breakout Game");
        primaryStage.show();

        // 게임 시작
        world.startGame();

        // 마우스 이동으로 패들 제어
        scene.setOnMouseMoved(event -> paddle.setTargetX(event.getX(), worldWidth));

        // 화면 업데이트 루프
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (world.getGameState() == GameState.PLAYING) {
                    world.update(1.0 / 60.0);  // 공, 브릭, 벽 충돌 처리
                }

                // 화면 그리기
                gc.clearRect(0, 0, 800, 600);
                drawWorld(gc);
            }
        }.start();
    }

    private void drawWorld(GraphicsContext gc) {
        // 브릭
        for (Breakable brick : world.getBricks()) {
            if (brick instanceof Paintable paintable) {
                paintable.paint(gc);
            }
        }

        // 패들
        BreakoutPaddle paddle = world.getPaddle();
        if (paddle != null) paddle.paint(gc);

        // 공
        for (BreakoutBall ball : world.getBalls()) {
            ball.paint(gc);
        }

        // 벽
        for (UnbreakableBrick wall : world.getWalls()) {
            wall.paint(gc);
        }
    }
}
