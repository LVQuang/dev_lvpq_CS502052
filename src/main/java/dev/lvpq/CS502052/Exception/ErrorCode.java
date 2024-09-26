package dev.lvpq.CS502052.Exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ErrorCode {
    USER_EXISTED(1001, "User does existed"),
    USER_NOT_EXISTED(1002, "User does not existed"),
    PASSWORD_INVALID(1003, "Password at least 8 characters"),
    PASSWORD_NOT_MATCHES(1004, "Password not correct");
    int code;
    String message;
}
