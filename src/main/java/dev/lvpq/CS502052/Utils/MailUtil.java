package dev.lvpq.CS502052.Utils;

import dev.lvpq.CS502052.Dto.Request.SimpleMailRequest;
import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Entity.Product;
import dev.lvpq.CS502052.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class MailUtil {
    UserService userService;
    public SimpleMailRequest buildSimpleMailToCurrentUser(Invoice invoice) {
        var user = userService.getCurrentUser();
        var text = buildTextByInvoice(invoice);
        return SimpleMailRequest.builder()
                .receiver(user.getEmail())
                .subject("Payment Success")
                .text(text)
                .sender("phantipo2331@gmail.com")
                .html(true)
                .build();
    }

    public String buildTextByInvoice(Invoice invoice) {
        var content = new StringBuilder();
        content.append("<html><body>")
                .append("<h2>Invoice Details</h2>")
                .append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">")
                .append("<thead>")
                .append("<tr>")
                .append("<th>Product Name</th>")
                .append("<th>Quantity</th>")
                .append("<th>Price</th>")
                .append("<th>Total</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>");

        Map<Product, Integer> products = invoice.getProduct();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            double totalPrice = product.getPrice() * quantity;

            content.append("<tr>")
                    .append("<td>").append(product.getName()).append("</td>")
                    .append("<td>").append(quantity).append("</td>")
                    .append("<td>").append(product.getPrice()).append("</td>")
                    .append("<td>").append(totalPrice).append("</td>")
                    .append("</tr>");
        }

        content.append("</tbody>")
                .append("</table>");

        content.append("<p><strong>Total Cost: </strong>").append(invoice.getTotalPrice()).append("</p>");
        content.append("</body></html>");

        return content.toString();
    }
}
