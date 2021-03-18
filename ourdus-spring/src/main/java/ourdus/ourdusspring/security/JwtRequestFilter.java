package ourdus.ourdusspring.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ourdus.ourdusspring.util.CompareValueUtils.isEmptyString;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String INVALID_HEADER = "인증 토큰이 필요합니다.";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("jwt-auth-token");

        if (isEmptyString(authorizationHeader)) {
            throw new IllegalStateException(INVALID_HEADER);
        }

        final String jwt = authorizationHeader.substring(7);
        final String email = JwtUtil.extractEmail(jwt);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtUtil.validateToken(jwt);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    email, null, JwtUtil.extractRole(jwt));
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !new AntPathMatcher().match("/api/t/**", request.getServletPath());
    }

}
