package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ourdus.ourdusspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{


    User save(User user);
    Optional<User> findByEmail(String email);
    String findPassword(String email);
//    public User findByEmailAndPassword(String email, String password);

}
