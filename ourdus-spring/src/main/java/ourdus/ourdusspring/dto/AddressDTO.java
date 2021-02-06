package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Address;

public class AddressDTO {
    private Long id;
    private String address;
    private Long userId;

    public AddressDTO(Address address){
        this.id= address.getId();
        this.address=address.getAddress();
        this.userId=address.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
