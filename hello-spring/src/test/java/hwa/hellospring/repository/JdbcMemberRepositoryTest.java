package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class JdbcMemberRepositoryTest {

    @Autowired
    private MemberRepository repository;
    private DataSource dataSource;





    @AfterEach
    public void afterEach()
    {
       // repository.clearStore();
    }

    @Test
    void join() {
        Member member=new Member();
        member.setName("spring");
        member.setPassword("1234");
        member.setUser_id("hello");
        member.setUser_email("ddd@naver.com");
        member.setUser_point(0);
        member.setUser_tel("010-1111-1111");

        repository.join(member);
        Member result=repository.findById(member.getUser_id()).get();

        Assertions.assertEquals(member,result);

    }

    @Test
    void login() {
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