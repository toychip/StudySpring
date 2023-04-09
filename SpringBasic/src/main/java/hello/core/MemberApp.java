package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MemberApp {
    public static void main(String[] args) {


//        AppConfig appConfig = new AppConfig();            스프링으로 바꿀것이므로 주석처리함.
//        MemberService memberService = appConfig.memberService();


//      MemberService memberService = new MemberServiceImpl();


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 이렇게하면 Appconfig에 가지고있는 정보를 가지고 스프링이 빈에있는 것을 스프링 컨테이너에다가 전부 집어놓고 관리를 해줌.

        // 이제는 AppllicationContext를 통해서 정보를 갖고 와야 함.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //                Bean으로 등록한 메서드의 갖고 올 이름, 갖고올 것 타입 적어주기.
        // getBean에 마우스 커서 옮기고 commad + option + v



        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
    // 관심사의 분리 2분 30초부터 수강하면 됨.
}
