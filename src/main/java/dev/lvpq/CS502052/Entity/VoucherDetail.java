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
@IdClass(VoucherDetailKey.class)
public class VoucherDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    boolean active;
    int usageCount;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
}
