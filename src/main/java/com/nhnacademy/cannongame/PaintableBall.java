package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


//Ball 상속 받고 색상정보 color(JavaFX Color 타입) 추가
public class PaintableBall extends Ball{

    private Color color;

    // 여러 생성자 오버로딩
    public PaintableBall(double x, double y, double radius) {
        super(x, y, radius);
        this.color = Color.RED;
    }

    public PaintableBall(double x, double y, double radius, Color color) {
        super(x, y, radius);
        this.color = color;
    }

    public PaintableBall(Point point, int y, Color color) {
        super(point, y);
        this.color = color;
    }

    public PaintableBall(Point point, int i) {
        super(point, i);
        this.color = Color.RED;
    }

    public Color getColor() {
        if(color == null){
            color = Color.RED; // 색상 NULL일시 기본 색 빨간색
        }
        return color;
    }

    // 색상 변경 메서드
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("null 색상 설정은 불가능 합니다.");
        }
        this.color = color;
    }

    // ball 본인 한개를 스스로 그리는 draw
    public void draw(GraphicsContext gc) {
        // 공의 왼쪽 상단 좌표 계산
        Point center = getCenter();
        double leftX = center.x() - getRadius();
        double topY = center.y() - getRadius();
        double diameter = getRadius() * 2;

        // 공 채우기
        gc.setFill(this.color);
        gc.fillOval(leftX, topY, diameter, diameter);
    }

}
