package kg.hagivagi.keycloaktype.rest;

import kg.hagivagi.keycloaktype.models.RoleModel;
import kg.hagivagi.keycloaktype.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<RoleModel> getAll(){
        return roleService.getAll();
    }

    @PostMapping
    public RoleModel save(@RequestBody RoleModel roleModel) {
        return roleService.save(roleModel);
    }

    @PutMapping
    public RoleModel update(@RequestBody RoleModel roleModel) {
        return roleService.update(roleModel);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) throws NoSuchElementException {
        return roleService.deleteById(id);
    }
}
