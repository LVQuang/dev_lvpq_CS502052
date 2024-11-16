package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LoginRequest {
    @NotBlank(message = "EMAIL_BLANK")
    @Email(message = "EMAIL_FORMAT")
    String email;
    @NotBlank(message = "PASSWORD_BLANK")
    @Size(min = 8, message = "PASSWORD_LENGTH")
    String password;
}
