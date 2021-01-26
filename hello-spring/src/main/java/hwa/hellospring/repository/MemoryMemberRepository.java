package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store= new HashMap<>();
    private static long sequence =0L;

    @Override
    public Member join(Member member) {
        member.setSeq(++sequence);
        store.put(member.getSeq(),member);
        return member;
    }

    @Override
    public Optional<Member> login(String user_id,String password)
    {
        return Optional.ofNullable(store.get(user_id));
    }

    @Override
    public Boolean userDelete(String user_id,String user_pw)
    {
        return true;
    }

    @Override
    public Boolean userModifying(Member member)
    {
        return true;
    }

    @Override
    public Optional<Member> findById(String user_id) {
        return Optional.ofNullable(store.get(user_id));
    }

    @Override
    public Optional<Member> findByInfo(String user_id) {
        return store.values().stream()
                .filter(member->member.getName().equals(user_id))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

     public void clearStore()
     {
         store.clear();
     }
}
