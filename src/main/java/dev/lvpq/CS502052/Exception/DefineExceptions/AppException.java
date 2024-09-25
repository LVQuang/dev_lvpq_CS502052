package dev.lvpq.CS502052.Exception.DefineExceptions;

import dev.lvpq.CS502052.Exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class AppException extends RuntimeException{
    ErrorCode errorCode;
}
