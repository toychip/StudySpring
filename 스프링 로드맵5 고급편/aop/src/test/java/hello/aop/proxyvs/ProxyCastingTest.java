package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(false);    // JDK 동적 프록시 사용 (기본)

        // 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        /** JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패
         * 너무나 당연하게도 MemberServiceImpl은 MemberService interface를
         * 구현한 것이기 때문에 JDK 동적 프록시는 MemberServiceImpl이 뭔지 모름
         * ClassCastException
         */
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemverService = (MemberServiceImpl) memberServiceProxy;
        });
    }

    @Test
    void chlibProxy() {
        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(true);    // CGLIB 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        // CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
        MemberServiceImpl castingMemverService = (MemberServiceImpl) memberServiceProxy;
    }
}
