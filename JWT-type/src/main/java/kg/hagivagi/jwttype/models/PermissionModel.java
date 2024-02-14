package kg.hagivagi.jwttype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionModel {
    Long id;
    String name;
    String description;
    Boolean isActive;
}
