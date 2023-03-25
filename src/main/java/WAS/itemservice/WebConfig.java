package WAS.itemservice;

import WAS.itemservice.web.filter.LogFilter;
import WAS.itemservice.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

// LogFilter를 사용할 수 있게 등록해주는 거
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter(){  //WAS를 띄울때 filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");    //어떤 url패턴에 할거냐? /* -> 모든 url

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter(){  //WAS를 띄울때 filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");    //어떤 url패턴에 할거냐? /* -> 모든 url

        return filterRegistrationBean;
    }
}
