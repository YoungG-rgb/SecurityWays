package kg.hagivagi.jwttype.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLES_SEQ")
    @SequenceGenerator(name = "AUTH_ROLES_SEQ", sequenceName = "AUTH_ROLES_SEQ", allocationSize = 1)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "auth_roles_permissions",
            joinColumns = @JoinColumn(name = "auth_role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id"))
    Set<Permission> permissions = new HashSet<>();

}
