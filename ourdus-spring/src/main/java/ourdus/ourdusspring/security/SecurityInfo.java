package ourdus.ourdusspring.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityInfo {
    public static String getUserEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
