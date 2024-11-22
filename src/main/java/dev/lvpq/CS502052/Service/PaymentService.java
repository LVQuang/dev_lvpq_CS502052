package dev.lvpq.CS502052.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import dev.lvpq.CS502052.Utils.PaymentUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    UserService userService;

    public Session payment(Long amount) throws StripeException {
        var lineItems = PaymentUtil.constructPayInformation(amount);
        var params = PaymentUtil.setUpEndpoint(lineItems);
        var user = userService.getCurrentUser();
        if (user != null) {
            
        }
        return Session.create(params);
    }
}
