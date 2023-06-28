package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.discount.FixDiscountPolicy;
import com.example.SpringBasicReview.domain.Grade;
import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.domain.Order;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 가상 Repository를 사용하여 테스트 가능
        // 당연하게도 생성자 주입을 사용할것.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}