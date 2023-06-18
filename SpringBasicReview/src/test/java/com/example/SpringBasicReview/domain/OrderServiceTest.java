package com.example.SpringBasicReview.domain;

import com.example.SpringBasicReview.Appconfig;
import com.example.SpringBasicReview.service.MemberService;
import com.example.SpringBasicReview.service.MemberServiceImpl;
import com.example.SpringBasicReview.service.OrderService;
import com.example.SpringBasicReview.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void forEach() {
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }

    @Test
    void createOrder() throws Exception {
        //given
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);
        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
