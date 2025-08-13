package com.nhnacademy.cannongame;

// n차원 벡터 추상 클래스
public abstract class Vector {

    // 특정 인덱스 값 가져오기
    public abstract double get(int index);

    // 특정 인덱스 값 설정
    public abstract void set(int index, double value);

    // 새 벡터 생성 (Factory Method)
    public abstract Vector createNew();

    // 벡터 크기 계산 √(Σ(vᵢ²))
    public double magnitude() {
        double sum = 0;
        for (int i = 0; i < dimension(); i++) {
            sum += get(i) * get(i);
        }
        return Math.sqrt(sum);
    }

    // 벡터 정규화
    public Vector normalize() {
        double mag = magnitude();
        if (mag == 0) return createNew(); // 영벡터 반환
        Vector result = createNew();
        for (int i = 0; i < dimension(); i++) {
            result.set(i, get(i) / mag);
        }
        return result;
    }

    // 내적 계산
    public double dot(Vector other) {
        double sum = 0;
        for (int i = 0; i < dimension(); i++) {
            sum += this.get(i) * other.get(i);
        }
        return sum;
    }

    // 차원
    public abstract int dimension();
}
