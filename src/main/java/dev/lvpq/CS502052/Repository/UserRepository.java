package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String> {}
