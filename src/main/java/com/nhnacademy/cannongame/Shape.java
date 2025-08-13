package com.nhnacademy.cannongame;

// 추상(상위) 클래스가 뼈대(템플릿)를 잡고 이를 유지하면서 구체(하위) 클래스에서 세부동작만 다르게 구현함 -> Template Method 패턴
public abstract class Shape {
    protected Point position;

    public Shape(Point position) {
        this.position = position;
    }

    // 추상 메서드 - 구현 없음
    public abstract double getArea();
    public abstract double getPerimeter(); //둘레
    public abstract String getShapeType();

    // 구체 메서드 - 구현 있음
    public Point getPosition(){
        return position;
    }

    // 위치 이동
    public void moveTo(Point newPosition){
        this.position = newPosition;
    }

    // 상대적 이동 (Vector는 이동량 표현에 사용)
    public void moveBy(Vector2D delta) {
        this.position = new Point(position.getX() + delta.getX(), position.getY() + delta.getY());
    }

    // 정보 출력 (템플릿 메소드 패턴)
    public void displayInfo(){

    }
}
