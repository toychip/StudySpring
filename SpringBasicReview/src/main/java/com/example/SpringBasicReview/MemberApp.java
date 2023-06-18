package com.example.SpringBasicReview;

import com.example.SpringBasicReview.domain.Grade;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;


public class MemberApp {

    public static void main(String[] args) {

        Appconfig appconfig = new Appconfig();
        MemberService memberService = appconfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
