package dev.lvpq.CS502052.Exception;

import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handlingRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();
        var errorCode = ErrorCode.valueOf(enumKey);
        return ResponseEntity.badRequest().body(packageApiResponse(errorCode));
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingAppException(AppException exception) {
        var errorCode = exception.getErrorCode();
        return ResponseEntity.badRequest().body(packageApiResponse(errorCode));
    }

//    Define Utils Function To Handle Exception
    ApiResponse<ErrorCode> packageApiResponse(ErrorCode errorCode) {
        return ApiResponse.<ErrorCode>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }
}
