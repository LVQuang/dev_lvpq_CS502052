package dev.lvpq.CS502052.Exception.DefineExceptions;

import dev.lvpq.CS502052.Exception.Error.OTPExceptionCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class OTPException extends RuntimeException {
    OTPExceptionCode code;
}
