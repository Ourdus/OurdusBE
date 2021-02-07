package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name="ADDRESS_NAME")
    private String name;
    @Column(name="ADDRESS_PHONE")
    private String phone;
    @Column(name="ADDRESS_ZIPCODE")
    private String zipcode;
    @Column(name="ADDRESS_MAIN")
    private String addressMain;
    @Column(name="ADDRESS_SUB")
    private String addressSub;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setAddressMain(String addressMain) {
        this.addressMain = addressMain;
    }

    public void setAddressSub(String addressSub) {
        this.addressSub = addressSub;
    }

    //연관관계 매핑
    public void setUser(User user){
        if(this.user!=null){
            this.user.getAddressList().remove(this);
        }
        this.user=user;
        user.getAddressList().add(this);
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Address(String name, String phone, String zipcode, String addressMain, String addressSub) {
        this.name = name;
        this.phone = phone;
        this.zipcode = zipcode;
        this.addressMain = addressMain;
        this.addressSub = addressSub;
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public Address(String name, String phone, String zipcode, String addressMain, String addressSub, User user) {
        this.name = name;
        this.phone = phone;
        this.zipcode = zipcode;
        this.addressMain = addressMain;
        this.addressSub = addressSub;
        this.user = user;
    }


}