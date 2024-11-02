package dev.lvpq.CS502052.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Role {
    @Id
    String name;
    String description;
    String meta;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    LocalDate createAt = LocalDate.now();
    @Builder.Default
    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();
}
