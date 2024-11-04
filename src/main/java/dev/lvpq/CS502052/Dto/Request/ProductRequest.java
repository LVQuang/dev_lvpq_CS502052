package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Enums.ProductType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequest {
    String name;
    double price;
    String image;
    ProductType type;
    String description;
}
