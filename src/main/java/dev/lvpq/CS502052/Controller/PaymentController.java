package dev.lvpq.CS502052.Controller;

import com.stripe.exception.StripeException;
import dev.lvpq.CS502052.Service.PaymentService;
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

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/payment")
@Controller
public class PaymentController {
    PaymentService paymentService;

    @GetMapping()
    public String checkout(Model model) {
        model.addAttribute("amount", 2000L);
        return "Payment/payment";
    }

    @PostMapping()
    public String payment(Model model, @ModelAttribute("amount") Long amount)
            throws StripeException {
        var session = paymentService.payment(amount);
        model.addAttribute("sessionId", session.getId());
        return "redirect:" + session.getUrl();
    }

    @GetMapping("/success")
    public String successPage() {
        return "Payment/success";
    }

    @GetMapping("/cancel")
    public String cancelPage() {
        return "Payment/cancel";
    }
}

