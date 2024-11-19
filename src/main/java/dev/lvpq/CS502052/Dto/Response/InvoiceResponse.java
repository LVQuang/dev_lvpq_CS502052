package dev.lvpq.CS502052.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {
    private String id;
    private String status;
    private String note;
    private double totalPrice;
    private double shippingFee;
    private String meta;
    private boolean hide;
    private List<InvoiceDetailResponse> details;
    private LocalDateTime updatedAt;
}
