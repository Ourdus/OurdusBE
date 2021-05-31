package ourdus.ourdusspring.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.user.*;
import ourdus.ourdusspring.service.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "유저 정보 관리")
public class UserController {

    private final AuthenticationManager manager;
    private final UserService userService;

    public UserController(UserService userService, AuthenticationManager manager) {
        this.userService = userService;
        this.manager = manager;
    }

    @ApiOperation(value = "로그인", notes = "로그인 성공시 JWT 토큰을 반환한다.")
    @PostMapping("/user/login")
    public ApiResult<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return OK(authentication.getDetails());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or passoword", e);
        }
    }

    @ApiOperation(value = "사용자 정보", notes = "토큰으로 사용자의 정보들을 반환한다.")
    @GetMapping("/t/user/info")
    public ApiResult<UserDTO> getInfo() {
        User user = userService.findUser(getAuthUserId());
        return OK(new UserDTO(user));
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 성공시 토큰을 반환한다.")
    @PostMapping("/user/join")
    public ApiResult<String> join(@Valid @RequestBody JoinRequest joinRequest) {
        return OK(userService.join(new User(joinRequest)));
    }

    @ApiOperation(value = "회원탈퇴", notes = "토큰으로 해당하는 유저 정보 회원을 탈퇴한다")
    @DeleteMapping("/t/user/delete")
    public ApiResult<String> delete() {
        return OK(userService.delete(getAuthUserId()));
    }

    @ApiOperation(value = "회원수정", notes = "토큰으로 해당하는 회원 정보를 수정한다")
    @PostMapping("/t/user/edit")
    public ApiResult<String> update(@Valid @RequestBody UserDTO userdto) {
        return OK(userService.update(getAuthUserId(), new User(userdto)));
    }

    @ApiOperation(value = "아이디 찾기", notes = "연락처에 맞는 아이디 정보를 찾는다.")
    @PostMapping("/user/id-finding")
    public ApiResult<String> findUserId(@Valid @RequestBody String userTel) {
        return OK(userService.findUserId(userTel));
    }

    @ApiOperation(value = "비밀번호 찾기", notes = "연락처와 이메일에 맞는 회원 정보의 비밀번호를 찾는다.")
    @PostMapping("/user/pw-finding")
    public ApiResult<String> findPW(@Valid @RequestBody FindUserRequest request) {
        return OK(userService.findPW(request.getEmail(), request.getTel()));
    }

    @ApiOperation(value = "주소 추가", notes = "토큰에 맞는 회원의 주소 정보를 추가한다.")
    @PostMapping("/t/user/address")
    public ApiResult<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        return OK(new AddressDTO(
                userService.AddAddress(getAuthUserId(), new Address(addressDTO))));
    }

    @ApiOperation(value = "주소 제거", notes = "토큰에 맞는 회원의 주소 정보를 제거한다.")
    @DeleteMapping("/t/user/address/{address_id}")
    public ApiResult<String> deleteAddress(@PathVariable("address_id") Long address_Id) {
        return OK(userService.deleteAddress(getAuthUserId(), address_Id));
    }

    @ApiOperation(value = "주소 수정", notes = "토큰에 맞는 회원의 주소 정보를 수정한다.")
    @PostMapping("/t/user/address/{address_id}")
    public ApiResult<AddressDTO> editAddress(@PathVariable("address_id") Long address_Id, @Valid @RequestBody AddressDTO addressDTO) {
        return OK(new AddressDTO(
                userService.editAddress(getAuthUserId(), address_Id, new Address(addressDTO))));
    }

    @ApiOperation(value = "주소 목록 찾기", notes = "토큰에 맞는 회원의 주소 정보 리스트를 찾는다.")
    @GetMapping("/t/user/address")
    public ApiResult<List<AddressDTO>> getAddress() {
        List<AddressDTO> addressDTOList = userService.findAddressList(getAuthUserId()).stream()
                .filter(address -> address != null)
                .map(AddressDTO::new)
                .collect(Collectors.toList());
        return OK(addressDTOList);
    }

}

