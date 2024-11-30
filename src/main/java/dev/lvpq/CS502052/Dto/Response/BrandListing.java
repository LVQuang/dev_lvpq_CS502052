package dev.lvpq.CS502052.Dto.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandListing {
    String name;
    String logoURL;
    String contractURL;
    String brandWebLink;
    String expirationDate;
    String registrationDate;    
}
