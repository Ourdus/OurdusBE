package ourdus.ourdusspring.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> findByTel(String tel);
    Optional<User> findByEmailAndTel (String email, String tel);
}
