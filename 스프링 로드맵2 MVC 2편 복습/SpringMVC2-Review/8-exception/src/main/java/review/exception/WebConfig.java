package review.exception;

import org.apache.catalina.User;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import review.exception.filter.LogFilter;
import review.exception.interceptor.LogInterceptor;
import review.exception.resolver.MyHandlerExceptionResolver;
import review.exception.resolver.UserHandlerExceptionResolver;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }

    // Filter
    // @Bean

    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        // 아래의 2가지 경우에 필터가 호출이 된다.
        // 처음에는 REQUEST로, 그 후에 다시 오류가 났을 때는 DispatcherType이 ERROR로 나오게된다.
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
    // Interceptor

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*.ico", "/error")
                .excludePathPatterns("/error-page/**"); // 오류 페이지 경로
                // 인터셉터는 DispatcherType 같은 것이 없다.
    }
}
