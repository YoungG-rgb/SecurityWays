package kg.hagivagi.keycloaktype.mappers;



import kg.hagivagi.keycloaktype.entities.Role;
import kg.hagivagi.keycloaktype.models.RoleModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PermissionMapper.class)
public interface RoleMapper {

    @Mapping(target = "permissions", source = "permissionModels")
    Role toEntity(RoleModel model);

    @InheritInverseConfiguration
    RoleModel toModel(Role entity);
}
