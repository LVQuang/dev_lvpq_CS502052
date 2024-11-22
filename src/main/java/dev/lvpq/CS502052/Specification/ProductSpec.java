package dev.lvpq.CS502052.Specification;

import dev.lvpq.CS502052.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {

    public static Specification<Product> hasName(String name, boolean exactMatch) {
        if (exactMatch) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), name);
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), "%" + name + "%");
        }
    }

    public static Specification<Product> hasPrice(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Product> searchByKeyword(String keyword, Double minPrice, Double maxPrice, boolean exactMatch) {
        if (keyword == null || keyword.isEmpty()) {
            return hasPrice(minPrice, maxPrice);
        }

        return Specification.where(hasName(keyword, exactMatch))
                .and(hasPrice(minPrice, maxPrice));
    }
}
