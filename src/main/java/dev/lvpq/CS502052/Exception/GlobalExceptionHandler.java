package dev.lvpq.CS502052.Exception;

import dev.lvpq.CS502052.Dto.Request.ForgotPasswordOTP;
import dev.lvpq.CS502052.Dto.Request.ForgotPasswordRequest;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.*;
import dev.lvpq.CS502052.Exception.Error.ForgotPasswordExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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

    @ExceptionHandler(MessageException.class)
    ModelAndView handlingMessageException(MessageException exception) {
        var code = exception.getCode();
        return handlingForgotExceptionCombination(code);
    }

    @ExceptionHandler(ForgotPasswordException.class)
    ModelAndView handlingForgotPasswordException(ForgotPasswordException exception) {
        var code = exception.getCode();
        return handlingForgotExceptionCombination(code);
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

    @ExceptionHandler(OTPException.class)
    ModelAndView handlingOTPException(OTPException exception) {
        var code = exception.getCode();
        var model = new ModelAndView("/client_layout/forgotPasswordOTP");
        model.addObject("forgotPasswordOTP", new ForgotPasswordOTP());
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

    ModelAndView handlingForgotExceptionCombination(ForgotPasswordExceptionCode code) {
        var model = new ModelAndView("/client_layout/forgotPassword");
        model.addObject("forgotPassword", new ForgotPasswordRequest());
        model.addObject("error", code.getMessage());
        return model;
    }
}