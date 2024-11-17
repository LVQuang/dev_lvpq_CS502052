package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ForgotPasswordRequest {
    @NotBlank(message = "Email is not empty")
    @Email(message = "Email format is invalid")
    String email;
}
