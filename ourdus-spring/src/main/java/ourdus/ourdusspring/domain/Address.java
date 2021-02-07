package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.repository.UserRepository;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    private Long id;

    @Column(name="USER_ADDRESS")
    private String address;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;


    //연관관계 매핑
    public void setUser(User user){
        if(this.user!=null){
            this.user.getAddressList().remove(this);
        }
        this.user=user;
        user.getAddressList().add(this);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //생성 메소드
    public static Address createAddress(String address){
        Address addr = new Address();
        addr.setAddress(address);
        return addr;
    }

    @Builder
    public Address(Long id, String address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
    }




}