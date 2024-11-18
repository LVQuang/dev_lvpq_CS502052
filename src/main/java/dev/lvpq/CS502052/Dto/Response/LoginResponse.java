package dev.lvpq.CS502052.Dto.Response;

import dev.lvpq.CS502052.Entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LoginResponse {
    boolean authenticated;
    String token;
    Set<String> roles;
}
