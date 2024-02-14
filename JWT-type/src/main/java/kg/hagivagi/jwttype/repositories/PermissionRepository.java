package kg.hagivagi.jwttype.repositories;

import kg.hagivagi.jwttype.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
