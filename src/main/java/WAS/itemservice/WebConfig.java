package WAS.itemservice;

import WAS.itemservice.web.filter.LogFilter;
import WAS.itemservice.web.filter.LoginCheckFilter;
import WAS.itemservice.web.interceptor.LogInterceptor;
import WAS.itemservice.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

// LogFilter를 사용할 수 있게 등록해주는 거
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**") // 하위는 전부다
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 이 경로는 먹이지 마

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login",
                        "/logout", "/css/**", "/*.ico", "/error");

    }

//    @Bean 사용 안 함
    public FilterRegistrationBean logFilter(){  //WAS를 띄울때 filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");    //어떤 url패턴에 할거냐? /* -> 모든 url

        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean loginCheckFilter(){  //WAS를 띄울때 filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");    //어떤 url패턴에 할거냐? /* -> 모든 url

        return filterRegistrationBean;
    }
}
