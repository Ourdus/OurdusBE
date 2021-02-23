package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaymentUserDTO {
    private Long userId;
    private String userName;
    private String userPhone;
    private List<AddressDTO> addressDTOList;

    public PaymentUserDTO(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.userPhone = user.getTel();
        List<Address> addressList = user.getAddressList();
        addressDTOList = new ArrayList<>();
        addressList.stream().forEach(address -> {
            addressDTOList.add(new AddressDTO(address));
        });
    }
}
