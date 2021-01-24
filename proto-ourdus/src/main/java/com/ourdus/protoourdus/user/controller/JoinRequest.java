package com.ourdus.protoourdus.user.controller;

public class JoinRequest {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userTel;

    public JoinRequest() { }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTel() {
        return userTel;
    }

    @Override
    public String toString() {
        return "JoinRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
