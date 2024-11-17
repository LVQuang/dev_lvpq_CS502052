package dev.lvpq.CS502052.Exception;

import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.DefineExceptions.AuthException;
import dev.lvpq.CS502052.Exception.Error.ArgExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<ErrorCode>> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: {}", String.valueOf(exception));
        var errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
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

    @ExceptionHandler(AuthException.class)
    ModelAndView handlingAuthException(AuthException exception) {
        var code = exception.getAuthExceptionCode();
        var model = new ModelAndView("/client_layout/login");
        model.addObject("login", new LoginRequest());
        model.addObject("register", new RegisterRequest());
        model.addObject("error", code.getMessage());
        return model;
    }

    //    Define Utils Function To Handle Exception
    ApiResponse<ErrorCode> packageApiResponse(ErrorCode errorCode) {
        return ApiResponse.<ErrorCode>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }

    private ModelAndView authRoute(HttpServletRequest request) {

        var currentEndpoint = request.getRequestURI();
        log.info("Current endpoint: {}", currentEndpoint);

        var model = new ModelAndView();
        if (currentEndpoint.equals("/login")) {
            model.setViewName("/client_layout/login");
            model.addObject("login", new LoginRequest());
        } else if (currentEndpoint.equals("/register")) {
            model.setViewName("/client_layout/register");
            model.addObject("register", new RegisterRequest());
        }


        return model;
    }
}