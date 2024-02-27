package kg.hagivagi.keycloaktype.configs;

import kg.hagivagi.keycloaktype.entities.Permission;
import kg.hagivagi.keycloaktype.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
@EnableWebSecurity
@KeycloakConfiguration
@RequiredArgsConstructor
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    private final RouteRepository routeRepository;
    private final CorsConfigurationSource corsConfigurationSource;
    private final CustomKeycloakAuthenticationProvider customKeycloakAuthenticationProvider;
    private static final String [] UNSECURED_RESOURCE_LIST = new String[]{
            "/public/**", "/static/**", "/resources/**", "/cache-control/**" , "/img/**", "/js/**", "/webjars/**", "/ext-api/**", "/"
    };

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customKeycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::authorizeHttpRequests);
    }

    private void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        routeRepository.findAllByIsActiveTrue()
                .forEach(route -> {
                    if (Boolean.TRUE.equals(route.getIsPublic())) {
                        registry.antMatchers(route.getHttpMethod().name(), route.getUri()).permitAll();
                        log.info(String.format("Public route %s::%s added", route.getHttpMethod(), route.getUri()));
                    } else {
                        for (Permission permission: route.getPermissions()) {
                            registry.antMatchers(route.getHttpMethod().name(), route.getUri()).hasAuthority(permission.getName());
                            log.info(String.format("Permission for route %s", route.getUri()));
                        }
                    }
                });

        registry.antMatchers(UNSECURED_RESOURCE_LIST).permitAll();
        registry.anyRequest().authenticated();
    }
}
