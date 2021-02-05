package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ourdus.ourdusspring.repository.UserRepository;

//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;

@DataJpaTest
//@EnableConfigurationProperties(value= SpringConfig.class)
class UserServiceTest {

//    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userService= new UserService(userRepository);
    }

    @Test
    void join() {
        //given
//       UserDTO user1 =new UserDTO();
//       user1.setEmail("abc@naver.com");
//       UserDTO user2 = new UserDTO();
//       user2.setEmail("abc@naver.com");
//        //when
//        userService.join(user1);
//        //then
//        String result = userService.join(user2);
//        assertThat(result).isEqualTo("join failed");
    }

//    @Test
//    void login() {
//        //given
//        UserDTO user1 = new UserDTO();
//        user1.setPassword("0000");
//        user1.setEmail("000@naver.com");
//        userService.join(user1);
//
//        UserDTO user2 = new UserDTO();
//        user2.setPassword("1111");
//        user2.setEmail("000@naver.com");
//        //when
//        userService.login(user2);
//        //then
//        String result = userService.login(user2);
//        assertThat(result).isEqualTo("login failed");
//    }


}