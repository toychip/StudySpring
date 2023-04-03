package WAS.itemservice.web.argumentresolver;

import WAS.itemservice.domain.member.Member;
import WAS.itemservice.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {           // 지원을 하는가?
        log.info("supportsParameter 실행");
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(Login.class);     //로그인 에노테이션이 파라미터에 있느냐?
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasMemberType && hasParameterAnnotation;
//        true면 밑에 resolveArgument가 실행, flase면 실행하지 않음.
    }

    @Override               // 지원을 한다면 어떤 값을 넣어줄것인가?
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;    // session이 null이면 @Login Member loginMember 에다가 null을 넣을것이다.
        }


//        Object member = session.getAttribute(SessionConst.LOGIN_MEMBER);
//        return member; 이 둘을 합쳐서 아래로 표현한 것
        return session.getAttribute(SessionConst.LOGIN_MEMBER);

//
    }
}
