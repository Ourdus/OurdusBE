package com.ourdus.protoourdus.user.model;

import java.time.LocalDateTime;

public class UserVo {
    private final Long userId;
    private final String userEmail;
    private String userPw;
    private final String userName;
    private String userTel;
    private final LocalDateTime regDate;
    private Long userPoint;
    private Boolean writerFlag; //null 값을 넣기 위해선 boolean은 불가능, Boolean 사용

    public UserVo(String userEmail, String userPw, String userName, String userTel) {
        this(null, userEmail, userPw, userName, userTel, null, null, null);
    }

    public UserVo(Long userId, String userEmail, String userPw, String userName, String userTel, LocalDateTime regDate, Long userPoint, Boolean writerFlag) {
        //TODO check logic 필요

        this.userId = userId;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.userTel = userTel;
        this.regDate = regDate;
        this.userPoint = userPoint;
        this.writerFlag = writerFlag;
    }


    @Override
    public boolean equals(Object o) {   //TODO Vo의 equals override 필요
    }

    @Override
    public int hashCode() {//TODO Vo의 hashcode override 필요

    }

    //Builder 생성 필요
}