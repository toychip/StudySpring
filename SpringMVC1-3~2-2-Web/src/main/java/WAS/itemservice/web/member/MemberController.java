package WAS.itemservice.web.member;

import WAS.itemservice.domain.member.Member;
import WAS.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;


    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult result){
        if(result.hasErrors()){     //오류가 나면
            log.info("saveId{}", result);
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
