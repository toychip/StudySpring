package WAS.itemservice.web.login;

import WAS.itemservice.domain.login.LoginService;
import WAS.itemservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response){

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        로그인 성공 처리

//        쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);
        /*
      로그인에 성공하면 쿠키를 생성하고 HttpServletResponse에 담는다. 쿠키 이름은 memberId이고,
      같은 회원의 id를 미리 담아둔다. 웹 브라우저는 종료 전까지 회원의 'id'를 서버에 계속 보내줄 것이다.
         */
        return "redirect:/";
    }

}
