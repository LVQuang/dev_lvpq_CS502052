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
    String username;
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;
    @Size(min = 8, message = "Repassword must be at least 8 characters")
    String repassword;
    @Email(message = "Invalid email format")
    String email;
    String phone;
    String address;
    @DobConstraint(min = 16, message = "DOB_INVALID")
    LocalDate dateOfBirth;
}
