package MVC.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();  // 테스트가 끝날때 마다 초기화
    }

    @Test
    void savedMember(){

        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findByid(savedMember.getId());
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findAllMember() {
    //given
    Member member1 = new Member("임준형", 24);
    Member member2 = new Member("김경민", 25);

    //when
    memberRepository.save(member1);
    memberRepository.save(member2);

    //then
    List<Member> memberList = memberRepository.findAll();
    assertThat(memberList.size()).isEqualTo(2);
    assertThat(memberList).contains(member1, member2);
    }

}