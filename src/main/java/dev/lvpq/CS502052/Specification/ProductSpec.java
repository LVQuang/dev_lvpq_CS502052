package dev.lvpq.CS502052.Specification;

import dev.lvpq.CS502052.Entity.*;
import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProductSpec {
    String name;
    double price;
    ProductStatus status;
    ProductType type;

    public static Specification<Product> hasName(String name, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"),"%" + name + "%"));
    }

    public static Specification<Product> hasPrice(Double price, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("price"), price));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("price"),"%" + price + "%"));
    }

    public static Specification<Product> hasStatus(ProductStatus status, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), status));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("status"),"%" + status + "%"));
    }

    public static Specification<Product> hasType(ProductType type, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("type"), type));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("type"),"%" + type + "%"));
    }

    public static Specification<Product> searchByKeyword(String keyword, boolean exactMatch) {
        if (keyword == null || keyword.isEmpty()) return null;

        return Specification.where(hasName(keyword, exactMatch))
                .or(hasName(keyword, exactMatch))
                .or(hasPrice(Double.parseDouble(keyword), exactMatch))
                .or(hasStatus(ProductStatus.valueOf(keyword), exactMatch))
                .or(hasType(ProductType.valueOf(keyword), exactMatch));
    }
}
