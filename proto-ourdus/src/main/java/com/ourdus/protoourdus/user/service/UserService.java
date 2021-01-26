package com.ourdus.protoourdus.user.service;

import com.ourdus.protoourdus.user.model.UserVo;
import com.ourdus.protoourdus.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserVo join(String userEmail, String userPw, String userName, String userTel) {
        //TODO join 체크 로직(validation)
        UserVo userVo = new UserVo(userEmail, userPw, userName, userTel);
        return insert(userVo);
    }

    @Transactional
    public UserVo login(String userEmail, String userPw){
        //TODO login 체크 로직(validation)
        UserVo userVo = findByEmail(userEmail)
                .orElseThrow(() -> new NoSuchElementException());
        if(userVo.getUserPw().equals(userPw)) return userVo;
        return new UserVo.Builder().build();
    }

    public Optional<UserVo> findByEmail(String userEmail){
        //validation필요
        return userRepository.findByEmail(userEmail);
    }

    public UserVo insert(UserVo userVo){
        return userRepository.insert(userVo);
    }

    //TODO update구문 필요

}