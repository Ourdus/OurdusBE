package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach()
    {
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore();
    }
    @Test
    void join() {

       //given
       Member member=new Member();
       member.setName("spring");

       //when
        Long saveId=memberService.join(member);

        //then
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void RedundantMember()
    {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //메세지 받아서 검증
        IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThrows(IllegalStateException.class,()->memberService.join(member2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}