package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String tel;
    private LocalDateTime regDate;
    private int point;
    private Boolean writerFlag;


    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.tel = user.getTel();
        this.regDate = user.getRegDate();
        this.point = user.getPoint();
        this.writerFlag = user.getWriterFlag();
    }
}
