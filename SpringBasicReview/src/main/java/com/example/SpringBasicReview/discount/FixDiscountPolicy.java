package com.example.SpringBasicReview.discount;

import com.example.SpringBasicReview.domain.Grade;
import com.example.SpringBasicReview.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
        return 0;
        }
    }
}
