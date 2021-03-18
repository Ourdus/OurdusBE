package ourdus.ourdusspring.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.user.AddressRepository;
import ourdus.ourdusspring.repository.user.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;

@Service
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(PasswordEncoder passwordEncoder, AddressRepository addressRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public User login2(User loginUser){
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

    @Transactional
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("아이디가 없습니다"));
        user.login(passwordEncoder, password);
        return user;
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


    @Transactional
    public String delete(Long id){
        if(!userRepository.existsById(id)) new NoSuchElementException("delete failed");
        userRepository.deleteById(id);
        return "delete success";
    }

    @Transactional
    public String update(Long id,User user){
        User findUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no id"));
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

    @Transactional
    public Address AddAddress(Long userId,Address address) {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다."));
        address.setUser(user);
        addressRepository.save(address);
        //user.addAddress(address);
        //양방향 연관관계 저장시 연관관계의 주인은 '외래키가 있는 곳'인 address.user가 된다. 고로 주인에게만 설정해주어야한다.
        return address;
    }

    public String deleteAddress(Long address_id) {
        if(!addressRepository.existsById(address_id)) new NoSuchElementException("Address delete failed");
        addressRepository.deleteById(address_id);
        return "Address delete success";
    }

    @Transactional
    public Address editAddress(Long address_id,Address address) {
        Address result  = addressRepository.findById(address_id).orElseThrow(() -> new NoSuchElementException("address update failed") );
        result.setName(address.getName());
        result.setPhone(address.getPhone());
        result.setZipcode(address.getZipcode());
        result.setAddressMain(address.getAddressMain());
        result.setAddressSub(address.getAddressSub());
        return result;
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressList(Long userId) {
        User user = findById(userId).orElse(null);
        return user.getAddressList();
    }

    @Transactional(readOnly = true)
    public User getUserInfo(Long userId) {
        User findUser = findById(userId).orElseThrow(() -> new NoSuchElementException("No User Info"));
        return findUser;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        checkNotNull(email);
        return userRepository.findByEmail(email);
    }

    @Transactional
    public Optional<User> findById(Long id) {
        checkNotNull(id);
        return userRepository.findById(id);
    }

}
