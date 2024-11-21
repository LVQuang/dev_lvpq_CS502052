package dev.lvpq.CS502052.Specification;

import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpec {
    public static Specification<Invoice> findInvoiceByStatus(OrderStatus status) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status));
    }
}
