package com.nhnacademy.cannongame;

import javafx.scene.canvas.GraphicsContext;

// 화면에 그릴 수 있는 객체를 위한 인터페이스:
public interface Paintable {
    void paint(GraphicsContext gc);
}