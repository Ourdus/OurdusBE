package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional//트랜잭션 실행후 test가 끝나면 rollback
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {

       //given
        Member member=new Member();
        member.setName("spring");
        member.setPassword("1234");
        member.setUser_id("qq");
        member.setUser_email("ddd@naver.com");
        member.setUser_point(0);
        member.setUser_tel("010-1111-1111");

       //when
        String saveId=memberService.join(member);

        //then
        Member findMember=memberService.findOne(member.getUser_id()).get();
        assertThat(member.getUser_id()).isEqualTo(findMember.getUser_id());
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