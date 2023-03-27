package WAS.itemservice.web;

import WAS.itemservice.domain.member.Member;
import WAS.itemservice.domain.member.MemberRepository;
import WAS.itemservice.web.argumentresolver.Login;
import WAS.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
    //    @GetMapping("/")
    public String home() {
        return "home";
    }


//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.
        if (memberId == null){
            return "home";
        }
        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.

        Object member = (Member)sessionManager.getSession(request);    // Object이기 때문에 캐스팅

        //로그인
        if(member == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.

        HttpSession session = request.getSession(false);    // 로그인 하기 전이므로 false를 사용하여 세션을 바로 만들어주면 안된다.
        if (session == null) {
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        //로그인
        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }


//    @GetMapping("/")                                                                    // 값이 없을수도 있을수도 있으므로
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                        Member loginMember, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.

        //로그인
        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")                                                                    // 값이 없을수도 있을수도 있으므로
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.

        //로그인
        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}