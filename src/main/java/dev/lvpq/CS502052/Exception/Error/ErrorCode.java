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
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Sorry we need more time to repair this error", HttpStatus.INTERNAL_SERVER_ERROR),
    LOGIN_ERROR(1004, "Website have problems related to Authentication", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "You need to login or create an account to access this website", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You don't have permissions", HttpStatus.FORBIDDEN),
    PRODUCT_NOT_EXISTED(1010, "Product is not exited", HttpStatus.NOT_FOUND ),
    TOKEN_TIME(1011 , "Token is expiry time", HttpStatus.BAD_REQUEST),
    TOKEN_CRASH(1012 , "Token is invalidated", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatusCode statusCode;
}
