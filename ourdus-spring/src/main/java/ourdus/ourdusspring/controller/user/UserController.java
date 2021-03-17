package ourdus.ourdusspring.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.user.AddressDTO;
import ourdus.ourdusspring.dto.user.LoginRequest;
import ourdus.ourdusspring.dto.user.UserDTO;
import ourdus.ourdusspring.security.CustomUserDetailsService;
import ourdus.ourdusspring.security.JwtUtil;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@Slf4j
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/login")
    public ApiResult<Map<String,Object>> login(HttpServletResponse res, @RequestBody UserDTO userdto){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            User loginUser = userService.login(
                    new User(userdto)
            );
            //로그인 성공했다면 토큰을 생성
            String token = jwtService.create(loginUser);
            //토큰 정보는 request헤더로 보내고 나머지는 Map에 담아둠.
            res.setHeader("jwt-auth-token",token);

            resultMap.put("status",true);
            resultMap.put("user",new UserDTO(loginUser));
            resultMap.put("data",token);
            status = HttpStatus.ACCEPTED;
        }catch(RuntimeException e){
            log.error("로그인 실패",e);
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return OK(resultMap);
    }

    @GetMapping("answer")
    public ApiResult<?> hi() {
        return OK("hi~~");
    }

    @PostMapping("t/test")
    public ApiResult<?> hello(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or passoword", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return OK(jwt);
    }

    @GetMapping("/t/user/info")
    public ApiResult<UserDTO> getInfo(HttpServletRequest req){
        Long id = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        User user = userService.getUserInfo(id);
        return OK(new UserDTO(user));
    }

    @PostMapping("/user/join")
    public ApiResult<String> join(@RequestBody UserDTO userdto){
        return OK(userService.join(new User(userdto)));
    }


    @DeleteMapping("/user/delete")
    public ApiResult<String> delete(HttpServletRequest req){
        Long id = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        return OK(userService.delete(id));
    }

    @PostMapping("/t/user/edit")
    public ApiResult<String> update(HttpServletRequest req, @RequestBody UserDTO userdto){
        Long id = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        return OK(userService.update(id,new User(userdto)));
    }

    @PostMapping("/user/id-finding")
    public ApiResult<String> findUserId(@RequestBody UserDTO userdto){
        return OK(userService.findUserId(userdto.getTel()));
    }

    @PostMapping("/user/pw-finding")
    public ApiResult<String> findPW(@RequestBody UserDTO userdto){
        return OK(userService.findPW(userdto.getEmail(),userdto.getTel()));
    }

    @PostMapping("/t/user/address")
    public ApiResult<AddressDTO> addAddress(/*HttpServletRequest req,*/ @RequestBody AddressDTO addressDTO){
//        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        Long userId = 1L;
        Address address = Address.createBuilder()
                            .addressDTO(addressDTO)
                            .build();
        return OK(new AddressDTO(userService.AddAddress(userId, address)));
    }


    @DeleteMapping("/t/user/address/{address_id}")
    public ApiResult<String> deleteAddress(@PathVariable("address_id") Long address_Id){
        return OK(userService.deleteAddress(address_Id));
    }

    @PostMapping("/t/user/address/{address_id}")
    public ApiResult<AddressDTO> editAddress(@PathVariable("address_id") Long address_Id,@RequestBody AddressDTO addressDTO){
        Address address = Address.createBuilder()
                .addressDTO(addressDTO)
                .build();
        return OK(new AddressDTO(userService.editAddress(address_Id,address)));
    }

    @GetMapping("/t/user/address")
    public ApiResult<List<AddressDTO>> getAddress(HttpServletRequest req){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        List<AddressDTO> addressDTOList = new ArrayList<>();
        userService.getAddressList(userId).stream()
                .filter(address -> address != null)
                .forEach(address -> {
            addressDTOList.add(new AddressDTO(address));
        });
        return OK(addressDTOList);
    }

}

