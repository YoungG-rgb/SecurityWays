package kg.hagivagi.keycloaktype.services;

import kg.hagivagi.keycloaktype.models.RoleModel;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleService {

    List<RoleModel> getAll();

    RoleModel save(RoleModel roleModel);

    RoleModel update(RoleModel roleModel);

    boolean deleteById(Long id) throws NoSuchElementException;
}
