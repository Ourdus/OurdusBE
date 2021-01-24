package com.ourdus.protoourdus.user.controller;

public class LoginRequest {
    private String userEmail;
    private String userPw;

    public LoginRequest() { }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", userPw='" + userPw + '\'' +
                '}';
    }
}
