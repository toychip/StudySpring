package com.example.SpringBasicReview;

import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "com.example.SpringBasicReview",     // 컴포넌트 스캔 범위 설정 가능
//        basePackageClasses = AutoAppConfig.class, // 클래스 단위로도 가능
//        위와 같이 지정하지 않으면 @ComponentScan을 적은 곳, 여기서는 AutoAppConfig 가 속한 패키지가 지정이 됨
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // @ComponentScan을 하는데 @Configuration 붙은 것은 제외함.
        // @Componet 애노테이션을 붙이면 스프링 빈에 등록을 함 @Configuration Vs @ComponetScan 의 차이 알기
)
// 수동으로 @Bean한게 없음
public class AutoAppConfig {

    // 같은 이름의 빈이 2개이므로 충돌 남, 학습용으로 공부했던 것이므로 주석처리
//    @Bean(name = "memoryMemberRepository")  // overriding을 해서 수동 등록이 우선권을 가짐
////                                              의도하지 않은 경우가 대부분
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}




