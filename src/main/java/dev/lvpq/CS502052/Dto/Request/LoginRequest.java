package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LoginRequest {
    String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
}
