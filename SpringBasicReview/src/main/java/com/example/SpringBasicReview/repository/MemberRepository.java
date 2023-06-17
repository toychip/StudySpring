package com.example.SpringBasicReview.repository;

import com.example.SpringBasicReview.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
