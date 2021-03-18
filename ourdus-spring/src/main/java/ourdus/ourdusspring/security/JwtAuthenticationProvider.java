package ourdus.ourdusspring.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.service.user.UserService;

import java.util.Collections;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationProvider(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return processUserAuthentication(String.valueOf(authentication.getPrincipal()), String.valueOf(authentication.getCredentials()));
    }

    private Authentication processUserAuthentication(String principal, String credential) {
        try {
            User user = userService.login(principal, credential);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.singleton(user.getRole()));
            String apiToken = jwtUtil.generateToken(UserPrincipal.of(user));
            authentication.setDetails(new AuthenticationResult(apiToken, user));
            return authentication;
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
