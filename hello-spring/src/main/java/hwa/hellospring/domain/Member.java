package hwa.hellospring.domain;



import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="User")
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Member implements Serializable {

    @Id @GeneratedValue
    private String user_id;
    private String user_name;
    @Column(name="user_pw")
    private String password;
    private String user_email;
    private String user_tel;
    private int user_point;
    private String user_status;
   // private int writer_flag;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public int getUser_point() {
        return user_point;
    }

    public void setUser_point(int user_point) {
        this.user_point = user_point;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

 /*   public int getWriter_flag() {
        return writer_flag;
    }

    public void setWriter_flag(int writer_flag) {
        this.writer_flag = writer_flag;
    }*/

}


