package kg.hagivagi.keycloaktype.repositories;


import kg.hagivagi.keycloaktype.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
