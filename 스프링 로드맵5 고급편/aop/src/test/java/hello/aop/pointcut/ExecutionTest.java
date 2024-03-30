package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        // 테스트에서 실행할 수 있게 getMethod()를 추출해서 helloMethod에 보관
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {

        // 앞으로 알아볼 execution으로 시작하는 포인트컷 표현식은 이 메서드 정보를 매칭해서 포인트컷 대상을 찾아낸다.
        // execution(* ..package..Class.)
        // public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    /**
     * *매칭 조건*
     * '?' 는 생략이 가능
     * 접근제어자?:public
     * 반환타입: String
     * 선언타입?: hello.aop.member.MemberServiceImpl
     * 메서드이름: helLo
     * 파라미터: (String)
     * 예외?: 생략I
     */
    @Test
    @DisplayName("가장 정확한 포인트 컷")
    void exactMatch() {
        // public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)   예외는 생략
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");

        // pointcut의 조건이 True냐?
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * *매칭 조건*
     * 접근제어자?: 생략
     * 반환타입:'*'
     * 선언타입?: 생략
     * 메서드이름: ‘*'
     * 파라미터: (..)
     * 예외?: 없음
     */
    @Test
    @DisplayName("가장 많이 새약한 포인트 컷")
    void allMatch() {
        // 반환타입: 전체, 메서드이름: 전체, 파라미터: 전체
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름으로 매칭")
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름으로 매칭하는데 패턴으로 확인")
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름으로 매칭하는데 패턴으로 확인")
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("매칭에 실패하는 케이스")
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("패키지 이름으로 정확히 매칭")
    void packageExactMatch1() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..)))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("패키지 이름을 *로 줄임")
    void packageExactMatch2() {
        pointcut.setExpression("execution(* hello.aop.member.*.*(..)))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("정확한 패키지 이름이 아니기 때문에 실패함. 아래에서 이를 해결하는법을 적용")
    void packageExactFalse() {
        pointcut.setExpression("execution(* hello.aop.*.*(..)))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("정확한 패키지 이름이 아니기 때문에 실패함. 아래에서 이를 해결하는법을 적용")
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* hello.aop.member..*.*(..)))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("hello aop와 그것의 하위에 있는 모든 것")
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* hello.aop..*.*(..)))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 패키지에서의 차이를 이해해야 한다.
     * . : 정확하게 해당 위치의 패키지
     * ..: 해당 위치의 패키지와 그 하위 패키지도 포함
     */
}
