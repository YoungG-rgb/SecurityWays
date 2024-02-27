package kg.hagivagi.keycloaktype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserRequest {

    @NonNull
    String login;

    @NonNull
    String password;
}
