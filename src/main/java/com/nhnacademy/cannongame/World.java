package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// 컨테이너 클래스 - 여러 개의 Ball을 관리
public class World {
    final double width;
    final double height;
    private final List<Ball> balls = new ArrayList<>();

    public World(double width, double height){
        this.width = width;
        this.height = height;

        if(width * height <= 0){
            throw new IllegalArgumentException("월드의 크기는 0 이하일 수 없습니다.");
        }
    }

    public void add(Ball ball){
        if(ball == null){
            throw new IllegalArgumentException("공이 null일 수 없습니다.");
        }

        if (!isInBounds(ball)) {
            //throw new IllegalArgumentException("공이 월드 경계를 벗어날 수 없습니다.");
            System.out.print("공이 월드 경계를 벗어난 상태입니다"); // 임시
        }

        balls.add(ball);
    }

    public void remove(Ball ball){
        if(!balls.contains(ball)){
            throw new NoSuchElementException("공이 없습니다.");
        }

        balls.remove(ball);
    }

    public void remove(){
        balls.clear();
    }

    // 방어적 복사 - 복사본 반환으로 외부에서 리스트 수정 불가
    public List<Ball> getBalls() {
        return new ArrayList<>(balls); // 복사본 반환
    }

    public int getBallCount() {
        return balls.size();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    //AdvancedCollisionApp에 사용하기 위해 추가
    public void removeLast() {
        if (!balls.isEmpty()) {
            balls.removeLast();
        }
    }

    // 모든 공 화면에 그리기
    public void draw(GraphicsContext gc){
        gc.clearRect(0,0, width, height); // 배경지우기

        for(Ball ball: balls){
            if(ball instanceof PaintableBall paintableBall){ // 모든 공 중에서 그릴수있는 공 객체면
                paintableBall.draw(gc);
            }
        }
    }

    private boolean isInBounds(Ball ball){
        return !(ball.getCenter().x() - ball.getRadius() < 0) &&
                !(ball.getCenter().y() - ball.getRadius() < 0) &&
                !(ball.getCenter().x() + ball.getRadius() > width) &&
                !(ball.getCenter().y() + ball.getRadius() > height);
    }
}
