package com.ourdus.protoourdus.user.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserVo {
    private final Long userId;
    private final String userEmail;
    private String userPw;
    private String userName;
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

    public void change(){
        //TODO 회원정보 수정으로 변경될 요소 수정
    }

    public Long getUserId() {
        return userId;
    }

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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public Long getUserPoint() {
        return userPoint;
    }

    public Boolean getWriterFlag() {
        return writerFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVo uservo = (UserVo) o;
        return Objects.equals(userId, uservo.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", regDate=" + regDate +
                ", userPoint=" + userPoint +
                ", writerFlag=" + writerFlag +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String userEmail;
        private String userPw;
        private String userName;
        private String userTel;
        private LocalDateTime regDate;
        private Long userPoint;
        private Boolean writerFlag;

        public Builder() {
        }

        public Builder(UserVo userVo){
            this.userId = userVo.getUserId();
            this.userEmail = userVo.getUserEmail();
            this.userPw = userVo.getUserPw();
            this.userName = userVo.getUserName();
            this.userTel = userVo.getUserTel();
            this.regDate = userVo.getRegDate();
            this.userPoint = userVo.getUserPoint();
            this.writerFlag = userVo.getWriterFlag();
        }

        public static Builder anUserVo() {
            return new Builder();
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder userPw(String userPw) {
            this.userPw = userPw;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userTel(String userTel) {
            this.userTel = userTel;
            return this;
        }

        public Builder regDate(LocalDateTime regDate) {
            this.regDate = regDate;
            return this;
        }

        public Builder userPoint(Long userPoint) {
            this.userPoint = userPoint;
            return this;
        }

        public Builder writerFlag(Boolean writerFlag) {
            this.writerFlag = writerFlag;
            return this;
        }

        public UserVo build() {
            return new UserVo(userId, userEmail, userPw, userName, userTel, regDate, userPoint, writerFlag);
        }
    }
}