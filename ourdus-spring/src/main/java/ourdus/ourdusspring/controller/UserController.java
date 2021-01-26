package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/login")
    @ResponseBody
    public String login(@RequestBody UserDTO loginUser){
        return userService.login(loginUser);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/join")
    @ResponseBody
    public String join(@RequestBody UserDTO newUser){
        return userService.join(newUser);
    }


    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/{id}")
    @ResponseBody
    public String update(@RequestBody UserDTO user){return userService.update(user);}


}
