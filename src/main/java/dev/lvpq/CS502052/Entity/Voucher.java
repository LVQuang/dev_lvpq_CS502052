package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.DiscountType;
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
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    String description;
    DiscountType discountType;
    double discountValue;
    LocalDate startDate;
    LocalDate endDate;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VoucherDetail> voucherDetails = new HashSet<>();
}
