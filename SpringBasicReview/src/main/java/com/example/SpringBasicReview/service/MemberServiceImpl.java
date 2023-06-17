package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.domain.Member;
import com.example.SpringBasicReview.repository.MemberRepository;
import com.example.SpringBasicReview.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
