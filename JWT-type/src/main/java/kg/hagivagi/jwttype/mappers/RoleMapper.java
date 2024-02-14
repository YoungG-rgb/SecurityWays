package kg.hagivagi.jwttype.mappers;


import kg.hagivagi.jwttype.entities.Role;
import kg.hagivagi.jwttype.models.RoleModel;
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
