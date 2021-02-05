package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.dto.UserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;
    @Column(name="USER_EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name="USER_NAME", nullable = false)
    private String name;
    @Column(name="USER_PW", nullable = false)
    private String password;
    @Column(name="USER_TEL")
    private String tel;
    @Column(name = "reg_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;
    @Column(name = "user_point")
    @ColumnDefault("0")
    private int point;
    @Column(name="WRITER_FLAG")
    @ColumnDefault("false")
    private Boolean writerFlag;

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Builder
    public User(String email, String name, String password, String tel){
        this.email = email;
        this.name = name;
        this.password = password;
        this.tel = tel;
    }

    @Builder
    public User(Long id, String email, String name, String password, String tel, LocalDateTime regDate, int point, Boolean writerFlag) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.regDate = regDate;
        this.point = point;
        this.writerFlag = writerFlag;
    }

    public User(UserDTO userdto){
        this.id = userdto.getId();
        this.email = userdto.getEmail();
        this.name = userdto.getName();
        this.password = userdto.getPassword();
        this.tel = userdto.getTel();
        this.regDate = userdto.getRegDate();
        this.point = userdto.getPoint();
        this.writerFlag = userdto.getWriterFlag();
    }

}
