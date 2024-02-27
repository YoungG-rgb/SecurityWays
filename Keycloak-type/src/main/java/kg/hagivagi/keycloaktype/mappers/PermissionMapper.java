package kg.hagivagi.keycloaktype.mappers;


import kg.hagivagi.keycloaktype.entities.Permission;
import kg.hagivagi.keycloaktype.models.PermissionModel;
import org.mapstruct.Mapper;

@Mapper
public interface PermissionMapper {

    Permission toEntity(PermissionModel model);

    PermissionModel toModel(Permission entity);

}
