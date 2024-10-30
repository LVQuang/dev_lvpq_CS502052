package dev.lvpq.CS502052.Exception;

import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: {}", String.valueOf(exception));
        var errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(packageApiResponse(errorCode));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();
        var errorCode = ErrorCode.valueOf(enumKey);
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(packageApiResponse(errorCode));
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingAppException(AppException exception) {
        var errorCode = exception.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(packageApiResponse(errorCode));
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingAccessDeniedException(AccessDeniedException exception) {
        var errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(packageApiResponse(errorCode));
    }

//    Define Utils Function To Handle Exception
    ApiResponse<ErrorCode> packageApiResponse(ErrorCode errorCode) {
        return ApiResponse.<ErrorCode>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }
}
