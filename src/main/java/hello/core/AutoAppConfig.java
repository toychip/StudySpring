package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 전부 긁어모아와서 자동으로 ComponentScan을 해줄건데 그 중에서 안할것을 설정하는 것
        // 그동안 작성한 예제를 안전하게 유지하기 위해서 뺏다
)
public class AutoAppConfig {
}
