package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

    //data clear -> 각각 메소드 실행 후 데이터를 지운다 (그렇지 않으면 의존성이 생겨서 문제 발생)
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save()
    {
        Member member=new Member();
        member.setName("spring");

        repository.save(member);
        Member result=repository.findById(member.getUser_id()).get();

        Assertions.assertEquals(member,result);
        //assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        assertThat(result).isEqualTo(result);
    }
    @Test
     public void findAll()
    {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
