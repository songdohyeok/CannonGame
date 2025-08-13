package com.nhnacademy.cannongame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

//고급 충돌 시뮬레이션 앱
public class AdvancedCollisionApp extends Application {
    // 중력 가속도 (예: 500 pixels/s²)
    private static final double GRAVITY = 500;
    // BoundedWorld 사용
    BoundedWorld boundedWorld = new BoundedWorld(800,600);

    private GraphicsContext gc;
    private CheckBox gravityCheckBox;

    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        gravityCheckBox = new CheckBox("중력 활성화 체크박스");
        Slider ballCountSlider = new Slider(1, 10, 5);
        ballCountSlider.setShowTickLabels(true);
        ballCountSlider.setShowTickMarks(true);

        VBox root = new VBox(gravityCheckBox, ballCountSlider, canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("고급 충돌 시뮬레이션 앱");
        primaryStage.setScene(scene);
        primaryStage.show();

        createBalls((int) ballCountSlider.getValue());

        //공 개수 조절 슬라이더
        ballCountSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                int newCount = (int) ballCountSlider.getValue();
                int currentCount = boundedWorld.getBalls().size();

                if (newCount > currentCount) {
                    createBalls(newCount - currentCount);
                } else if (newCount < currentCount) {
                    int ballsToRemove = currentCount - newCount;
                    for (int i = 0; i < ballsToRemove; i++) {
                        boundedWorld.removeLast();
                    }
                }
            }
        });

        //게임 루프 구현
        new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                if (gravityCheckBox.isSelected()) {
                    applyGravity(deltaTime);
                }

                boundedWorld.update(deltaTime);
                render();
            }
        }.start();
    }

    // 랜덤한 크기와 속도의 공 생성
    public void createBalls(int count){
        for (int i = 0; i < count; i++) {
            double radius = 10 + random.nextDouble() * 20; // 10~30 픽셀 크기
            double x = radius + random.nextDouble() * (boundedWorld.getWidth() - 2 * radius);
            double y = radius + random.nextDouble() * (boundedWorld.getHeight() - 2 * radius);

            // 랜덤 속도: -100 ~ 100 픽셀/초
            double dx = (random.nextDouble() - 0.5) * 200;
            double dy = (random.nextDouble() - 0.5) * 200;

            BoundedBall ball = new BoundedBall(new Point(x, y), radius);
            ball.setVelocity(new Vector2D(dx, dy));
            boundedWorld.add(ball);
        }
    }

    // 모든 공에 중력 적용 (dy 증가)
    public void applyGravity(double deltaTime){
        for (Ball ball : boundedWorld.getBalls()) {
            if (ball instanceof MovableBall movableBall) {
                Vector2D v = movableBall.getVelocity();
                movableBall.setVelocity(new Vector2D(v.getX(), v.getY() + GRAVITY * deltaTime));
            }
        }
    }

    // Canvas에 그리기
    public void render(){
        gc.clearRect(0, 0, boundedWorld.getWidth(), boundedWorld.getHeight());

        for (Ball ball : boundedWorld.getBalls()) {
            Point center = ball.getCenter();
            double radius = ball.getRadius();

            gc.fillOval(center.x() - radius, center.y() - radius, radius * 2, radius * 2);
        }
    }
}