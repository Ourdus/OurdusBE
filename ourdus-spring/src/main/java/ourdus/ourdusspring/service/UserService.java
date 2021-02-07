package ourdus.ourdusspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.AddressRepository;
import ourdus.ourdusspring.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
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


    public String delete(Long id){
        if(!userRepository.existsById(id)) new NoSuchElementException("delete failed");
        userRepository.deleteById(id);
        return "delete success";
    }

    public String update(Long id,User user){
        Optional<User> result = userRepository.findById(id);
        if(!result.isPresent()) new NoSuchElementException("update failed");
        User findUser = result.get();
        findUser.setTel(user.getTel());
        findUser.setName(user.getName());
        return "update success";
    }

    public String findUserId(String tel){
        if(!userRepository.findByTel(tel).isPresent()) new NoSuchElementException("find Id failed");
        User user=userRepository.findByTel(tel).get();
        return user.getEmail();
    }

    public String findPW(String email, String tel){
        if(!userRepository.findByEmailAndTel(email,tel).isPresent()) new NoSuchElementException("find pw failed");
        User user=userRepository.findByEmailAndTel(email,tel).get();
        return user.getPassword();
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

    public String deleteAddress(Long address_id) {
        if(!addressRepository.existsById(address_id)) new NoSuchElementException("Address delete failed");
        addressRepository.deleteById(address_id);
        return "Address delete success";
    }

    public String editAddress(Long address_id,String newAddress) {
        Optional<Address> result  = addressRepository.findById(address_id);
        if(!result.isPresent()) new NoSuchElementException("address update failed");
        Address address = result.get();
        address.setAddress(newAddress);
        addressRepository.save(address);

        return "address update success";
    }

    public List<String> getAddressList(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<String> showAddress = new ArrayList<String>();
            User findUser = user.get();
            List<Address> addressList= findUser.getAddressList();
            for(Address address : addressList){
                showAddress.add(address.getAddress());
            }
            return showAddress;
        }else{
            throw new NoSuchElementException("Address info load failed");
        }
    }

    public User getUserInfo(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        if(findUser.isPresent()){
            User user = findUser.get();
            return user;
        }else{
            throw new NoSuchElementException("No User Info");
        }

    }


//    public void DeleteAddress(Long userId){
//        Optional<User> user = userRepository.findById(userId);
//        User findUser = user.get();
//
//    }

}
