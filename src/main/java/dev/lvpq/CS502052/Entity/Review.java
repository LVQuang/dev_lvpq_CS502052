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
@IdClass(ReviewKey.class)
public class Review {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    String content;
    String meta;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    @Builder.Default
    boolean hide = false;
}
