package com.nhnacademy.cannongame;

import javafx.application.Application;
import javafx.stage.Stage;

//고급 충돌 시뮬레이션 앱
public class AdvancedCollisionApp extends Application {
    // 중력 가속도 (예: 500 pixels/s²)
    private static final double GRAVITY = 500;
    BoundedWorld boundedWorld = new BoundedWorld(800,600);

    @Override
    public void start(Stage primaryStage) {


        //UI 컨트롤
        //중력 활성화 체크박스
        //공 개수 조절 슬라이더

        //다양한 크기의 공 생성
        //AnimationTimer로 게임 루프 구현



    }

    //랜덤한 크기와 속도의 공 생성
    public void createBalls(){

    }

    //모든 공에 중력 적용 (dy 증가)
    public void applyGravity(){

    }

    //render(): Canvas에 그리기
    public void render(){

    }
}