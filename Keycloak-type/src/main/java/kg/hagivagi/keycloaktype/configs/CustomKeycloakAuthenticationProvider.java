package kg.hagivagi.keycloaktype.configs;

import kg.hagivagi.keycloaktype.entities.User;
import kg.hagivagi.keycloaktype.models.exceptions.NotFoundException;
import kg.hagivagi.keycloaktype.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomKeycloakAuthenticationProvider extends KeycloakAuthenticationProvider {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = getUserNameFromKeycloakToken(authentication).split("@")[0];
            User user = userRepository.findByLogin(username).orElseThrow(() -> new NotFoundException("No user found with username: " + username));
            Collection<? extends GrantedAuthority> userAuthorities = getUserAuthorities(user);

            return createKeycloakAuthentication(authentication, userAuthorities);
        } catch (Exception exception){
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    private KeycloakAuthenticationToken createKeycloakAuthentication(Authentication authentication, Collection<? extends GrantedAuthority> allAuthorities) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        return new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(), allAuthorities);
    }

    @SuppressWarnings("unchecked")
    private String getUserNameFromKeycloakToken(Authentication authentication) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) token.getPrincipal();
        return Optional.ofNullable(principal.getKeycloakSecurityContext().getToken().getPreferredUsername())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("No credentials found in context"));
    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(User user) {
        if (user.getRole() == null) return Collections.emptyList();
        if (user.getRole().getPermissions() == null) return Collections.emptyList();
        return user.getRole().getPermissions();
    }

}
