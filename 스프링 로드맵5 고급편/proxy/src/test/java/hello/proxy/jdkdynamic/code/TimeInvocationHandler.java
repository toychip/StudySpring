package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(final Object target) {
        this.target = target;
    }

    @Override                                                                // args: method 의 파라미터
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // method 호출을 동적으로 사용하기 때문에 이것이 가능함
        Object result = method.invoke(target, args);    // call, 이때 동적으로 실행

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
