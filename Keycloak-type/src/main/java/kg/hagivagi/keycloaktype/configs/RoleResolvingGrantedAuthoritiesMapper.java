package kg.hagivagi.keycloaktype.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.util.Collection;

@RequiredArgsConstructor
public class RoleResolvingGrantedAuthoritiesMapper extends NullAuthoritiesMapper {
    private final GrantedAuthoritiesMapper delegate;

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return delegate.mapAuthorities(authorities);
    }
}
