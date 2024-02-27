package kg.hagivagi.keycloaktype.rest;

import kg.hagivagi.keycloaktype.models.AuthUserRequest;
import kg.hagivagi.keycloaktype.models.AuthUserResponse;
import kg.hagivagi.keycloaktype.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthUserResponse login(@RequestBody AuthUserRequest authUserRequest) {
        return authService.login(authUserRequest.getLogin(), authUserRequest.getPassword());
    }

}
