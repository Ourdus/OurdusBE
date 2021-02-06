package ourdus.ourdusspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.AddressRepository;
import ourdus.ourdusspring.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(AddressRepository addressRepository,UserRepository userRepository ) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public User login(User loginUser){
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        Optional<User> user1 = userRepository.findByEmail(email);
        if(user1.isPresent()){
            User result = user1.get();
            if(result.getPassword().equals(password)){
                return result;
            }else{
                throw new RuntimeException("비밀번호가 틀렸습니다.");
            }
        }
        throw new RuntimeException("아이디가 존재하지 않습니다.");
    }

    public String join(User user){
        Optional<User> result = userRepository.findByEmail(user.getEmail());
        result.orElseThrow(() -> new IllegalStateException("join failed"));
        userRepository.save(user);
        return "join success";  //TODO token 생성 필요
    }

    public String getServerInfo(){
        return "made by KSY";
    }

    public String delete(Long id){
        if(!userRepository.existsById(id)) new NoSuchElementException("delete failed");
        userRepository.deleteById(id);
        return "delete success";
    }

    public String update(User user){
        Optional<User> result = userRepository.findByEmail(user.getEmail());
        if(!result.isPresent()) new NoSuchElementException("update failed");
        User findUser = result.get();
        findUser.setTel(user.getTel());
        findUser.setName(user.getName());
        return "update success";
    }

//    public Optional<User> info(Long id){
//        return userRepository.findById(id);
//    }
    public String AddAddress(Long userId,String addAddress) {

        //배송지 생성
        Address address = Address.createAddress(addAddress);

        //유저 생성
//        User user = User.createUser(address);
        //엔티티조회
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User findUser = user.get();
            findUser.addAddress(address);

            //유저 저장
            userRepository.save(findUser);
            address.setUser(findUser);
            addressRepository.save(address);
            if (findUser.getId().equals(userId)) {
                return "Address add success";
            } else {
                throw new NoSuchElementException("Address add failed");
            }

        } else {
            throw new NoSuchElementException("Address add failed");
        }
    }



//    public void DeleteAddress(Long userId){
//        Optional<User> user = userRepository.findById(userId);
//        User findUser = user.get();
//
//    }

}
