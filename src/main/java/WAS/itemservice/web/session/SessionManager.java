package WAS.itemservice.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/*
session 관리, 직접 만들어보기
 */
@Component
public class SessionManager {
    // 자주 사용해서 상수로 선언
    public static final String SESSION_COOKIE_NAME = "mysessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /*
    세션 생성
     *세션 생성
     * 세션 저장소에 sessionId와 보관할 값 저장
     * sessionId로 응답쿠키를 생성해서 클라이언트에 전달
     */
    public void createSession(Object value, HttpServletResponse response) {
        // 세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

         // 쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);


    }

    // 세션 조회

    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }

        return sessionStore.get(sessionCookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {

        /*
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        위 코드를 아래로 합침.
        */
        if (request.getCookies() == null){
            return null;
        }
        return Arrays.stream(request.getCookies()) // 배열을 stream으로 바꿔줌, stream을 바꿨기때문에 filter를 적용할 수 있음
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()       // findAny -> 순서와 상관 없이 제읾 먼저 나온애
                .orElse(null);           // findFirst -> 순서를 신경쓰면서 제일 먼저 나온애
    }

    // 세션 만료

    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
