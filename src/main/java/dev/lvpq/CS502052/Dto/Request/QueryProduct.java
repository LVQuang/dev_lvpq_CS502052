package dev.lvpq.CS502052.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QueryProduct {
//    String keyword;
//    String sort;
//    int page;
//    @Builder.Default
//    int size = 2;
    String keyword;
    Double minPrice;
    Double maxPrice;
    String sort;
    int page;
//    @Builder.Default

    int size;
}
