package ourdus.ourdusspring.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.user.AddressRepository;
import ourdus.ourdusspring.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {
    private final String NOT_FOUND_USER = "찾으려는 사용자 정보가 잘못되었습니다.";
    private final String NOT_FOUND_ADDRESS = "찾으려는 주소 정보가 잘못되었습니다.";

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
        User user = findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
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
    public String update(Long userId, User user) {
        checkNotNull(user);
        User findUser = findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
        findUser.changeInfo(user);
        return "update success";
    }

    @Transactional(readOnly = true)
    public String findUserId(String tel) {
        checkNotNull(tel);
        User user = userRepository.findByTel(tel)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
        return user.getEmail();
    }

    @Transactional(readOnly = true)
    public String findPW(String email, String tel) {
        checkNotNull(email);
        checkNotNull(tel);
        User user = userRepository.findByEmailAndTel(email, tel)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
        return user.getPassword();
    }

    @Transactional
    public Address AddAddress(Long userId, Address address) {
        User user = findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

    @Transactional
    public String deleteAddress(Long userId, Long address_id) {
        Address address = findAddressById(address_id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ADDRESS));
        address.validOwner(userId);
        addressRepository.deleteById(address_id);
        return "Address delete success";
    }

    @Transactional
    public Address editAddress(Long userId, Long addressId, Address address) {
        Address findAddress = findAddressById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ADDRESS));
        findAddress.validOwner(userId);
        findAddress.changeAddress(address);
        return findAddress;
    }

    @Transactional
    public Address editAddress(Long addressId, Address address) {
        Address findAddress = findAddressById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ADDRESS));
        findAddress.changeAddress(address);
        return findAddress;
    }

    @Transactional(readOnly = true)
    public List<Address> findAddressList(Long userId) {
        User user = findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
        return user.getAddressList();
    }

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        User findUser = findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER));
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
