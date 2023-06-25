package com.example.SpringBasicReview.domain;

import com.example.SpringBasicReview.Appconfig;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import com.example.SpringBasicReview.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberServiceMemberRepository = memberService.getMemberRepository();
        MemberRepository orderServiceMemberRepository = orderService.getMemberRepository();


        System.out.println("memberService -> memberRepository = " + memberServiceMemberRepository);
        System.out.println("orderService -> memberRepository = " + orderServiceMemberRepository);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}
