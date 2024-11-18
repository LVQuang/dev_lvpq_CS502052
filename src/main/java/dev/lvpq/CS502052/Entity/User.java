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
    @ManyToMany
    Set<Role> roles = new HashSet<>();
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<ActivityLog> activityLogs = new HashSet<>();
    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "favourite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    Set<Product> products = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Review> reviews = new HashSet<>();
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Invoice> invoice = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<VoucherDetail> voucherDetails = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OTP> otps = new HashSet<>();
    public void addRole(Role role) {
        roles.add(role);
    }
}
