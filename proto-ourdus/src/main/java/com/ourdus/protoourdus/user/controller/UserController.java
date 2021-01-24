package com.ourdus.protoourdus.user.controller;

import com.ourdus.protoourdus.user.model.UserVo;
import com.ourdus.protoourdus.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest){
        //카카오 및 외부 api를 통한 로그인이 아닌 아이디어스 이메일로 로그인만 구현
        //TODO UserService의 login 로직 필요
        UserVo userVo = userService.login(
                loginRequest.getUserEmail(), loginRequest.getUserPw()
        );
        //TODO result type 형태를 ApiResult로 구현
        if(userVo.getUserId() != null) return userVo.getUserEmail();
        return "잘못된 값";
    }

    @PostMapping("join")
    public UserDto join(@RequestBody JoinRequest joinRequest){
        //카카오 및 외부 api를 통한 가입이 아닌 아이디어스 이메일로 가입만 구현
        UserVo userVo = userService.join(
                joinRequest.getUserEmail(), joinRequest.getUserPw(), joinRequest.getUserName(), joinRequest.getUserTel());
        //TODO UserService의 join 로직 필요
        //TODO result type 형태를 ApiResult로 구현
        return new UserDto(userVo);
    }
}
