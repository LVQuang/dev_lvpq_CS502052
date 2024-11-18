package dev.lvpq.CS502052.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OTP {
    @Id
    String code;
    @Builder.Default
    boolean valid = true;
    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
