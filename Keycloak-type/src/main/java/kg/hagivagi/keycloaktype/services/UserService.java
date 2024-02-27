package kg.hagivagi.keycloaktype.services;



import kg.hagivagi.keycloaktype.entities.User;
import kg.hagivagi.keycloaktype.models.FilterRequest;
import kg.hagivagi.keycloaktype.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable);

    UserModel save(UserModel userModel) throws Exception;

    UserModel update(UserModel userModel) throws Exception;

    boolean deleteById(Long id) throws Exception;

    User findByLogin(String login) throws Exception;

}
