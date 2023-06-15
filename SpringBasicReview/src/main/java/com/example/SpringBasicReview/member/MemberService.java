package com.example.SpringBasicReview.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
