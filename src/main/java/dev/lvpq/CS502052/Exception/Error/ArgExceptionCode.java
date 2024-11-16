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
public enum ArgExceptionCode {
    PASSWORD_LENGTH(2001, "Password at least 8 characters", HttpStatus.BAD_REQUEST),
    DOB_INVALID(2002, "At least {min} years old", HttpStatus.BAD_REQUEST),
    KEY_INVALID(2003, "Invalid Key of Error Code", HttpStatus.INTERNAL_SERVER_ERROR),
    PASSWORD_BLANK(2004, "Password cannot be empty", HttpStatus.BAD_REQUEST),
    EMAIL_FORMAT(2005, "Invalid email format", HttpStatus.BAD_REQUEST),
    EMAIL_BLANK(2006, "Email cannot be empty", HttpStatus.BAD_REQUEST),
    USERNAME_BLANK(2007, "Username cannot be empty", HttpStatus.BAD_REQUEST),
    REPASSWORD_BLANK(2008, "Repassword cannot be empty", HttpStatus.BAD_REQUEST),
    REPASSWORD_LENGTH(2009, "Repassword at least 8 characters", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    HttpStatusCode statusCode;
}
