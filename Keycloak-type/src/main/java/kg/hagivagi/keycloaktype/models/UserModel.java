package kg.hagivagi.keycloaktype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {

    Long id;

    @NonNull
    String login;

    @NonNull
    String password;

    String name;

    String lastname;

    String patronymic;

    Boolean isActive;

    @NonNull
    String roleName;
}
