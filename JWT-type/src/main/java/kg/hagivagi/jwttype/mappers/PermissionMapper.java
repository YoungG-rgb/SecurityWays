package kg.hagivagi.jwttype.mappers;


import kg.hagivagi.jwttype.entities.Permission;
import kg.hagivagi.jwttype.models.PermissionModel;
import org.mapstruct.Mapper;

@Mapper
public interface PermissionMapper {

    Permission toEntity(PermissionModel model);

    PermissionModel toModel(Permission entity);

}
