package kg.hagivagi.keycloaktype.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_PERMISSIONS_SEQ")
    @SequenceGenerator(name = "AUTH_PERMISSIONS_SEQ", sequenceName = "AUTH_PERMISSIONS_SEQ", allocationSize = 1)
    Long id;

    @Column(name = "name", nullable = false,unique = true)
    String name;

    String description;

    @Column(name = "is_active",columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean isActive;

    @Override
    public String getAuthority() {
        return name;
    }
}
