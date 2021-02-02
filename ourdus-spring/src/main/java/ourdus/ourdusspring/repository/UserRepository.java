package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
