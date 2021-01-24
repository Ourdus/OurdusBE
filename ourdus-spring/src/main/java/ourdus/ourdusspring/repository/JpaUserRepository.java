package ourdus.ourdusspring.repository;

import ourdus.ourdusspring.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email){
        User user = em.find(User.class,email);
        return Optional.ofNullable(user);
    }


//    @Override
//    public String findPassword(String email) {
//        Query query = em.createQuery("select m.password from User m where m.email = :email", User.class)
//                .setParameter("email", email);
//        String result = query.getSingleResult().toString();
//        return result;
//    }
}
