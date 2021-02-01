package ourdus.ourdusspring.domain;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class User {

    @Id @GeneratedValue
    @Column(name="USER_ID",nullable = false,unique = true)
    private Long id;
    @Column(name="USER_EMAIL",nullable = false,unique = true)
    private String email;
    @Column(name="USER_NAME")
    private String username;
    @Column(name="USER_PW",nullable = false)
    private String password;
    @Column(name="USER_TEL")
    private String tel;
    @Column(name="WRITER_FLAG")
    private Boolean writerFlag;


}
