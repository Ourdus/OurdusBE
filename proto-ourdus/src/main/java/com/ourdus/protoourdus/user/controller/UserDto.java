package com.ourdus.protoourdus.user.controller;

import com.ourdus.protoourdus.user.model.UserVo;

import java.time.LocalDateTime;

public class UserDto {
    private Long userId;
    private String userEmail;
    private String userPw;  //TODO UserDto에서 password 삭제하기 (테스트용)
    private String userName;
    private String userTel;
    private LocalDateTime regDate;
    private Long userPoint;
    private Boolean writerFlag;


    public UserDto(UserVo userVo) {
        this.userId = userVo.getUserId();
        this.userEmail = userVo.getUserEmail();
        this.userPw = userVo.getUserPw();
        this.userName = userVo.getUserName();
        this.userTel = userVo.getUserTel();
        this.regDate = userVo.getRegDate().orElse(null);
        this.userPoint = userVo.getUserPoint().orElse(0L);
        this.writerFlag = userVo.getWriterFlag().orElse(false);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Long getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Long userPoint) {
        this.userPoint = userPoint;
    }

    public Boolean getWriterFlag() {
        return writerFlag;
    }

    public void setWriterFlag(Boolean writerFlag) {
        this.writerFlag = writerFlag;
    }
}
