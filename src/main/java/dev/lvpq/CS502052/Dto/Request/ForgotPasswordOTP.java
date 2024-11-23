package dev.lvpq.CS502052.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ForgotPasswordOTP {
    @NotBlank(message = "OTP is not empty")
    String OTP;
}
