package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.discount.DiscountPolicy;
import com.example.SpringBasicReview.discount.FixDiscountPolicy;
import com.example.SpringBasicReview.discount.RateDiscountPolicy;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.domain.Order;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); 할인 정책 수정
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;  // 현재 NPE

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
