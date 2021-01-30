package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.UserService;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/login")
    public String login(@RequestBody UserDTO loginUser, Model model){
        model.addAttribute("loginUser",loginUser);
        return userService.login(loginUser);
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
