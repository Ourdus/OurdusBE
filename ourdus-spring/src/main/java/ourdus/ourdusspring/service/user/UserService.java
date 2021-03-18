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

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(PasswordEncoder passwordEncoder, AddressRepository addressRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public User login(String email, String password) {
        checkNotNull(password);
        User user = findByEmail(email).orElseThrow(() -> new NoSuchElementException("아이디가 없습니다"));
        user.login(passwordEncoder, password);
        return user;
    }

    @Transactional
    public String join(User user) {
        checkNotNull(user);
        findByEmail(user.getEmail()).ifPresent(exist -> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
        user.join(passwordEncoder);
        userRepository.save(user);
        return user.makeToken();
    }

    @Transactional
    public String delete(Long id) {
        checkNotNull(id);
        userRepository.deleteById(id);
        return "delete success";
    }

    @Transactional
    public String delete(String email) {
        checkNotNull(email);
        userRepository.deleteByEmail(email);
        return "delete success";
    }

    @Transactional
    public String update(String email, User user) {
        checkNotNull(user);
        User findUser = findByEmail(email).orElseThrow(() -> new NoSuchElementException("no id"));
        findUser.changeInfo(user);
        return "update success";
    }

    @Transactional(readOnly = true)
    public String findUserId(String tel) {
        checkNotNull(tel);
        User user = userRepository.findByTel(tel).orElseThrow(() -> new NoSuchElementException("find Id failed"));
        return user.getEmail();
    }

    @Transactional(readOnly = true)
    public String findPW(String email, String tel) {
        checkNotNull(email);
        checkNotNull(tel);
        User user = userRepository.findByEmailAndTel(email, tel)
                .orElseThrow(() -> new NoSuchElementException("find Id failed"));
        return user.getPassword();
    }

    @Transactional
    public Address AddAddress(Long userId, Address address) {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다."));
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

    @Transactional
    public Address AddAddress(String userEmail, Address address) {
        User user = findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다."));
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

    @Transactional
    public String deleteAddress(String userEmail, Long address_id) {
        Address address = findAddressById(address_id).orElseThrow(() -> new NoSuchElementException("지우려는 주소가 없습니다."));
        address.validOwner(userEmail);
        addressRepository.deleteById(address_id);
        return "Address delete success";
    }

    @Transactional
    public Address editAddress(String userEmail, Long addressId, Address address) {
        Address findAddress = findAddressById(addressId).orElseThrow(() -> new NoSuchElementException("address update failed"));
        findAddress.validOwner(userEmail);
        findAddress.changeAddress(address);
        return findAddress;
    }

    @Transactional
    public Address editAddress(Long addressId, Address address) {
        Address findAddress = findAddressById(addressId).orElseThrow(() -> new NoSuchElementException("address update failed"));
        findAddress.changeAddress(address);
        return findAddress;
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressList(Long userId) {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("No User Info"));
        return user.getAddressList();
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressList(String userEmail) {
        User user = findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("No User Info"));
        return user.getAddressList();
    }

    @Transactional(readOnly = true)
    public User getUserInfo(Long userId) {
        User findUser = findById(userId).orElseThrow(() -> new NoSuchElementException("No User Info"));
        return findUser;
    }

    @Transactional(readOnly = true)
    public User getUserInfo(String userEmail) {
        User findUser = findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("No User Info"));
        return findUser;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        checkNotNull(email);
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        checkNotNull(id);
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Address> findAddressById(Long id) {
        checkNotNull(id);
        return addressRepository.findById(id);
    }
}
