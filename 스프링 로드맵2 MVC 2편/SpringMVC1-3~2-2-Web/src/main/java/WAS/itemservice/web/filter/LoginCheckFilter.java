package WAS.itemservice.web.filter;

import WAS.itemservice.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            log.info("인증 체크 필터 시작{}", requestURI);

            if(isLoginChechPath(requestURI)){       // 로그인을 체크해야하는 경로인가?
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청{}", requestURI);
                    //로그인으로  redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    // 미인증 사용자가 요청하면 redirect를 한 후 로그인이 성공하면 그 뒤에 ?redirectURL=을 사용하여 처음에 시도했던 링크로 들어오게 함
                    return; // 더이상 안해줄거야 끝내.요청이 끝남.
                }
            } chain.doFilter(request, response);     // 로그인을 체크해야하는 경로가 아니면(화이트 체크리스트가 아니면)  넘어갈 수 있게 해줌
        } catch (Exception e){
            throw e;    // 예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 한다.
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /*
    화이트 리스트의 경우 인증 체크 x
     */
    private boolean isLoginChechPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
