package kg.hagivagi.jwttype.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserResponse {
    String login;
    String token;
    String role;
    List<String> permissions;
}
