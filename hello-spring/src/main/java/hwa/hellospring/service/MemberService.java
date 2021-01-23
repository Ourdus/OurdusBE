package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
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


    public Long join(Member member)
    {
        validateDuplicateMember(member);//같은 이름이 있는 중복 회원 불가능
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {//이름이 중복되는 회원이 있는 경우
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원 입니다");
        });
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }
}
