package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Invoice {
//    public Invoice(User user){
//        buyer = user;
//    }
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

    public Map<Product, Integer> getProduct() {
        Map<Product, Integer> result = new HashMap<>();
        for (InvoiceDetail detail : this.getInvoiceDetails()) {
            result.put(detail.getProduct(), detail.getQuantity());
        }
        return result;
    }

}
