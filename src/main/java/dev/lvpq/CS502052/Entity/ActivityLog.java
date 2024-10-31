package dev.lvpq.CS502052.Entity;

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
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String activity;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    String meta;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    @ManyToMany(mappedBy = "activityLogs")
    Set<User> users = new HashSet<>();
}
