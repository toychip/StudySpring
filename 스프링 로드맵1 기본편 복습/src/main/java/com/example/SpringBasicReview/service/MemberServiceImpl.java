package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Component("memberService2") 이름 지정 가능
//@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    //@Autowired  // @Componet가 스프링 컨테이너에 자동으로 등록해주므로 어떤걸 의존관계 주입해야할지 알 수 없음.
                // 생성자에 Autowired를 붙이므로써 해결, 생성자가 하나면 @Autowired가 생략되어 있는 것임.
                // 의존 관계 자동 주입
                // 마치 ac.getBean(MemberRepository.class) 처럼 동작

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
