package kg.hagivagi.keycloaktype.repositories;

import kg.hagivagi.keycloaktype.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByIsActiveTrue();
}
