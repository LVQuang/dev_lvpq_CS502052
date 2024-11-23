package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.InvalidatedToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    boolean existsById(@NonNull String id);
}
