package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceResponse toResponse(Invoice invoice);
}
