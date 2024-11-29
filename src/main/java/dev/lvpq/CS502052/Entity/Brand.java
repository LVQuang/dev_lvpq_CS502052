package dev.lvpq.CS502052.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false, unique = true)
    String name;
    LocalDate expirityContractDate;
    String logoURL;
    String brandWebLink;
    String contractURL;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    LocalDate registyDate = LocalDate.now();
    @OneToMany(mappedBy = "brand")
    @Builder.Default
    private Set<Product> products = new HashSet<>();
}
