package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {
    /**
     * 아래 로직은 5번의 로직 전부다 실패했을 때만 exceptionHolder에 있는 객체로 예외를 던진다.
     * 여기서 value (재시도 가능한 횟수)가 4로 넘어왔으므로 4번 더 재시도하는데, 다시 한 번 했을 때
     * 바로 성공했으므로 더이상 재시도하지 않고 로직이 종료된다.
     */

    //    @Around("@annotation(hello.aop.exam.annotation.Retry)")
    //    public Object doRetry(ProceedingJoinPoint joinPoint) throws Throwable {
    //    아래와 같이 파라미터를 직접 받도록 응용
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 0; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
