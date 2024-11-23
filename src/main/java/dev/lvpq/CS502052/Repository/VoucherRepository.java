package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, String> {
    Optional<Voucher> findByCode(String code);
}
