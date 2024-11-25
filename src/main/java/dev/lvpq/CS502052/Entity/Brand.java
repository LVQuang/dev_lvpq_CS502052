package dev.lvpq.CS502052.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Brand {
    @Id
    String brand;
    LocalDate expirityContractDate;
    String logo;
    String meta;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    LocalDate createAt = LocalDate.now();
    @OneToMany(mappedBy = "brand")
    @Builder.Default
    private Set<Product> products = new HashSet<>();
}
