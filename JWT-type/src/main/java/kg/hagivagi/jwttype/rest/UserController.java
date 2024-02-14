package kg.hagivagi.jwttype.rest;

import kg.hagivagi.jwttype.models.FilterRequest;
import kg.hagivagi.jwttype.models.UserModel;
import kg.hagivagi.jwttype.services.UserService;
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
