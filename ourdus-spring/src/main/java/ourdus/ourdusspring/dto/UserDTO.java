package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String tel;
//    private LocalDateTime regDate;
    private Boolean writerFlag;

    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.tel = user.getTel();
//        this.regDate = user.getRegDate().orElse(null);
        this.writerFlag = user.getWriterFlag().orElse(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocalDateTime getRegDate() {
//        return regDate;
//    }

    public Boolean getWriterFlag() {
        return writerFlag;
    }

    public void setWriterFlag(Boolean writerFlag) {
        this.writerFlag = writerFlag;
    }
}
