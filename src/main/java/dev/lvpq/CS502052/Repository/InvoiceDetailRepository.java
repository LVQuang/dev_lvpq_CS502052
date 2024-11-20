package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository  extends JpaRepository<InvoiceDetail, String> {
    boolean findByProductId(String productId);
    void deleteByProductId(String productId);
}