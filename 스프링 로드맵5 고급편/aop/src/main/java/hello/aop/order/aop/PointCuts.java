package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    // hello.aop.order 패키지와 하위 패키지 전부
    @Pointcut("execution(* hello.aop.order..*(..))")  // point
    public void allOrder() {
    } // pointcut 시그니처라고 함

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")  // service
    public void allService() {
    }

    // allOrder && allService 조합해서 사용
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}


}
