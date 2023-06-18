package com.example.SpringBasicReview;

import com.example.SpringBasicReview.domain.Grade;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.domain.Order;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import com.example.SpringBasicReview.service.OrderService;
import com.example.SpringBasicReview.service.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        Appconfig appconfig = new Appconfig();
        MemberService memberService = appconfig.memberService();
        OrderService orderService = appconfig.orderService();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.caculate() = " + order.caculate());
    }
}
