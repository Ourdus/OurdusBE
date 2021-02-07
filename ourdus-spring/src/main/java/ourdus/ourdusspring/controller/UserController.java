package ourdus.ourdusspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.AddressDTO;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private JwtService jwtService;


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
            resultMap.put("data",loginUser);
            status = HttpStatus.ACCEPTED;
        }catch(RuntimeException e){
            log.error("로그인 실패",e);
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return OK(resultMap);
    }

    @GetMapping("/user/info")
    public ApiResult<User> getInfo(HttpServletRequest req){
        Long id = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        return OK(userService.getUserInfo(id));
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

    @PostMapping("/user/edit")
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


//    @GetMapping("/user/{id}")
//    public ApiResult<String> info(@PathVariable("id") Long id,Model model) {
//        Optional<User> user = userService.info(id);
//        if(user.isPresent()){
//            model.addAttribute("userInfo" ,user);
//            return OK("회원 정보 조회 성공");
//        }else{
//            return OK("회원 정보 조회 실패");
//        }
//      }

    @PostMapping("/user/address")
    public ApiResult<AddressDTO> addAddress(HttpServletRequest req, @RequestBody AddressDTO addressDTO){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        Address address = Address.createBuilder()
                            .name(addressDTO.getName())
                            .phone(addressDTO.getPhone())
                            .zipcode(addressDTO.getZipcode())
                            .addressMain(addressDTO.getAddressMain())
                            .addressSub(addressDTO.getAddressSub())
                            .build();
        return OK(new AddressDTO(userService.AddAddress(userId, address)));
    }

    @DeleteMapping("/user/address/{address_id}")
    public ApiResult<String> deleteAddress(@PathVariable("address_id") Long address_Id){
        return OK(userService.deleteAddress(address_Id));
    }

    @PostMapping("/user/address/{address_id}")
    public ApiResult<String> editAddress(@PathVariable("address_id") Long address_Id,@RequestBody AddressDTO addressDTO){
        Address address = Address.createBuilder()
                .name(addressDTO.getName())
                .phone(addressDTO.getPhone())
                .zipcode(addressDTO.getZipcode())
                .addressMain(addressDTO.getAddressMain())
                .addressSub(addressDTO.getAddressSub())
                .build();
        return OK(userService.editAddress(address_Id,address));
    }

    @GetMapping("/user/address")
    public ApiResult<List<AddressDTO>> getAddress(HttpServletRequest req){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        List<AddressDTO> addressDTOList = new ArrayList<>();
        userService.getAddressList(userId).stream().forEach(address -> {
            addressDTOList.add(new AddressDTO(address));
        });
        return OK(addressDTOList);
    }

}

