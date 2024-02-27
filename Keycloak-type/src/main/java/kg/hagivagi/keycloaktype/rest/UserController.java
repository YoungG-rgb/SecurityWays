package kg.hagivagi.keycloaktype.rest;

import kg.hagivagi.keycloaktype.models.FilterRequest;
import kg.hagivagi.keycloaktype.models.UserModel;
import kg.hagivagi.keycloaktype.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/filter")
    public Page<UserModel> getAll(@RequestBody FilterRequest filterRequest, Pageable pageable) {
        return userService.getAll(filterRequest, pageable);
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel) throws Exception {
        return userService.save(userModel);
    }

    @PutMapping
    public UserModel update(@RequestBody UserModel userModel) throws Exception {
        return userService.update(userModel);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) throws Exception {
        return userService.deleteById(id);
    }
}
