package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Address;

@Getter
@Setter
@NoArgsConstructor
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
