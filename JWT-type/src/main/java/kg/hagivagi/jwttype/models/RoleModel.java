package kg.hagivagi.jwttype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel {
    Long id;
    String name;
    String description;
    Set<PermissionModel> permissionModels;
}
