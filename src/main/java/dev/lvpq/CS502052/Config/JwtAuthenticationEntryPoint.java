package dev.lvpq.CS502052.Config;

import dev.lvpq.CS502052.Exception.Error.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final String EXCEPTION_URL = "/exception";
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException{
        try {
            var message = ErrorCode.UNAUTHENTICATED.getMessage();
            response.sendRedirect(EXCEPTION_URL+ "?message="
                    + URLEncoder.encode(message, StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
        }
    }
}
