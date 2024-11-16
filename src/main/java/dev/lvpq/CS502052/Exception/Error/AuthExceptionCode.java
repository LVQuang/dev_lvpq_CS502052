package dev.lvpq.CS502052.Exception.Error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum AuthExceptionCode {
    USER_EXISTED(1001, "User does existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User does not existed", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(1003, "Password at least 8 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCHES(1004, "Password not correct", HttpStatus.BAD_REQUEST),
    DOB_INVALID(1008, "At least {min} years old", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatusCode statusCode;
}
