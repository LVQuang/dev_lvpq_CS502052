package dev.lvpq.CS502052.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
    public class InvoiceDetailRequest {
        private String productId;
        private int quantity;
}
