package ourdus.ourdusspring.domain;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name="USER")
public class User {

    @Id @GeneratedValue
    @Column(name="USER_ID")
    private Long id;
    @Column(name="USER_EMAIL")
    private String email;
    @Column(name="USER_NAME")
    private String username;
    @Column(name="USER_PW")
    private String password;
    @Column(name="USER_TEL")
    private String tel;
//    @Column(name="REG_DATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime regDate;
    @Column(name="WRITER_FLAG")
    private Boolean writerFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long seq) {
        this.id = seq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

//    public Optional<LocalDateTime> getRegDate() { return Optional.ofNullable(regDate); }
//
//    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public Optional<Boolean> getWriterFlag() {
        return Optional.ofNullable(writerFlag);
    }

    public void setWriterFlag(Boolean writerFlag) {
        this.writerFlag = writerFlag;
    }
}
