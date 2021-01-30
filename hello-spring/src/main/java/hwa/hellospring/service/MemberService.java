package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.repository.JdbcMemberRepository;
import hwa.hellospring.repository.MemberRepository;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public boolean login(Member member, Model model)
    {
        return memberRepository.login(member.getUser_id(), member.getPassword()).isEmpty();
    }

    public Boolean findInfo(String user_id)
    {
        return memberRepository.findByInfo(user_id).isEmpty();
    }

    public Boolean userDelete(String user_id,String user_pw)
    {
        return memberRepository.userDelete(user_id,user_pw);
    }

    public Boolean userModifying(Member member)
    {
        return memberRepository.userModifying(member);
    }

    public boolean validateDuplicateMember(String user_id) {//이름이 중복되는 회원이 있는 경우
        return memberRepository.findById(user_id).isEmpty();
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
