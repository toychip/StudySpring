package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String [] petterns;

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

        // 메서드 이름 필터
        String methodName = method.getName();

        //save, request, reque*, test

        if (!PatternMatchUtils.simpleMatch(petterns, methodName)) {
            // 매칭이 되지 않으면 그냥 끝냄 ( 특정 메서드 이름이 매칭 되는 경우에만 LogTrace 로직 실행, 이름이 매칭되지 않으면 실제 로직 바로 호출)
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);
            // 로직 호출
            /*
               어떠한 객체가 들어오던 들어온 객체 target.method() 이렇게 진행되어 있을 때
               여기서 target은 실제 객체, method는 실행할 메서드가 되며, result는 method의 반환값이 된다.
               단점: nolog 또한 실행되므로 적용2 강의에서 이를 제외하는법을 학습
             */
            Object result = method.invoke(target, args);
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
