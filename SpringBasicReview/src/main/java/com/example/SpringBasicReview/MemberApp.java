package com.example.SpringBasicReview;

import com.example.SpringBasicReview.member.Grade;
import com.example.SpringBasicReview.member.Member;
import com.example.SpringBasicReview.member.MemberService;
import com.example.SpringBasicReview.member.MemberServiceImpl;


public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
