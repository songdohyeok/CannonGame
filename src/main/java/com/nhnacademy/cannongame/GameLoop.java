package com.nhnacademy.cannongame;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer {
    private long lastUpdate;
    private World world;
    private GraphicsContext gc;

    public GameLoop(World world, GraphicsContext gc){
        this.world = world;
        this.gc = gc;
    }

    // AnimationTimer가 매 프레임 호출
    @Override
    public void handle(long now){
        // 나노초를 초로 변환: 1초 = 1,000,000,000 나노초
        if (lastUpdate > 0) {
            double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
            update(lastUpdate);
            render();
        }
        lastUpdate = now;
    }

    // 공 위치 업데이트
    public void update(double deltaTime){
        //모든 MovableBall의 move(deltaTime) 호출
        for(Ball ball: world.getBalls()){
            if (ball instanceof MovableBall movableBall){
                movableBall.move(deltaTime);
            }
        }
    }

    public void render(){
        //화면 지우기
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        //배경 그리기
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        //모든 공 그리기
        for(Ball ball: world.getBalls()){
            if(ball instanceof PaintableBall paintableBall){
                paintableBall.draw(gc);
            }
        }

    }
}
