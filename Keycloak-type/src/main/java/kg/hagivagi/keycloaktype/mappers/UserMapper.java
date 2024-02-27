package kg.hagivagi.keycloaktype.mappers;



import kg.hagivagi.keycloaktype.entities.User;
import kg.hagivagi.keycloaktype.models.UserModel;
import kg.hagivagi.keycloaktype.repositories.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class UserMapper {
    @Autowired protected RoleRepository roleRepository;

    @Mapping(target = "role", expression = "java( roleRepository.findByName(model.getRoleName())" +
            ".orElseThrow(() -> new java.lang.Exception(String.format(\"no role found with name '%s'\", model.getRoleName()))) )")
    public abstract User toEntity(UserModel model) throws Exception;

    @Mapping(target = "roleName", source = "role.name")
    public abstract UserModel toModel(User entity);
}
