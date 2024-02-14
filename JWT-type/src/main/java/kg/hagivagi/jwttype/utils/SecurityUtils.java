package kg.hagivagi.jwttype.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    public Authentication getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
