package dev.lvpq.CS502052.Exception.DefineExceptions;

import dev.lvpq.CS502052.Exception.Error.ResetPasswordCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class ResetPasswordException extends RuntimeException {
    ResetPasswordCode code;
    String otp;
}
