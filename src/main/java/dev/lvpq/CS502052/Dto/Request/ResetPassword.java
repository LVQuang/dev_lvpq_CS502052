package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ResetPassword {
    @NotBlank(message = "Password is not empty")
    @Size(min = 8, message = "Password at least 8 characters")
    String password;
    @NotBlank(message = "Repassword is not empty")
    @Size(min = 8, message = "Repassword at least 8 characters")
    String repassword;
}
