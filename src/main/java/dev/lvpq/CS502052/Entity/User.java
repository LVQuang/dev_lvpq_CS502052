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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false, unique = true)
    String email;
    String password;
    String username;
    String phone;
    String address;
    LocalDate dateOfBirth;
    @Builder.Default
    LocalDate createdAt = LocalDate.now();
    String meta;
    @Builder.Default
    boolean hide = false;
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Role> roles = new HashSet<>();
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<ActivityLog> activityLogs = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public void addActivityLog(ActivityLog activityLog) {
        activityLogs.add(activityLog);
        activityLog.getUsers().add(this);
    }

    public void removeActivityLog(ActivityLog activityLog) {
        activityLogs.remove(activityLog);
        activityLog.getUsers().remove(this);
    }
}
