package com.nhnacademy.cannongame;

public interface Boundable {
    Bounds getBounds();
    boolean isColliding(Boundable other);
}
