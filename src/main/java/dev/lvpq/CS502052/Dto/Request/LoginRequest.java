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
    @NotBlank(message = "Email is not empty")
    @Email(message = "Email format is invalid")
    String email;
    @NotBlank(message = "Password is not empty")
    @Size(min = 8, message = "Password at least 8 characters")
    String password;
}
