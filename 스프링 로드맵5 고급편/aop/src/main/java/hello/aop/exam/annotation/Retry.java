package hello.aop.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    int value() default 3;
    /**
     * 실무에서 Server - Server로 통신하는데, 서버끼리 튈 때가 있다.
     * 그래서 Retry를 사용하는데, 절대로 무한 요청을 가도록 하면 안되고 기본 값이 있어야한다.
     */
}
