package com.nhnacademy.cannongame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CannonGameApp extends Application {

    private CannonGameWorld world;
    private Canvas canvas;
    private GraphicsContext gc;
    private Timeline gameLoop;

    @Override
    public void start(Stage primaryStage) {
        world = new CannonGameWorld(800, 600);

        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        Scene scene = new Scene(root, 800, 600);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) world.getCannon().adjustAngle(-0.05);
            if (e.getCode() == KeyCode.DOWN) world.getCannon().adjustAngle(0.05);
            if (e.getCode() == KeyCode.SPACE) world.getCannon().startCharging(); // 충전 시작
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                world.getCannon().stopCharging(); // 충전 종료 + power 계산
                world.fire();                    // 계산된 파워로 발사
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cannon Game");
        primaryStage.show();

        createGameLoop();
        gameLoop.play();
    }

    private void createGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            world.update(0.016);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            world.render(gc);
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }
}
