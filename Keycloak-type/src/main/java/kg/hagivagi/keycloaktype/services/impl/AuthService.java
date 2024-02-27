package kg.hagivagi.keycloaktype.services.impl;


import kg.hagivagi.keycloaktype.models.AuthUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthUserResponse login(String login, String password) {
        return null;
    }
}
