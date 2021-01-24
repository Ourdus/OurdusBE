package ourdus.ourdusspring.controller;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.Optional;

@Controller
public class UserController {

   @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/login")
    public String login(String email, String password){
        Optional<User> user1 = userRepository.findByEmail(email);
        if(user1.isPresent()){
            String result = userRepository.findPassword(email);
            if(result.equals(password)){
                return "login success";
            }
        }
        return "login fail";
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user/join")
    @ResponseBody
    public String join(@RequestBody UserDTO newUser){

        String email = newUser.getEmail();
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        String tel = newUser.getTel();
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setTel(tel);

        if(userRepository.findByEmail(email).isPresent()){
            return "failed";
        }
        userRepository.save(user);
        return "success";
    }




}
