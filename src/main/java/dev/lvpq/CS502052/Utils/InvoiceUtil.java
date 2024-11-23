package dev.lvpq.CS502052.Utils;

import dev.lvpq.CS502052.Dto.Response.InvoiceProductResponse;
import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Enums.OrderStatus;
import dev.lvpq.CS502052.Repository.InvoiceRepository;
import dev.lvpq.CS502052.Repository.ProductRepository;
import dev.lvpq.CS502052.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class InvoiceUtil {
    UserService userService;
    InvoiceRepository invoiceRepository;
    ProductRepository productRepository;

    public InvoiceProductResponse buildInvoiceProductResponse(String productId) {
        var invoice = getInvoiceCurrentUser();

        if (invoice == null)
            invoice = Invoice.builder()
                    .buyer(userService.getCurrentUser())
                    .status(OrderStatus.PENDING)
                    .build();

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return InvoiceProductResponse.builder()
                .invoice(invoice)
                .product(product)
                .build();
    }

    public Invoice getInvoiceCurrentUser() {
        var currentUser = userService.getCurrentUser();
        return invoiceRepository.findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING)
                .orElse(null);
    }
}
