package kg.hagivagi.jwttype.configs;

import kg.hagivagi.jwttype.entities.Permission;
import kg.hagivagi.jwttype.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint;
    private final DelegateAuthorizationEntryPoint delegateAuthorizationEntryPoint;
    private final RouteRepository routeRepository;
    private final AuthenticationManager daoAuthenticationManager;
    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${spring.unsecured-paths}")
    private String [] unsecuredPaths;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::authorizeHttpRequests)
                .authenticationManager(daoAuthenticationManager)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(delegatedAuthenticationEntryPoint))
                .exceptionHandling(exception -> exception.accessDeniedHandler(delegateAuthorizationEntryPoint))
                .build();
    }

    private void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        routeRepository.findAllByIsActiveTrue()
                .forEach(route -> {
                    if (Boolean.TRUE.equals(route.getIsPublic())) {
                        registry.requestMatchers(HttpMethod.valueOf(route.getHttpMethod().name()), route.getUri()).permitAll();
                        log.info(String.format("Public route %s::%s added", route.getHttpMethod(), route.getUri()));
                    } else {
                        for (Permission permission: route.getPermissions()) {
                            registry.requestMatchers(HttpMethod.valueOf(route.getHttpMethod().name()), route.getUri()).hasAuthority(permission.getName());
                            log.info(String.format("Permission for route %s", route.getUri()));
                        }
                    }
                });

        registry.requestMatchers(unsecuredPaths).permitAll();
        registry.anyRequest().authenticated();
    }


}
