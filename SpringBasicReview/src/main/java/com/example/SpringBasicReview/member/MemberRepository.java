package com.example.SpringBasicReview.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
