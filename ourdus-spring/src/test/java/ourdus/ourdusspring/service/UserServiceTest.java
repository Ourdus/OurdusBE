package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.UserDTO;
//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        userService = new UserService(userRepository);
    }

    @Test
    void join() {
        //given
       UserDTO user1 =new UserDTO();
       user1.setEmail("abc@naver.com");
       UserDTO user2 = new UserDTO();
       user2.setEmail("abc@navercom");
        //when
        userService.join(user1);
        //then
        String result = userService.join(user2);
        assertThat(result).isEqualTo("join failed");
    }

    @Test
    void login() {
        //given
        UserDTO user1 = new UserDTO();
        user1.setPassword("0000");
        user1.setEmail("000@naver.com");
        userService.join(user1);

        UserDTO user2 = new UserDTO();
        user2.setPassword("1111");
        user2.setEmail("000@naver.com");
        //when
        userService.login(user2);
        //then
        String result = userService.login(user2);
        assertThat(result).isEqualTo("login failed");
    }


}