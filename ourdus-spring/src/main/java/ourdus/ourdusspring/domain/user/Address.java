package ourdus.ourdusspring.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.dto.user.AddressDTO;

import javax.persistence.*;

import static ourdus.ourdusspring.util.CompareValueUtils.changeValue;

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

    //연관관계 매핑
    public void setUser(User user){
        if(this.user!=null){
            this.user.getAddressList().remove(this);
        }
        this.user=user;
        user.getAddressList().add(this);
    }

    public void changeAddress(Address address) {
        this.name = changeValue(this.name, address.name);
        this.phone = changeValue(this.phone, address.phone);
        this.zipcode = changeValue(this.zipcode, address.zipcode);
        this.addressMain = changeValue(this.addressMain, address.addressMain);
        this.addressSub = changeValue(this.addressSub, address.addressSub);
    }

    public void validOwner(Long id) {
        if (!user.isUser(id)) {
            throw new IllegalStateException("해당 회원의 주소정보가 아닙니다. 다시 확인해주세요.");
        }
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Address(AddressDTO addressDTO) {
        this.name = addressDTO.getName();
        this.phone = addressDTO.getPhone();
        this.zipcode = addressDTO.getZipcode();
        this.addressMain = addressDTO.getAddressMain();
        this.addressSub = addressDTO.getAddressSub();
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", addressMain='" + addressMain + '\'' +
                ", addressSub='" + addressSub + '\'' +
                ", user=" + user +
                '}';
    }
}