package ourdus.ourdusspring.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.UserDTO;
import ourdus.ourdusspring.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    public void beforeEach(){
        userService =new UserService(userRepository);
    }

    @Test
    public void "회원가입"(){
        //given
        UserDTO user1 = new UserDTO();
        user1.setEmail("abc@naver.com");

        UserDTO user2 = new UserDTO();
        user2.setEmail("abc@naver.com");
        //when
        userService.join(user1);
        //then
        String result =userService.join(user2);
        assertThat(result).isEqualTo("join failed");
    }



}
