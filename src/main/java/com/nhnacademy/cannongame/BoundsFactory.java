package com.nhnacademy.cannongame;

public class BoundsFactory {

    static public Bounds createCircleBounds(Point point, double radius){
        if(point == null) throw new IllegalArgumentException("Point는 null일 수 없습니다");
        if(radius <= 0) throw new IllegalArgumentException("반지름은 양수여야 합니다");
        return new CircleBounds(point, radius);
    }

    static public Bounds createRectangleBounds(double minX, double minY, double width, double height){
        if(width <= 0 || height <= 0) throw new IllegalArgumentException("너비와 높이는 양수여야 합니다");
        return new RectangleBounds(minX, minY, width, height);
    }
}
