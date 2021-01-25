package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ourdus.ourdusspring.domain.User;

import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<User,Long>, UserRepository {

    @Override
    Optional<User> findByEmail(String email);
}
