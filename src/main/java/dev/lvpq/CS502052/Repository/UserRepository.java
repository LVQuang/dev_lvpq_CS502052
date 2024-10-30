package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, String> {
    boolean existsByEmail(@Nonnull String email);
    Optional<User> findByEmail(String email);
}
