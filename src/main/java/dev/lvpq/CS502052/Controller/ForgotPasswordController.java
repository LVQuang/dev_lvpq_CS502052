package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Dto.Request.ForgotPasswordOTP;
import dev.lvpq.CS502052.Dto.Request.ForgotPasswordRequest;
import dev.lvpq.CS502052.Dto.Request.ResetPassword;
import dev.lvpq.CS502052.Service.ForgotPasswordService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/forgotPassword")
@Controller
public class ForgotPasswordController {
    ForgotPasswordService forgotPasswordService;

    @GetMapping()
    String ForgotPassword(Model model) {
        log.info("View Forgot Password");
        model.addAttribute("forgotPassword", new ForgotPasswordRequest());
        return "/client_layout/forgotPassword";
    }

    @PostMapping()
    String ForgotPassword(@Valid @ModelAttribute("forgotPassword") ForgotPasswordRequest forgotPassword,
                          BindingResult bindingResult, Model model) throws MessagingException {
        log.info("Email: {}", forgotPassword.getEmail());
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/forgotPassword";
        }
        forgotPasswordService.sendOTP(forgotPassword.getEmail());

        return "redirect:/forgotPassword/forgotPasswordOTP";
    }

    @GetMapping("/forgotPasswordOTP")
    String ForgotPasswordOTP(Model model) {
        model.addAttribute("forgotPasswordOTP", new ForgotPasswordOTP());
        return "/client_layout/forgotPasswordOTP";
    }

    @PostMapping("/forgotPasswordOTP")
    String ForgotPasswordOTP(@Valid @ModelAttribute("forgotPasswordOTP") ForgotPasswordOTP forgotPasswordOTP,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes redirectAttributes) {
        log.info("OTP: {}", forgotPasswordOTP.getOTP());
        if (bindingResult.hasErrors()) {
            model.addAttribute("validation", bindingResult.getAllErrors());
            return "/client_layout/forgotPasswordOTP";
        }
        forgotPasswordService.validateOTP(forgotPasswordOTP.getOTP());

        redirectAttributes.addFlashAttribute("otp", forgotPasswordOTP.getOTP());

        return "redirect:/forgotPassword/forgotPasswordOTP/resetPassword";
    }

    @GetMapping("/forgotPasswordOTP/resetPassword")
    String ResetPassword(@ModelAttribute("otp") String otp, Model model) {
        model.addAttribute("resetPassword", ResetPassword.builder().otp(otp).build());
        return "/client_layout/resetPassword";
    }

    @PostMapping("/forgotPasswordOTP/resetPassword")
    String ResetPassword(@Valid @ModelAttribute("resetPassword") ResetPassword resetPassword,
                         BindingResult bindingResult, Model model) {
        log.info("Reset Password: {}", resetPassword.getOtp());
        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("globalErrors", bindingResult.getGlobalErrors());
            return "/client_layout/resetPassword";
        }
        return "redirect:/home";
    }
}
