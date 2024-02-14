package kg.hagivagi.jwttype.repositories;


import kg.hagivagi.jwttype.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByIsActiveTrue();
}
