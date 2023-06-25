package com.example.SpringBasicReview;

import com.example.SpringBasicReview.discount.DiscountPolicy;
import com.example.SpringBasicReview.discount.FixDiscountPolicy;
import com.example.SpringBasicReview.discount.RateDiscountPolicy;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import com.example.SpringBasicReview.service.OrderService;
import com.example.SpringBasicReview.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call Appconfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call Appconfig.orderService");
        return new OrderServiceImpl(
                memberRepository(), getDiscountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call Appconfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy getDiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
