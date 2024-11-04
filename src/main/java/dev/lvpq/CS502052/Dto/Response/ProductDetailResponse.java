package dev.lvpq.CS502052.Dto.Response;


import dev.lvpq.CS502052.Entity.Brand;
import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    String id;
    String name;
    String description;
    double price;
    ProductType type;
    ProductStatus status;
    int totalSold;
    Brand brand;
}
