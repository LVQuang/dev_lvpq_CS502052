package dev.lvpq.CS502052.Dto.Response;

import dev.lvpq.CS502052.Entity.Brand;
import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherDetailResponse {
    String id;
    String code;
    String image;
    double discount;
    LocalDate startDate;
    LocalDate endDate;
}
