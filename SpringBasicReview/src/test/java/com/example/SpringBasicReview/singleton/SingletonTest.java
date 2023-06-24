package com.example.SpringBasicReview.singleton;

import com.example.SpringBasicReview.Appconfig;
import com.example.SpringBasicReview.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureCOntainer() {
        Appconfig appconfig = new Appconfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appconfig.memberService();
        MemberService memberService2 = appconfig.memberService();

        //2. 참조값 비교
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // 참조값이 다름. 계속 새로운 객체 생성.

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
