package hello.proxy.advisor.component;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class Advice1 implements MethodInterceptor {
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        log.info("advice1 호출");
        return invocation.proceed();
    }
}
