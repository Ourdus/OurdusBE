package com.ourdus.protoourdus.user.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_email", unique = true)
    private String userEmail;
    @Column(name = "user_pw")
    private String userPw;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_tel")
    private String userTel;
    @Column(name = "reg_date", )
    private LocalDateTime regDate;
    @Column(name = "user_point")
    private Long userPoint;
    @Column(name = "writer_flag")
    private boolean writerFlag;

    public User(UserVo userVo){

    }
}
