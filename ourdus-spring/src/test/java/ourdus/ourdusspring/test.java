package ourdus.ourdusspring;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    @Test
    void testt(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String tmp = "0000";
        String password = encoder.encode(tmp);
        System.out.println(password);
    }
}
