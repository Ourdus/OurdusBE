package ourdus.ourdusspring.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.UserDTO;
//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;
import ourdus.ourdusspring.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(UserDTO loginUser){
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        Optional<User> user1 = userRepository.findByEmail(email);
        if(user1.isPresent()){
            User result = user1.get();
            if(result.getPassword().equals(password)){
                return result;
//                return "login success";
            }else{
                throw new RuntimeException("비밀번호가 틀렸습니다.");
            }
        }
//        return "login fail";
        throw new RuntimeException("아이디가 존재하지 않습니다.");
    }

    public String join(UserDTO newUser){
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setTel(newUser.getTel());
        user.setWriterFlag(newUser.getWriterFlag());
        Optional<User> result = userRepository.findByEmail(newUser.getEmail());
        if(result.isPresent()){
            return "join failed";
        }
        userRepository.save(user);
        return "join success";
    }

    public String getServerInfo(){
        return "made by KSY";
    }

    public String delete(Long id){
        userRepository.deleteById(id);
        return "delete success";

//        userRepository.findById(id)
//                .ifPresent(M->{
//                    userRepository.deleteById(id);
//                    return "delete success";
//                });
//        return "delete fail";
//        throw new IllegalStateException("delete fail");

    }

    public String update(UserDTO updateUser){
        String password = updateUser.getPassword();
        String tel = updateUser.getTel();
        String email = updateUser.getEmail();
        Optional<User> findUser = userRepository.findByEmail(email);
        User user = findUser.get();
        user.setPassword(password);
        user.setTel(tel);
        userRepository.save(user);
        return "update success";
    }

    public Optional<User> info(Long id){
        return userRepository.findById(id);
    }
}
