package com.example.SpringBasicReview;

import com.example.SpringBasicReview.discount.FixDiscountPolicy;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import com.example.SpringBasicReview.service.OrderService;
import com.example.SpringBasicReview.service.OrderServiceImpl;

public class Appconfig {

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                getMemberRepository(), getDiscountPolicy()
        );
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    private FixDiscountPolicy getDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}
