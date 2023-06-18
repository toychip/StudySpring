package com.example.SpringBasicReview;

import com.example.SpringBasicReview.domain.Grade;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MemberApp {

    public static void main(String[] args) {

//        1. Appconfig를 쓰지 않은 방식 MemberService memberService = new MemberServiceImpl();

//        2. Appconfig를 써서 역할과 구현을 구분하는 방식
//        Appconfig appconfig = new Appconfig();
//        MemberService memberService = appconfig.memberService();
//         Spring이 전부 해결해줌

//        3. Spring @Configuration 스프링 컨테이너에 등록하기
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class); // 메서드 이름으로 꺼내고, 밤환 타입 정하기


        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
