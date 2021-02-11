package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<AddressDTO> addressList = new ArrayList<AddressDTO>();

    //연관관계 메서드
    public void addAddress(Address address){
        addressList.add(new AddressDTO(address));
    }


    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.tel = user.getTel();
        this.regDate = user.getRegDate();
        this.point = user.getPoint();
        this.writerFlag = user.getWriterFlag();
        List<Address> addressList = user.getAddressList();
        for(Address address : addressList){
            this.addAddress(address);
        }
    }
}
