package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Validator.DobConstraint;
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
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String repassword;
    String email;
    String phone;
    String address;
    @DobConstraint(min = 16, message = "DOB_INVALID")
    LocalDate dateOfBirth;
}
