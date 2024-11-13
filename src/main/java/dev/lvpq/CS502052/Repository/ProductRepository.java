package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.Product;
import dev.lvpq.CS502052.Enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByNameContaining(String name);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByStatus(ProductStatus status);
}

