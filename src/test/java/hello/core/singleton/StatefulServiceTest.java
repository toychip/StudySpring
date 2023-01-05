package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("userA", 10000);
        //ThreadA: a사용자가 10000원 주문

        statefulService2.order("userB", 20000);
        //ThreadB: b사용자가 20000원 주문

        //특정 클라이언트가 값을 변경하므로 문제가 생김. 변경하지 못하게 해야한다고 처음에 알려줌.

        // ThreadA: 사용자 a 주문 금액 조회
        int price = statefulService1.getPrice();
        // a가 주문한 후, a가 조회하는 사이에 b가 들어와서 주문을 한 것

        System.out.println("price = " + price);
        //기대 값은 10000원이지만 b가 주문한 금액이 조회되었음

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}