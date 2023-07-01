package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.annotaion.MainDiscountPolicy;
import com.example.SpringBasicReview.discount.DiscountPolicy;
import com.example.SpringBasicReview.discount.FixDiscountPolicy;
import com.example.SpringBasicReview.discount.RateDiscountPolicy;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.domain.Order;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;    // final을 사용하여 필수 값이라고 설정을 한것.

    @Autowired  // 생성자가 하나일 경우 @Autowired 생략되어 있음. 자동으로 붙여 줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    private final DiscountPolicy discountPolicy = new FixDiscoun tPolicy(); 할인 정책 수정
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
