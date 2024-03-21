package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {
    /**
     * Around가 가장 강력한 기능, 모든 애노테이션을 합친 것이다.
     * Around는 ProceedingJoinPoint 매개 변수가 필수이다.
     * 다음 어드바이스나 타켓을 호출하는 메서드인 joinPoint.proceed()를 직접 실행해야한다.
     */
    @Around("hello.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before  // - 이곳만 작성 가능
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning // - 이곳만 작성 가능
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing // - 이곳만 작성 가능
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After // - 이곳만 작성 가능
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
//    public void doBefore(ProceedingJoinPoint joinPoint)
//    ProceedingJoinPoint 사용 불가, joinPoint.proceed(); 직전까지만 작성 가능
//    아래와 같이 작성하면 자동으로 joinPoint.proceed();를 실행하기 떄문에, 간단하게 작성 가능
//    매개변수로 joinPoint를 받아도 되고, 안 받아도 됨
//    @Around와 다르게 작업 흐름을 변경할 수 없다.
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    // 여기서 returning = "result" 이렇게 작성 했는데, 메서드의 반환 결과가 "result"에 들어온다.
    // 또한, 가장 중요한 하나로는 반환 값으로 받은 returning된 값을 임의로 바꿀 수 "없다" 그대로 사용해야한다.
    // 반환 값 자체를 바꾸고 싶다면 @Around를 사용해야한다.
    // Object 타입으로 선언했지만, 같은 타입만을 반환하도록 응용해서 사용할 수도 았음
    @AfterReturning(value = "hello.aop.order.aop.PointCuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return = {}", joinPoint.getSignature(), result);
    }

    // 위와 마찬가지로 예외 이름을 적어주어야한다. 여기선 "ex"로 설정함
    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message = {}", joinPoint.getSignature(), ex);
    }

    // finally와 같은 효과
    // 일반적으로 리소스를 헤제하는데 사용
    @After(value = "hello.aop.order.aop.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
