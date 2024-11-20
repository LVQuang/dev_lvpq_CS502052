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
public enum ForgotPasswordExceptionCode {
    EMAIL_NOT_EXISTED(2001, "Email does not existed", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_SEND(2002, "Email can not send", HttpStatus.BAD_REQUEST),;

    int code;
    String message;
    HttpStatusCode statusCode;
}
