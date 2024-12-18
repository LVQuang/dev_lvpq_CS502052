package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
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
    String image;
    int quantity;
    ProductStatus status;
    ProductType type;
    int totalSold;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Size> sizes = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Review> reviews = new HashSet<>();
    @Builder.Default
    @ManyToMany(mappedBy = "products")
    Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<InvoiceDetail> invoiceDetails = new HashSet<>();

    public void addSize(Size size) {
        sizes.add(size);
        size.getProducts().add(this);
    }

    public void removeSize(Size size) {
        sizes.remove(size);
        size.getProducts().add(this);
    }
    public void setAvailable(){
        this.status = ProductStatus.Available;
    }
    public void setOutOfStock(){
        this.status = ProductStatus.OutOfStock;
    }
    public void setDiscontinued(){
        this.status = ProductStatus.Discontinued;
    }
    public void setPreOrder(){
        this.status = ProductStatus.PreOrder;
    }
}
