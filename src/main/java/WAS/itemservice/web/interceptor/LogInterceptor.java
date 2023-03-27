package WAS.itemservice.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        // @RequestMapping: HandlerMethod가 넘어옴\
        // 정적 리소스: ResourceHttpHandler가 사용됨
        if (handler instanceof HandlerMethod) { // 타입이 맞는지 확인
            HandlerMethod handlerMethod = (HandlerMethod) handler;// 호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
//            handlerMethod.get~
        }

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        return true;
//        false 면 여기서 stop, true면 다음 postHandle을 호출
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String requestURI = request.getRequestURI();
        Object logId = (String)request.getAttribute(LOG_ID);// 위 preHandle이 실행되고, 성공을 하던 안하던 afterCompletion은 항상 실행되기 때문에 저기서 설정한 uuid의 값을 받는다.

        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);

        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
