package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController()
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
    public ApiResponse<String> login(@RequestBody LoginRequest request) {
        if (!authenticationService.login(request)) return null;
        return ApiResponse.<String>builder()
                .code(200)
                .message("Login Success !")
                .build();
    }
}
