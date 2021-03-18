package ourdus.ourdusspring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.util.CompareValueUtils.isEmptyString;

/*
 * referece : https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
 * */

public class JwtUtil {
    private static final String INVALID_TOKEN = "토큰이 유효하지 않습니다.";
    private static final String INVALID_TOKEN_LOGIN = "해당 토큰에 알맞는 사용자 정보가 없습니다.";

    private static final String SECRET_KEY = "secret";

    public static String extractEmail(String token) {
        String email = extractClaim(token, Claims::getIssuer);
        if (isEmptyString(email)) {
            throw new IllegalStateException(INVALID_TOKEN);
        }
        return email;
    }

    public static Collection<? extends GrantedAuthority> extractRole(String token) {
        String role = extractClaim(token, Claims::getSubject);
        if(isEmptyString(role)) {
            throw new IllegalStateException(INVALID_TOKEN);
        }
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), userDetails.getAuthorities().stream().map(auth -> auth.toString()).collect(Collectors.joining()));
    }

    private static String createToken(Map<String, Object> claims, String issuer, String role) {
        return Jwts.builder().setClaims(claims).setIssuer(issuer).setSubject(role).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String createToken(String issuer, String role) {
        return createToken(new HashMap<>(), issuer, role);
    }

    public static void validateToken(String token) {
        if (isTokenExpired(token)) {
            throw new IllegalStateException(INVALID_TOKEN_LOGIN);
        }
    }

}
