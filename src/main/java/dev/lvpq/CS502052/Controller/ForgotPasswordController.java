package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Dto.Request.ForgotPasswordOTP;
import dev.lvpq.CS502052.Dto.Request.ForgotPasswordRequest;
import dev.lvpq.CS502052.Dto.Request.ResetPassword;
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

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/forgotPassword")
@Controller
public class ForgotPasswordController {
    @GetMapping()
    String ForgotPassword(Model model) {
        log.info("View Forgot Password");
        model.addAttribute("forgotPassword", new ForgotPasswordRequest());
        return "/client_layout/forgotPassword";
    }

    @PostMapping()
    String ForgotPassword(@Valid @ModelAttribute("forgotPassword") ForgotPasswordRequest forgotPassword,
                          BindingResult bindingResult, Model model) {
        log.info("Email: {}", forgotPassword.getEmail());
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/forgotPassword";
        }
        return "redirect:/forgotPassword/forgotPasswordOTP";
    }

    @GetMapping("/forgotPasswordOTP")
    String ForgotPasswordOTP(Model model) {
        model.addAttribute("forgotPasswordOTP", new ForgotPasswordOTP());
        return "/client_layout/forgotPasswordOTP";
    }

    @PostMapping("/forgotPasswordOTP")
    String ForgotPasswordOTP(@Valid @ModelAttribute("forgotPasswordOTP") ForgotPasswordOTP forgotPasswordOTP,
                             BindingResult bindingResult, Model model) {
        log.info("OTP: {}", forgotPasswordOTP.getOTP());
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/forgotPasswordOTP";
        }
        return "redirect:/forgotPassword/forgotPasswordOTP/resetPassword";
    }

    @GetMapping("/forgotPasswordOTP/resetPassword")
    String ResetPassword(Model model) {
        model.addAttribute("resetPassword", new ResetPassword());
        return "/client_layout/resetPassword";
    }

    @PostMapping("/forgotPasswordOTP/resetPassword")
    String ResetPassword(@Valid @ModelAttribute("resetPassword") ResetPassword resetPassword,
                         BindingResult bindingResult, Model model) {
        log.info("Reset Password: {}", resetPassword.getPassword());
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/resetPassword";
        }
        return "redirect:/home";
    }
}
