package MVC.servlet.web.frontcontroller.v4.controller;

import MVC.servlet.domain.member.Member;
import MVC.servlet.domain.member.MemberRepository;
import MVC.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));    // parameter에서 값 꺼내고

        Member member = new Member(username, age);          // ㅁ비즈니스 로직 실행하고
        memberRepository.save(member);

        model.put("member", member);                     //model에다가 값 put으로 해주고
        return "save-result";                               // return 을 문자로 해주면 끝.
    }
}
