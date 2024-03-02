package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.domain.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
