package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.VoucherDetailResponse;
import dev.lvpq.CS502052.Entity.Voucher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    VoucherDetailResponse toDetailResponse(Voucher voucher);
}