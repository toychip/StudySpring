package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired      // 자동 연결 해줌 Component를 하면 Bean은 등록이 되지만 의존관계를 수동으로 등록할수 없기 때문에 Autowired를 쓴다
    // ac.getBean(MemberRepository.class)와 같은 기능
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 가입을 하고 회원을 찾는것
    // interface만 갖고 있으면 null로 반환됨. 구현 객체 선택, memorymemberrepository
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
