//package com.ourdus.protoourdus.user.model;
//
//import org.hibernate.annotations.ColumnDefault;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name= "user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @Column(name = "user_email", unique = true)
//    private String userEmail;
//    @Column(name = "user_pw")
//    private String userPw;
//    @Column(name = "user_name")
//    private String userName;
//    @Column(name = "user_tel")
//    private String userTel;
//    @Column(name = "reg_date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime regDate;
//    @Column(name = "user_point")
//    @ColumnDefault("0")
//    private Long userPoint;
//    @Column(name = "writer_flag")
//    @ColumnDefault("false")
//    private boolean writerFlag;
//
//    public User(UserVo userVo) {
//        this.userId = userVo.getUserId();
//        this.userEmail = userVo.getUserEmail();
//        this.userPw = userVo.getUserPw();
//        this.userName = userVo.getUserName();
//        this.userTel = userVo.getUserTel();
//        this.regDate = userVo.getRegDate();
//        this.userPoint = userVo.getUserPoint();
//        this.writerFlag = userVo.getWriterFlag();
//    }
//
//}
