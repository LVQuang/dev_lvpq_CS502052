package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Dto.Response.ProductListResponse;
import dev.lvpq.CS502052.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "image", source = "product.image")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "type", source = "product.type")
    @Mapping(target = "status", source = "product.status")
    ProductDetailResponse toDetailResponse(Product product);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "totalSold", source = "product.totalSold")

    ProductListResponse toListResponse(Product product);
}
