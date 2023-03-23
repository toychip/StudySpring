package WAS.itemservice.web;

import WAS.itemservice.domain.member.Member;
import WAS.itemservice.domain.member.MemberRepository;
import WAS.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){ // 여기서 reqired = false 란 이 파라미터를 필수 값으로 받을 것인지에 대한 설정임.

        Object member = (Member)sessionManager.getSession(request);    // Object이기 때문에 캐스팅

        //로그인
        if(member == null) { // db에 없는 경우, 쿠키가 너무 오래됐거나 등
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}