package dev.lvpq.CS502052.Controller;

import com.nimbusds.jose.JOSEException;
import dev.lvpq.CS502052.Dto.Request.*;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        session.getAttribute("myToken");
        model.addAttribute("login", new LoginRequest());
        return "Authentication/login";
    }

    @PostMapping("/login")
    String Login(HttpServletRequest request,
                 @Valid @ModelAttribute("login") LoginRequest login,
                 BindingResult bindingResult, Model model) throws ParseException, JOSEException
    {
        HttpSession session = request.getSession();
        String token =(String) session.getAttribute("myToken");

        if(token != null) {
            authenticationService
                    .logout(LogoutRequest.builder()
                            .token(token)
                            .build());
            session.removeAttribute("myToken");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/login";
        }
        var response = authenticationService.login(login);
        request.getSession().setAttribute("myToken", response.getToken());
        var roles = response.getRoles();
        if (roles.contains("Manager")) return "redirect:/admin";
        return "redirect:/home";
    }

    @GetMapping("/register")
    String Register(HttpServletRequest request, Model model) {
        model.addAttribute("register", new RegisterRequest());
        return "Authentication/register";
    }

    @PostMapping("/register")
    String Register(HttpServletRequest request,
                    @Valid @ModelAttribute("register") RegisterRequest register,
                    BindingResult bindingResult, Model model) {
        log.info("Register: {}", register.getEmail());
        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("globalErrors", bindingResult.getGlobalErrors());
            return "/client_layout/register";
        }
        authenticationService.register(register);
        return "redirect:/login";
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

