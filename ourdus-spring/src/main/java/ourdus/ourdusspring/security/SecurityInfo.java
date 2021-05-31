package ourdus.ourdusspring.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityInfo {
    public static Long getUserId() {
        return Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
