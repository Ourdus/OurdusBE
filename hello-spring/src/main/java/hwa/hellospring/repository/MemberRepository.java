package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member join(Member member);
    Optional <Member> findById(String user_id);
    Optional<Member> findByInfo(String user_id);
    List<Member> findAll();
    Optional<Member> login(String user_id, String password);
    Boolean userDelete(String user_id,String user_pw);
}
