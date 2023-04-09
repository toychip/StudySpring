package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy{

    int discount(Member member, int price);
    // return 할인 대상 금액
}
