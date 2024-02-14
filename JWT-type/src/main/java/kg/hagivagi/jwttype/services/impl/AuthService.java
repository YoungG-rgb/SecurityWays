package kg.hagivagi.jwttype.services.impl;


import kg.hagivagi.jwttype.entities.Permission;
import kg.hagivagi.jwttype.entities.User;
import kg.hagivagi.jwttype.models.AuthUserResponse;
import kg.hagivagi.jwttype.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthUserResponse login(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = ((User) authentication.getPrincipal());
        List<String> permissions = user.getRole().getPermissions().stream().map(Permission::getName).toList();
        log.info("Auth ads user: {}", login);
        return AuthUserResponse.of(login, JwtUtil.generateJwtToken(user), user.getRole().getName(), permissions);
    }
}
