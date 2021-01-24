package hwa.hellospring.repository;

import hwa.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional <Member> findById(String user_id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
