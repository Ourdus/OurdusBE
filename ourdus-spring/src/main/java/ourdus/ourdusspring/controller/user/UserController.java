package ourdus.ourdusspring.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.user.AddressDTO;
import ourdus.ourdusspring.dto.user.LoginRequest;
import ourdus.ourdusspring.dto.user.UserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getUserEmail;

@RestController
@RequestMapping("api")
@Slf4j
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager manager;

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/login")
    public ApiResult<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return OK(authentication.getDetails());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or passoword", e);
        }
    }

    @GetMapping("t/answer")
    public ApiResult<?> hi() {
        return OK("hi~~");
    }

    @GetMapping("/t/user/info")
    public ApiResult<UserDTO> getInfo() {
        User user = userService.getUserInfo(getUserEmail());
        return OK(new UserDTO(user));
    }

    @PostMapping("/user/join")
    public ApiResult<String> join(@Valid @RequestBody UserDTO userdto) {
        return OK(userService.join(new User(userdto)));
    }

    @DeleteMapping("/t/user/delete")
    public ApiResult<String> delete() {
        return OK(userService.delete(getUserEmail()));
    }

    @PostMapping("/t/user/edit")
    public ApiResult<String> update(@Valid @RequestBody UserDTO userdto) {
        return OK(userService.update(getUserEmail(), new User(userdto)));
    }

    @PostMapping("/user/id-finding")
    public ApiResult<String> findUserId(@Valid @RequestBody UserDTO userdto) {
        return OK(userService.findUserId(userdto.getTel()));
    }

    @PostMapping("/user/pw-finding")
    public ApiResult<String> findPW(@Valid @RequestBody UserDTO userdto) {
        return OK(userService.findPW(userdto.getEmail(), userdto.getTel()));
    }

    @PostMapping("/t/user/address")
    public ApiResult<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = Address.createBuilder()
                .addressDTO(addressDTO)
                .build();
        return OK(new AddressDTO(userService.AddAddress(getUserEmail(), address)));
    }

    @DeleteMapping("/t/user/address/{address_id}")
    public ApiResult<String> deleteAddress(@PathVariable("address_id") Long address_Id) {
        return OK(userService.deleteAddress(getUserEmail(), address_Id));
    }

    @PostMapping("/t/user/address/{address_id}")
    public ApiResult<AddressDTO> editAddress(@PathVariable("address_id") Long address_Id, @Valid @RequestBody AddressDTO addressDTO) {
        Address address = Address.createBuilder()
                .addressDTO(addressDTO)
                .build();
        return OK(new AddressDTO(userService.editAddress(getUserEmail(), address_Id, address)));
    }

    @GetMapping("/t/user/address")
    public ApiResult<List<AddressDTO>> getAddress() {
        List<AddressDTO> addressDTOList = userService.getAddressList(getUserEmail()).stream()
                .filter(address -> address != null)
                .map(AddressDTO::new)
                .collect(Collectors.toList());
        return OK(addressDTOList);
    }

}

