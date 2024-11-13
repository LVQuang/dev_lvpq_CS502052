package dev.lvpq.CS502052.Exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User does existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User does not existed", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(1003, "Password at least 8 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCHES(1004, "Password not correct", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You don't have permissions", HttpStatus.FORBIDDEN),
    DOB_INVALID(1008, "At least {min} years old", HttpStatus.BAD_REQUEST),
    KEY_INVALID(1009, "Invalid Key of Error Code", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_EXISTED(1010, "Product is not exited", HttpStatus.NOT_FOUND ),
    TOKEN_TIME(1011 , "Token is expiry time", HttpStatus.BAD_REQUEST),
    TOKEN_CRASH(1012 , "Token is invalidated", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatusCode statusCode;
}
