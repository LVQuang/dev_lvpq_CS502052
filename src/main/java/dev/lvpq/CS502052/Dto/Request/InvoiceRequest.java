package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceRequest {
//    String userId;
//    String productId;
    int quantity;
    OrderStatus status; // Hoặc dùng enum OrderStatus
    String note;
    Double shippingFee;
    Double totalPrice;
    String meta;
    boolean hide;
    List<InvoiceDetailRequest> details;
}
