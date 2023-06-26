package com.example.SpringBasicReview;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // @ComponentScan을 하는데 @Configuration 붙은 것은 제외함.
        // @Componet 애노테이션을 붙이면 스프링 빈에 등록을 함 @Configuration Vs @ComponetScan 의 차이 알기
)
// 수동으로 @Bean한게 없음
public class AutoAppConfig {

}
