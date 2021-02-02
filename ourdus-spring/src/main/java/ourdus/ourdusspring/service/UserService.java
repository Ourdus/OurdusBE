package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

//import ourdus.ourdusspring.repository.SpringDataJpaUserRepository;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
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
}
