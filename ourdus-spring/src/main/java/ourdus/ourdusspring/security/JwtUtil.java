package ourdus.ourdusspring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static ourdus.ourdusspring.util.StringUtils.isEmptyString;

/*
 * referece : https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
 * */

@Service
public class JwtUtil {
    private static final String INVALID_TOKEN = "토큰이 유효하지 않습니다.";
    private static final String INVALID_TOKEN_LOGIN = "해당 토큰에 알맞는 사용자 정보가 없습니다.";

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        String username = extractClaim(token, Claims::getSubject);
        if (isEmptyString(username)) {
            throw new IllegalStateException(INVALID_TOKEN);
        }
        return username;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public void validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if ((!username.equals(userDetails.getUsername()) || isTokenExpired(token))) {
            throw new IllegalStateException(INVALID_TOKEN_LOGIN);
        }
    }

}
