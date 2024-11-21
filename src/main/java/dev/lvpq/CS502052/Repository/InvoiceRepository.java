package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>, JpaSpecificationExecutor<Invoice> {
    Optional<Invoice> findByBuyerIdAndStatus(String buyerId, OrderStatus status);
}
