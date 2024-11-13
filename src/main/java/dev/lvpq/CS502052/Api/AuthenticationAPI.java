package dev.lvpq.CS502052.Api;

import com.nimbusds.jose.JOSEException;
import dev.lvpq.CS502052.Dto.Request.IntrospectRequest;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.LogoutRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.IntrospectResponse;
import dev.lvpq.CS502052.Dto.Response.LoginResponse;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController()
@Slf4j
public class AuthenticationAPI {
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ApiResponse.<RegisterResponse>builder()
                .code(200)
                .message("Register Success !")
                .result(authenticationService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody @Valid LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        var response = authenticationService.login(request);
        var token = response.getToken();
        httpRequest.getSession().setAttribute("myToken", token);
        log.info("Login: " + httpRequest.getSession().getAttribute("myToken"));
        return ApiResponse.<LoginResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(HttpServletRequest httpRequest)
            throws ParseException, JOSEException {
        var token = httpRequest.getSession().getAttribute("myToken").toString();
        log.info("Logout: " + token);
        var request = LogoutRequest.builder()
                .token(token)
                .build();

        if (!authenticationService.logout(request))
            throw new AppException();

        httpRequest.getSession().removeAttribute("myToken");

        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .result(authenticationService.introspect(request))
                .build();
    }
}
