package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.UserVo;

import java.util.Optional;

public interface UserRepository {
    UserVo insert(UserVo userVo);

    Optional<UserVo> findByEmail(String userEmail);
    //TODO repository login, join 구현 - jdbc, JPA 둘다
}
