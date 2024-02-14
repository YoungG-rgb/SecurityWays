package kg.hagivagi.jwttype.rest;


import kg.hagivagi.jwttype.models.AuthUserRequest;
import kg.hagivagi.jwttype.models.AuthUserResponse;
import kg.hagivagi.jwttype.services.impl.AuthService;
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
