package dev.lvpq.CS502052.Exception;

import dev.lvpq.CS502052.Dto.Request.*;
import dev.lvpq.CS502052.Exception.DefineExceptions.*;
import dev.lvpq.CS502052.Exception.Error.ErrorCode;
import dev.lvpq.CS502052.Exception.Error.ForgotPasswordExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String EXCEPTION_URL = "/exception";

    @ExceptionHandler(value = RuntimeException.class)
    void handlingRuntimeException(RuntimeException exception,
                                  HttpServletResponse response)
            throws IOException {
        log.error("Exception: {}", String.valueOf(exception));
        var message = ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage();
        response.sendRedirect(EXCEPTION_URL+ "?message="
                + URLEncoder.encode(String.valueOf(message), StandardCharsets.UTF_8));
    }

    @ExceptionHandler(AppException.class)
    void handlingAppException(AppException exception,
                                      HttpServletResponse response)
            throws IOException {
        var message = exception.getErrorCode().getMessage();
        response.sendRedirect(EXCEPTION_URL+ "?message="
                + URLEncoder.encode(String.valueOf(message), StandardCharsets.UTF_8));
    }

    @ExceptionHandler(AccessDeniedException.class)
    void handlingAccessDeniedException(AccessDeniedException exception,
                                       HttpServletResponse response) throws IOException {
        var message = ErrorCode.UNAUTHORIZED.getMessage();
        response.sendRedirect(EXCEPTION_URL+ "?message="
                + URLEncoder.encode(String.valueOf(message), StandardCharsets.UTF_8));
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
    ModelAndView handlingAuthException(AuthException exception, HttpServletRequest request) {
        log.debug("Authentication Exception Handler");
        var endpoint = request.getRequestURL().toString();
        var code = exception.getAuthExceptionCode();
        ModelAndView model;

        if (endpoint.contains("/login")) {
            model = new ModelAndView("/client_layout/login");
            model.addObject("login", new LoginRequest());
        } else {
            model = new ModelAndView("/client_layout/register");
            model.addObject("register", new RegisterRequest());
        }
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

    @ExceptionHandler(ResetPasswordException.class)
    ModelAndView handlingResetPasswordException(ResetPasswordException exception) {
        var code = exception.getCode();
        var otp = exception.getOtp();
        var model = new ModelAndView("/client_layout/resetPassword");
        model.addObject("resetPassword", ResetPassword.builder().otp(otp).build());
        model.addObject("error", code.getMessage());
        return model;
    }

    ModelAndView handlingForgotExceptionCombination(ForgotPasswordExceptionCode code) {
        var model = new ModelAndView("/client_layout/forgotPassword");
        model.addObject("forgotPassword", new ForgotPasswordRequest());
        model.addObject("error", code.getMessage());
        return model;
    }
}