package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.repository.JdbcMemberRepository;
import hwa.hellospring.repository.MemberRepository;
import hwa.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository=memberRepository;
    }


    public String join(Member member)
    {
        validateDuplicateMember(member.getUser_id());//같은 이름이 있는 중복 회원 불가능
        memberRepository.save(member);
        return member.getUser_id();
    }

    public String validateDuplicateMember(String user_id) {//이름이 중복되는 회원이 있는 경우
        memberRepository.findById(user_id);
        return "hello";
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId)
    {
        return memberRepository.findById(memberId);
    }
}
