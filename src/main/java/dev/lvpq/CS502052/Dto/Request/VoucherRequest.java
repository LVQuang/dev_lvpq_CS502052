package dev.lvpq.CS502052.Dto.Request;

import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class VoucherRequest {
    String code;
    double discount;
    String image;
    LocalDate startDate;
    LocalDate endDate;
}
