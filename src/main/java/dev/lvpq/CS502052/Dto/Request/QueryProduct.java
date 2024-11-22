package dev.lvpq.CS502052.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QueryProduct {
    String keyword;
    Double minPrice;
    Double maxPrice;
    boolean isExactMatch;
    int page;
    Integer size;
    String sortBy = "name";
    String sortOrder = "asc";
}
