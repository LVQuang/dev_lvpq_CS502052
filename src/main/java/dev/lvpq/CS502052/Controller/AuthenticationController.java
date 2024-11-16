package dev.lvpq.CS502052.Controller;

import com.nimbusds.jose.JOSEException;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.LogoutRequest;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class AuthenticationController {

    AuthenticationService authenticationService;

    @GetMapping({"/login","/login.html"})
    String Login(HttpServletRequest request, Model model) throws ParseException, JOSEException {
        HttpSession session = request.getSession();
        String token =(String) session.getAttribute("myToken");

        if(token != null) {
            authenticationService
                    .logout(LogoutRequest.builder()
                            .token(token)
                            .build());
            session.removeAttribute("myToken");
        }
        model.addAttribute("login", new LoginRequest());
        return "/client_layout/login";
    }

    @PostMapping("/login")
    String Login(@ModelAttribute("login") LoginRequest login
            , HttpServletRequest request)
    {
        var response = authenticationService.login(login);
        request.getSession().setAttribute("myToken", response.getToken());
        log.info("Login Token: {}", response.getToken());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    String Logout(HttpServletRequest httpRequest)
            throws ParseException, JOSEException {
        var token = httpRequest.getSession().getAttribute("myToken").toString();
        log.info("Logout Token: {}", token);
        var request = LogoutRequest.builder()
                .token(token)
                .build();

        if (!authenticationService.logout(request))
            throw new AppException();

        httpRequest.getSession().removeAttribute("myToken");

        return "redirect:/home";
    }
}
