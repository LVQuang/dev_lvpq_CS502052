package dev.lvpq.CS502052.Entity;

import dev.lvpq.CS502052.Enums.Activity;
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
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    Activity activity;
    @Builder.Default
    String keyword = null;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    String meta;
    @Builder.Default
    boolean hide = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}