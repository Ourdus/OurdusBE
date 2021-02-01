package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class UserJpaRepository{
    /* Jpa 구조에서 Vo를 사용하면 생기는 문제점
    1. ★★Entity 수정의 변경감지(Dirty Checking)가 어려움
    2. Vo 형태로 재생성해줘야하는 번거로움
    * */

    @PersistenceContext
    private final EntityManager em;

    public UserJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public User insert(User user) {
        if(user.getUserId() == null){
            em.persist(user);
        } else{
            em.merge(user);
        }
        return user;
    }

    public Optional<User> findById(Long userId) {
        User user = em.find(User.class, userId);
        return ofNullable(user);
    }

    public void deleteById(Long userId) {
        em.createQuery("DELETE FROM User u WHERE u.userId = :userId")
        .setParameter("userId", userId)
        .executeUpdate();
    }
}
