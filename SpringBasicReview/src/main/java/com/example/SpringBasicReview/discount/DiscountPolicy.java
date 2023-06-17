package com.example.SpringBasicReview.discount;

import com.example.SpringBasicReview.domain.Member;

public interface DiscountPolicy {

    /**
     * @return discount price
     */
    int discount(Member member, int price);
}
