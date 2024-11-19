package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Optional<Invoice> findByBuyerIdAndStatus(String buyerId, OrderStatus status);
    List<Invoice> findByBuyerId(String buyerId); // Tìm tất cả hóa đơn của một người dùng theo buyerId
}
