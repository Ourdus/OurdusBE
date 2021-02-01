package ourdus.ourdusspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDTO user, HttpServletResponse res){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            User loginUser = userService.login(user);
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
        return new ResponseEntity<Map<String,Object>>(resultMap, status);
    }
//    public String login(@RequestBody UserDTO loginUser){
//        return userService.login(loginUser);
//    }

    @PostMapping("/info")
    public ResponseEntity<Map<String,Object>>getInfo(HttpServletRequest req, @RequestBody UserDTO user){
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
        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

    @PostMapping("/user/join")
    public String join(@RequestBody UserDTO newUser){
        return userService.join(newUser);
    }


    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @PostMapping("/user/{id}")
    public String update(@RequestBody UserDTO user){return userService.update(user);}


    @GetMapping("/user/{id}")
    public String info(@PathVariable("id") Long id,Model model) {
        Optional<User> user = userService.info(id);
        if(user.isPresent()){
            model.addAttribute("userInfo" ,user);
            return "회원 정보 조회 성공";
        }else{
            return "회원 정보 조회 실패";
        }
    }
}
