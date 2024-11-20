package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Validator.DobConstraint;
import dev.lvpq.CS502052.Validator.PasswordMatches;
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
@PasswordMatches
@Builder
public class RegisterRequest {
    @NotBlank(message = "Username is not empty")
    String username;
    @NotBlank(message = "Password is not empty")
    @Size(min = 8, message = "Password at least 8 characters")
    String password;
    @NotBlank(message = "Repassword is not empty")
    @Size(min = 8, message = "Repassword at least 8 characters")
    String repassword;
    @NotBlank(message = "Email is not empty")
    @Email(message = "Email format is invalid")
    String email;
    String phone;
    String address;
    @DobConstraint(min = 11, message = "At least 11 years to Create Account")
    LocalDate dateOfBirth;
}
