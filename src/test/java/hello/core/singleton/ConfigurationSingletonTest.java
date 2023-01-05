package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {


    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        // 다른 것이 생성되는 것이 아닌 같은 것으로 생성되는 것이 진짜 맞을까?
    }

    @Test
    void configurationdeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);       // 얘도 스프링 빈으로 등록이된다.

        System.out.println("been = " + bean.getClass());
        //been = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$275712d6
        // 내가만든 AppConfig가 아니라 AppCofig를 상속 받은 것에 임의의 같은 것을 만들고 @CGIB를 만들어 그것을 스프링 컨테이너에 등록한다.

        // @Configuration 이 없으면 우리가 기존 생각한 AppConfig가 등록된다.
        // call이 3번 불러짐
        // 하지만 싱글톤을 보장하는 것 즉 CGLIB가 사용되지 않아 비용 낭비
        // 이것은 스프링 빈이 아니다. 직접 new한 것이기 때문에 스프링 빈이 등록되지도 않는다.
        // Autowired와 같다
//        Appconfig@CGLIB 예상코드
//        @Bean
//        public MemberRepository memberRepository() {
//            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) { return 스프링 컨테이너에서 찾아서 반환;
//            } else { //스프링 컨테이너에 없으면
//                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록 return 반환
//            } }

    }
}
