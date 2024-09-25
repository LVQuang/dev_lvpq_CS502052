package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisterRequest {
    String name;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String email;
    String phone;
}
