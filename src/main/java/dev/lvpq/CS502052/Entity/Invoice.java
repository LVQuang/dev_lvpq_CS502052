package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.OrderStatus;
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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String note;
    double price;
    double totalPrice;
    OrderStatus status;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User buyer;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceDetail> invoiceDetails = new HashSet<>();
}
