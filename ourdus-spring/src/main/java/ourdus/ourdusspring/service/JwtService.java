package ourdus.ourdusspring.service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ourdus.ourdusspring.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Component
@Service
public class JwtService {

    private String salt = "MYSALT";
    private Long expireMin = 50L;

    public String create(final User user){
        final JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ","JWT");
        builder.setSubject("로그인토큰") //토큰 제목설정
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
                .claim("UserId",user.getId());

        builder.signWith(SignatureAlgorithm.HS256,salt.getBytes());

        final String jwt = builder.compact();
        return jwt;
    }

    public void checkValid(final String jwt){
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String,Object> get(final String jwt){
        checkValid(jwt);
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        }catch(final Exception e){
            throw new RuntimeException();
        }
        return claims.getBody();
    }

    public Long getId(final HttpServletRequest req){
        return Long.valueOf(String.valueOf(get(req.getHeader("jwt-auth-token")).get("UserId")));
    }
}
