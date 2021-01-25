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
        memberRepository.join(member);
        return member.getUser_id();
    }

    public boolean login(String user_id,String password)
    {
        return memberRepository.login(user_id, password).isEmpty();
    }

    public Boolean findInfo(String user_id)
    {
        return memberRepository.findByInfo(user_id).isEmpty();
    }

    public Boolean userDelete(String user_id,String user_pw)
    {
        return memberRepository.userDelete(user_id,user_pw);
    }

    public boolean validateDuplicateMember(String user_id) {//이름이 중복되는 회원이 있는 경우
        Boolean checker;
        checker=memberRepository.findById(user_id).isEmpty();
        return checker;
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
