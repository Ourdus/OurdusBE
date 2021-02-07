package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Address;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressMain() {
        return addressMain;
    }

    public void setAddressMain(String addressMain) {
        this.addressMain = addressMain;
    }

    public String getAddressSub() {
        return addressSub;
    }

    public void setAddressSub(String addressSub) {
        this.addressSub = addressSub;
    }
}
