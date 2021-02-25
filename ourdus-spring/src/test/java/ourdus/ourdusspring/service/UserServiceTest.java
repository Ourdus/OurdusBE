
//package ourdus.ourdusspring.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ourdus.ourdusspring.repository.UserRepository;
//
////import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;
//
//@DataJpaTest
////@EnableConfigurationProperties(value= SpringConfig.class)
//class UserServiceTest {
//
////    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void beforeEach(){
//        userService= new UserService(userRepository);
//    }
//

package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;

//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setUp(){

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
//    void join() {
//        //given
////       UserDTO user1 =new UserDTO();
////       user1.setEmail("abc@naver.com");
////       UserDTO user2 = new UserDTO();
////       user2.setEmail("abc@naver.com");
////        //when
////        userService.join(user1);
////        //then
////        String result = userService.join(user2);
////        assertThat(result).isEqualTo("join failed");
//    }

//
////    @Test
////    void login() {
////        //given
////        UserDTO user1 = new UserDTO();
////        user1.setPassword("0000");
////        user1.setEmail("000@naver.com");
////        userService.join(user1);
////
////        UserDTO user2 = new UserDTO();
////        user2.setPassword("1111");
////        user2.setEmail("000@naver.com");
////        //when
////        userService.login(user2);
////        //then
////        String result = userService.login(user2);
////        assertThat(result).isEqualTo("login failed");
////    }
//
//
//}

    @Test
    void 주소넣기_및_수정을_테스트(){
//        Address address = Address.createBuilder()
//                                .name("수령인")
//                                .phone("받는사람번호")
//                                .zipcode("우편번호")
//                                .addressMain("메인주소")
//                                .addressSub("서브주소")
//                                .build();
//        Long userId = 1L;
//        주소찾기를_테스트();
//        Address getAddress = userService.AddAddress(userId, address);
//        assertThat(address.getName(), is(equalTo(address.getName())));
//        System.out.println("*---추가");
//        주소찾기를_테스트();
//        String changeName = "바뀐이름";
//        address.setName(changeName);
//        userService.editAddress(getAddress.getId(), address);
//        주소찾기를_테스트();

    }

    @Test
    void 주소찾기를_테스트(){
        List<Address> list = userService.getAddressList(1L);
        for(Address address : list){
            System.out.println(address);
        }
    }

    @Test
    void 주소삭제를_테스트(){
        주소찾기를_테스트();
        userService.deleteAddress(1L);
        주소찾기를_테스트();
    }

}

