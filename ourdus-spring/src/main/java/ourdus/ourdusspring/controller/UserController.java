package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/login")
    public String login(@RequestBody UserDTO loginUser){
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


}
