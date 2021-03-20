package ourdus.ourdusspring.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ourdus.ourdusspring.domain.offlineclass.order.COrder;
import ourdus.ourdusspring.dto.user.JoinRequest;
import ourdus.ourdusspring.dto.user.UserDTO;
import ourdus.ourdusspring.security.JwtUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.util.CompareValueUtils.changeValue;

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
    private Boolean writerFlag = false;

    @Transient
    private GrantedAuthority role = new SimpleGrantedAuthority("USER");

    @OneToMany(mappedBy = "user")
    private List<Address> addressList = new ArrayList<Address>();

    @OneToMany(mappedBy = "user")
    private List<COrder> cOrderList=new ArrayList<>();

    public void addAddress(Address address){
        addressList.add(address);
        address.setUser(this);
    }

    public void login(PasswordEncoder passwordEncoder, String password) {
        if(!passwordEncoder.matches(password, this.password)) {
            throw new IllegalStateException("잘못된 비밀번호입니다.");
        }
    }

    public void join(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public String makeToken() {
        return JwtUtil.createToken(email, role.toString());
    }

    public void changeInfo(User user) {
        this.tel = changeValue(this.tel, user.tel);
        this.name = changeValue(this.name, user.name);
    }

    public boolean isUser(String email) {
        return this.email.equals(email);
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
        this.tel = userdto.getTel();
        this.regDate = userdto.getRegDate();
        this.point = userdto.getPoint();
        this.writerFlag = userdto.getWriterFlag();
    }

    public User(JoinRequest joinRequest) {
        this.email = joinRequest.getEmail();
        this.password = joinRequest.getPassword();
        this.name = joinRequest.getName();
        this.tel = joinRequest.getTel();
    }

}
