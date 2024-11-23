package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.VoucherRequest;
import dev.lvpq.CS502052.Dto.Response.VoucherDetailResponse;
import dev.lvpq.CS502052.Entity.Voucher;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Mapper.VoucherMapper;
import dev.lvpq.CS502052.Repository.VoucherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    VoucherMapper voucherMapper;
    public Voucher findVoucherByCode(String code) {
        return voucherRepository.findByCode(code).orElse(null);
    }
    public VoucherDetailResponse createVoucher(VoucherRequest voucherRequest) {
        Voucher voucher = new Voucher();
        voucher.setCode(voucherRequest.getCode());
        voucher.setDiscountValue(voucherRequest.getDiscount());
        voucher.setStartDate(voucherRequest.getStartDate());
        voucher.setEndDate(voucherRequest.getEndDate());

        Voucher savedVoucher = voucherRepository.save(voucher);
        return voucherMapper.toDetailResponse(savedVoucher);
    }
    public void deleteVoucher(String id) {
        if (!voucherRepository.existsById(id)) {
            throw new AppException(AuthExceptionCode.VOUCHER_NOT_EXISTED);
        }
        voucherRepository.deleteById(id);
    }
    public List<VoucherDetailResponse> list(){
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
}
