package ourdus.ourdusspring.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String tel;
    private LocalDateTime regDate;
    private int point;
    private Boolean writerFlag = false;
    private List<AddressDTO> addressList = new ArrayList<>(3);

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.tel = user.getTel();
        this.regDate = user.getRegDate();
        this.point = user.getPoint();
        this.writerFlag = user.getWriterFlag();
        this.addressList = user.getAddressList().stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }
}
