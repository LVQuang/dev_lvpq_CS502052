package dev.lvpq.CS502052.Dto.Response;

import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceProductResponse {
    Invoice invoice;
    Product product;
}
