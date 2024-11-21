package dev.lvpq.CS502052.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@IdClass(InvoiceDetailKey.class)
public class InvoiceDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Builder.Default
    int quantity = 1;
    String meta;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();

    public void increaseQuantity() {
        quantity += 1;
    }
}
