package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
        // 공통 로직1 종료

    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    @Test
    void reflection1()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 클래스 메타 정보 휙득, 내부 클래스는 구분을 위해 "$"사용
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        //callB 메서드 정보
        String parameter = "callB";
        Method methodCallB = classHello.getMethod(parameter);
        Object result2 = methodCallB.invoke(target);
        log.info("result1={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 메타 정보 휙득, 내부 클래스는 구분을 위해 "$"사용
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        /**
         * String result1 = target.callA();
         * 기존에는 위와 같이 메서드가 정해져있으므로 수정할 수 없었는데,
         * 메타데이터를 사용하여 아래와 같이 추상화를 사용하여 동적으로 해결하는 기술이 리플렉션이다.
         */
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
}
