package WAS.itemservice.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

//        ServletRequest : httpServletRequest의 부모이다. 기능이 별로 없으니 다운캐스팅이 필요함.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 모든 사용자의 uri를 받음

        String uuid = UUID.randomUUID().toString(); // 사용자들을 구분함
        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response); // 있으면 다음필터 호출, 없으면 servlet호출
        }catch (Exception e) {
            throw e; // 예외가 발생하면 throw를 던져버림
        }finally {
            log.info("RESPONSE[{}][{}]", uuid, requestURI);
        }

    }

    @Override
    public void destroy() {
        log.info("log filter destory");
    }
}
