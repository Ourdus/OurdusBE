package ourdus.ourdusspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private final UserService userService;

    @Autowired
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

    @PostMapping("/info")
    public ApiResult<Map<String,Object>> getInfo(HttpServletRequest req, @RequestBody UserDTO user){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            //사용자에게 전달할 정보
            String info = userService.getServerInfo();
            //보너스로 토큰에 담긴 정보도 전달. 서버에서 토큰을 사용하는 방법임을 알수있음
            resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
            resultMap.put("status",true);
            resultMap.put("info",info);
            resultMap.put("request_body",user);
            status=HttpStatus.ACCEPTED;
        }catch(RuntimeException e){
            log.error("정보 조회 실패",e);
            resultMap.put("message",e.getMessage());
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return OK(resultMap);
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
        //Long id = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        return OK(userService.update(new User(userdto)));
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
    public ApiResult<String> addAddress(HttpServletRequest req, @RequestBody Address newAddress){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        String address = newAddress.getAddress();
        return OK(userService.AddAddress(userId, address));
    }

    @DeleteMapping("/user/address/{address_id}")
    public ApiResult<String> addAddress(@PathVariable("address_id") Long address_Id){
        return OK(userService.deleteAddress(address_Id));
    }

    @PostMapping("/user/address/{address_id}")
    public ApiResult<String> editAddress(@PathVariable("address_id") Long address_Id,@RequestBody Address newAddress){
        String address = newAddress.getAddress();
        return OK(userService.editAddress(address_Id,address));
    }

    @GetMapping("/user/address")
    public ApiResult<List<String>> getAddress(HttpServletRequest req){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        return OK(userService.getAddressList(userId));
    }

}

