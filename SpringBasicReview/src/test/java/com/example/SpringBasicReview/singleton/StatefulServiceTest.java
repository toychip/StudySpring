package com.example.SpringBasicReview.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB: B사용자 20000원 주문
        int userAPrice2 = statefulService2.order("userB", 20000);

        //ThreadA: 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);


        Assertions.assertThat(userAPrice).isNotEqualTo(userAPrice2);

    }

    static class Testconfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }
}