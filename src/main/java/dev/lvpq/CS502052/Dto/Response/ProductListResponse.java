package dev.lvpq.CS502052.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListResponse {
    String id;
    String name;
    double price;
    int totalSold;
}
