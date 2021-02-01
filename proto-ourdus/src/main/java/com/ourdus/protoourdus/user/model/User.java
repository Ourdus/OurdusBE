package com.ourdus.protoourdus.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "user")
@Getter
@Setter //TODO setter 수정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", unique = true)
    private String userEmail;
    @Column(name = "user_pw")
    private String userPw;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_tel")
    private String userTel;
    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;
    @Column(name = "user_point")
    @ColumnDefault("0")
    private int userPoint;
    @Column(name = "writer_flag")
    @ColumnDefault("false")
    private boolean writerFlag;



    public User(UserVo userVo) {
        this.userId = userVo.getUserId();
        this.userEmail = userVo.getUserEmail();
        this.userPw = userVo.getUserPw();
        this.userName = userVo.getUserName();
        this.userTel = userVo.getUserTel();
        this.regDate = userVo.getRegDate().orElse(null);
        this.userPoint = userVo.getUserPoint().orElse(0);
        this.writerFlag = userVo.getWriterFlag().orElse(false);
    }

    public User() {
    }
}
