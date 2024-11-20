package dev.lvpq.CS502052.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductWithQuantityResponse {
    private ProductResponse product;
    private int quantity;

    public ProductWithQuantityResponse(ProductResponse product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
