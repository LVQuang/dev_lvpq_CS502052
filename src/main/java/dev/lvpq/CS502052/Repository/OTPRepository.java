package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, String> {
    Optional<OTP> findByCode(String code);

    Optional<OTP> findByCodeAndValid(String code, boolean valid);
    List<OTP> findAllByValid(boolean valid);
}
