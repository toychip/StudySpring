package com.example.SpringBasicReview.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient bean = ac.getBean(NetworkClient.class);

        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        // Spring에 의존 x, 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드 적용 가능
        // destroyMethod의 default는 inferred(추론) 이다. 이것이 자동으로 close와 shutdown 등 이름의 메서드를 자동으로 호출해줌.
        // 자동 종료 메서드
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://test-spring.dev");
            return networkClient;
        }
    }
}
