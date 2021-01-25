package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ourdus.ourdusspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{

    User save(User user);
    Optional<User> findByEmail(String email);

}
