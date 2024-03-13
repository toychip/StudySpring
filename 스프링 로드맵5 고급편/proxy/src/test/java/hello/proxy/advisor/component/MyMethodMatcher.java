package hello.proxy.advisor.component;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodMatcher;

@Slf4j
public class MyMethodMatcher implements MethodMatcher {
    private static final String MATCH_NAME = "save";

    @Override
    public boolean matches(final Method method, final Class<?> targetClass) {
        boolean result = method.getName().equals(MATCH_NAME);
        log.info("포인트컷 호출 method={} targetClass={}", method.getName(), targetClass);
        log.info("포인트컷 결과 result={}", result);
        return result;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(final Method method, final Class<?> targetClass, final Object... args) {
        return false;
    }
}
