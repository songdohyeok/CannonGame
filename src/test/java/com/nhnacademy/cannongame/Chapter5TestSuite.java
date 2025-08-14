package com.nhnacademy.cannongame;

// 테스트 러너 예제 (필요시 사용)
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ShapeTest.class,
        Vector2DExtendTest.class,
        AbstractBallTest.class,
        BoundsFactoryTest.class
})
public class Chapter5TestSuite {
    // 테스트 슈트 - 5장의 모든 테스트를 한 번에 실행
}