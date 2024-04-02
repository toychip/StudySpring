package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})   // JDK 동적 프록시
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})   // CGLIB
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    /**
     * JDK Proxy를 사용하면 항상 인터페이스로 의존관계 주입을해야한다.
     * JDK Proxy는 구체 클래스가 무엇인지 전혀 모르기 떄문에 캐스팅 자체가 안된다.
     */

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass()); // JDK 동적 프록시로 실행시에 오류
        memberService.hello("hello");
    }
}
