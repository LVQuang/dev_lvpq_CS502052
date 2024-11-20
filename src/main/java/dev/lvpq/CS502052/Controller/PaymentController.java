package dev.lvpq.CS502052.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import dev.lvpq.CS502052.Config.StripeConfig;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/payment")
@Controller
public class PaymentController {

    @GetMapping()
    public String checkout(Model model) {
        model.addAttribute("amount", 2000L);
        return "Payment/payment";
    }

    @PostMapping()
    public String payment(Model model, @ModelAttribute("amount") Long amount ) {
        try {
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
            lineItems.add(
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(amount)
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("Shoe")
                                                            .build()
                                            )
                                            .build()
                            )
                            .setQuantity(1L)
                            .build()
            );


            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8081/payment/success")
                    .setCancelUrl("http://localhost:8081/payment/cancel")
                    .addAllLineItem(lineItems)
                    .build();

            Session session = Session.create(params);

            model.addAttribute("sessionId", session.getId());
            return "redirect:" + session.getUrl();
        } catch (StripeException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
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

