package kg.hagivagi.keycloaktype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserResponse {
    String login;
    String token;
    String role;
    List<String> permissions;
}
