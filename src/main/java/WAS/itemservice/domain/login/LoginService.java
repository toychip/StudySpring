package WAS.itemservice.domain.login;

import WAS.itemservice.domain.member.Member;
import WAS.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

//    return NULL이면 로그인 실패
    public Member login(String loginId, String passWord){

        /*
        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);// 로그인한 아이디의 회원이 있는지 우선 확인
        Member member = findMemberOptional.get();
        if(member.getPassword().equals(passWord)){
            return member;
        } else {
            return null;
        }

        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        byLoginId.filter(m -> m.getPassword().equals(passWord))
                .orElse(null);
        */
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(passWord))
                .orElse(null);

    }
}
