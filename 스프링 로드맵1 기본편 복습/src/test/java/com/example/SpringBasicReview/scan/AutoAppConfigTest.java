package com.example.SpringBasicReview.scan;

import com.example.SpringBasicReview.AutoAppConfig;
import com.example.SpringBasicReview.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // 여기서 AutoAppConfig에는 아무런 설정 정보가 없다. @ComponetScan만 존재할 뿐

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
