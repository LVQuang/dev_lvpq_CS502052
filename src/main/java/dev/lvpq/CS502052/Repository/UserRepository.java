package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository <User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);



}
