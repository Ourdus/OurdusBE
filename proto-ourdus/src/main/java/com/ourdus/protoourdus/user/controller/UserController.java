package com.ourdus.protoourdus.user.controller;

import com.ourdus.protoourdus.common.ApiResult;
import com.ourdus.protoourdus.user.model.UserVo;
import com.ourdus.protoourdus.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ourdus.protoourdus.common.ApiResult.ERROR;
import static com.ourdus.protoourdus.common.ApiResult.OK;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public ApiResult<?> login(@RequestBody LoginRequest loginRequest){
        UserVo userVo = userService.login(
                loginRequest.getUserEmail(), loginRequest.getUserPw()
        );
        if(userVo.getUserId() != null) return OK(new UserDto(userVo));
        return ERROR("잘못된로그인", HttpStatus.NOT_FOUND);
    }

    @PostMapping("join")
    public ResponseEntity<UserDto> join(@RequestBody JoinRequest joinRequest){
        UserVo userVo = userService.join(
                joinRequest.getUserEmail(), joinRequest.getUserPw(), joinRequest.getUserName(), joinRequest.getUserTel());
        return new ResponseEntity<UserDto>(new UserDto(userVo), HttpStatus.OK);
    }

    @PostMapping("edit")
    public ResponseEntity<UserDto> edit(@RequestBody UserDto userDto){
        UserVo userVo = userService.modify(
                new UserVo.Builder(userDto).build());
        return new ResponseEntity<UserDto>(new UserDto(userVo), HttpStatus.OK);

    }



}
