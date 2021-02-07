package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.AddressRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
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
        if(result.isPresent())  //email이 이미 존재하면 가입 실패
             throw new RuntimeException("Join failed");
        else {
            userRepository.save(user);
            return "join success";  //TODO token 생성 필요
        }
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

    public String findUserId(String tel){
        if(!userRepository.findByTel(tel).isPresent()) new NoSuchElementException("find Id failed");
        User user=userRepository.findByTel(tel).get();
        //System.out.println(user.getEmail());
        return "find id success";
    }

    public String findPW(String email, String tel){
        if(!userRepository.findByEmailAndTel(email,tel).isPresent()) new NoSuchElementException("find pw failed");
        User user=userRepository.findByEmailAndTel(email,tel).get();
        //System.out.println(user.getPassword());
        return "find pw success";
    }

//    public Optional<User> info(Long id){
//        return userRepository.findById(id);
//    }
    public Address AddAddress(Long userId,Address address) {
        //엔티티조회
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다."));
        address.setUser(user);
        addressRepository.save(address);
        user.addAddress(address);
        return address;
    }

    public String deleteAddress(Long address_id) {
        if(!addressRepository.existsById(address_id)) new NoSuchElementException("Address delete failed");
        addressRepository.deleteById(address_id);
        return "Address delete success";
    }

    public String editAddress(Long address_id,Address address) {
        Address result  = addressRepository.findById(address_id).orElseThrow(() -> new NoSuchElementException("address update failed") );
        result.setName(address.getName());
        result.setPhone(address.getPhone());
        result.setZipcode(address.getZipcode());
        result.setAddressMain(address.getAddressMain());
        result.setAddressSub(address.getAddressSub());
        return "address update success";
    }

    public List<Address> getAddressList(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user.getAddressList();
    }


//    public void DeleteAddress(Long userId){
//        Optional<User> user = userRepository.findById(userId);
//        User findUser = user.get();
//
//    }

}
