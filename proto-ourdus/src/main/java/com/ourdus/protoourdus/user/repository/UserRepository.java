package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.UserVo;

import java.util.Optional;

public interface UserRepository {
    UserVo insert(UserVo userVo);
    void update(UserVo userVo);

    Optional<UserVo> findByEmail(String userEmail);
    void deleteByEmail(String userEmail);
}
