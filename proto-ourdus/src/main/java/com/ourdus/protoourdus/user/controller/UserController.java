package com.ourdus.protoourdus.user.controller;

import com.ourdus.protoourdus.common.ApiResult;
import com.ourdus.protoourdus.user.model.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    public ApiResult<UserVo> login(@RequestBody LoginRequest loginRequest){
        //카카오 및 외부 api를 통한 로그인이 아닌 아이디어스 이메일로 로그인만 구현
        //TODO UserService의 login 로직 필요
        return new ApiResult<>();
    }

    @PostMapping("join")
    public ApiResult<UserVo> join(@RequestBody JoinRequest joinRequest){
        //카카오 및 외부 api를 통한 가입이 아닌 아이디어스 이메일로 가입만 구현
        //TODO UserService의 join 로직 필요
        return new ApiResult<>();
    }
}
