package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String description;
    String name;
    double price;
    ProductStatus status;
    int totalSold;
    String brand;
    String category;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Size> sizes = new HashSet<>();

    public void addSize(Size size) {
        sizes.add(size);
        size.getProducts().add(this);
    }

    public void removeRole(Size size) {
        sizes.remove(size);
        size.getProducts().add(this);
    }
}
