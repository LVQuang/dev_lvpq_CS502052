package dev.lvpq.CS502052.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserTrackingFilter  extends OncePerRequestFilter {
    private final String[] STATIC_RESOURCE = {
            "/css/**", "/js/**", "/img/**", "/admin_style/**", "/fonts/**", "/Karma Shop-doc", "/scss", "/favicon.ico"
    };

    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (request.getAttribute("UserTrackingFilter.PROCESSED") == null) {
            request.setAttribute("UserTrackingFilter.PROCESSED", true);

            for (String pattern : STATIC_RESOURCE) {
                if (pathMatcher.match(pattern, uri)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            log.info("Tracking request: {}", uri);
        }

        filterChain.doFilter(request, response);
    }
}
