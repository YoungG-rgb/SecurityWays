package kg.hagivagi.jwttype.services;


import kg.hagivagi.jwttype.entities.User;
import kg.hagivagi.jwttype.models.FilterRequest;
import kg.hagivagi.jwttype.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable);

    UserModel save(UserModel userModel) throws Exception;

    UserModel update(UserModel userModel) throws Exception;

    boolean deleteById(Long id) throws Exception;

    User findByLogin(String login) throws Exception;

}
