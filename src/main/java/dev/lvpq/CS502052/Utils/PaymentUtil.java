package dev.lvpq.CS502052.Utils;

import com.stripe.param.checkout.SessionCreateParams;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class PaymentUtil {
    private static final String PAYMENT_ENDPOINT = "http://localhost:8081/payment";

    public static List<SessionCreateParams.LineItem> constructPayInformation(Long amount) {
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        lineItems.add(
                SessionCreateParams.LineItem.builder()
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(amount*100)
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
        return lineItems;
    }

    public static SessionCreateParams setUpEndpoint(List<SessionCreateParams.LineItem> lineItems) {
        return SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(PAYMENT_ENDPOINT + "/success")
                .setCancelUrl(PAYMENT_ENDPOINT + "/cancel")
                .addAllLineItem(lineItems)
                .build();
    }
}
