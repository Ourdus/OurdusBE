package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;
import hwa.hellospring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//트랜잭션 실행후 test가 끝나면 rollback
class JdbcMemberRepositoryTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach()
    {
       // repository.clearStore();
    }

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
        Member findMember=memberService.findOne(saveId).get();
        assertThat(saveId).isEqualTo(findMember.getUser_id());

    }

    @Test
    void login() {

        //given
        Member member=new Member();
        member.setName("spring");
        member.setPassword("1234");
        member.setUser_id("qq");
        member.setUser_email("ddd@naver.com");
        member.setUser_point(0);
        member.setUser_tel("010-1111-1111");

        //when
        Boolean saveId=memberService.login(member.getUser_id(),member.getPassword());

        //then
        Member findMember=memberService.findOne(member.getUser_id()).get();
        assertThat(member.getUser_id()).isEqualTo(findMember.getUser_id());

    }

    @Test
    void userDelete() {
    }

    @Test
    void userModifying() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByInfo() {
    }
}