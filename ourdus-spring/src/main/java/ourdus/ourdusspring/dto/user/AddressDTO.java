package ourdus.ourdusspring.dto.user;

import lombok.*;
import ourdus.ourdusspring.domain.user.Address;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String name;
    private String phone;
    private String zipcode;
    private String addressMain;
    private String addressSub;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.name = address.getName();
        this.phone = address.getPhone();
        this.zipcode = address.getZipcode();
        this.addressMain = address.getAddressMain();
        this.addressSub = address.getAddressSub();
    }

}
