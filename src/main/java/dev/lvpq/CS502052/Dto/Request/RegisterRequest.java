package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisterRequest {
    @NotBlank(message = "USERNAME_BLANK")
    String username;
    @NotBlank(message = "PASSWORD_BLANK")
    @Size(min = 8, message = "PASSWORD_LENGTH")
    String password;
    @NotBlank(message = "REPASSWORD_BLANK")
    @Size(min = 8, message = "REPASSWORD_LENGTH")
    String repassword;
    @NotBlank(message = "EMAIL_BLANK")
    @Email(message = "EMAIL_FORMAT")
    String email;
    String phone;
    String address;
    @DobConstraint(min = 16, message = "DOB_INVALID")
    LocalDate dateOfBirth;
}
