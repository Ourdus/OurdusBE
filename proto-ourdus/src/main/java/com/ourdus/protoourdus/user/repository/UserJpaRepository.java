package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.User;
import com.ourdus.protoourdus.user.model.UserVo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class UserJpaRepository implements UserRepository{
    /* Jpa 구조에서 Vo를 사용하면 생기는 문제점
    1. ★★Entity 수정의 변경감지(Dirty Checking)가 어려움
    2. Vo 형태로 재생성해줘야하는 번거로움
    * */

    @PersistenceContext
    private final EntityManager em;

    public UserJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserVo insert(UserVo userVo) {
        User user = new User(userVo);
        em.persist(user);
        return new UserVo.Builder(user).build();
    }

    @Override
    public void update(UserVo userVo) {
        insert(userVo);
    }

    @Override
    public Optional<UserVo> findByEmail(String userEmail) {
        User user = em.createQuery("SELECT u FROM User u WHERE u.userEmail = :email", User.class)
                .setParameter("email", userEmail)
                .getSingleResult();
        UserVo userVo = new UserVo.Builder(user).build();
        return ofNullable(userVo);
    }

    @Override
    public void deleteByEmail(String userEmail) {
        em.createQuery("DELETE FROM User u WHERE u.userEmail = :email")
        .setParameter("email", userEmail)
        .executeUpdate();
    }
}
