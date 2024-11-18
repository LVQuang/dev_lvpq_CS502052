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
public enum OTPExceptionCode {
    OTP_NOT_CORRECT(3001, "OTP is not correct", HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(3001, "OTP is expired", HttpStatus.BAD_REQUEST),;

    int code;
    String message;
    HttpStatusCode statusCode;
}
