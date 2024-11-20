package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Dto.Response.ProductListResponse;
import dev.lvpq.CS502052.Entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toDetailResponse(Product product);

    ProductListResponse toListResponse(Product product);
}
