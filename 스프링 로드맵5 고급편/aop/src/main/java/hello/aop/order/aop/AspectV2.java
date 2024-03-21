package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    // hello.aop.order 패키지와 하위 패키지 전부
    @Pointcut("execution(* hello.aop.order..*(..))")  // point
    private void allOrder() {} // pointcut 시그니처라고 함

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{    // Advice
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    @Around("allOrder()")
    public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable{    // Advice
        log.info("나는 dolog2 호출이야! {}", joinPoint.getSignature()); // join point 시그니처
        Object result = joinPoint.proceed();
        log.info("methodname: {}, dolog2 끝!", joinPoint.getSignature().toShortString());
        return result;
    }
}
