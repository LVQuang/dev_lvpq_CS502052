package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Request.VoucherRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Dto.Response.VoucherDetailResponse;
import dev.lvpq.CS502052.Entity.Invoice;
import dev.lvpq.CS502052.Entity.Voucher;
import dev.lvpq.CS502052.Service.InvoiceService;
import dev.lvpq.CS502052.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher/")
public class VoucherAPI {
    @Autowired
    VoucherService voucherService;
    @Autowired
    InvoiceService invoiceService;
    @PostMapping("/create")
    public ApiResponse<VoucherDetailResponse> createVoucher(@RequestBody VoucherRequest voucherRequest){
        return ApiResponse.<VoucherDetailResponse>builder()
                .code(200)
                .message("Voucher created successfully")
                .result(voucherService.createVoucher(voucherRequest))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteVoucher(@PathVariable String id) {
        voucherService.deleteVoucher(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Voucher deleted successfully")
                .build();
    }
    @GetMapping("/list")
    public ApiResponse<List<VoucherDetailResponse>> listingVoucher() {
        List<VoucherDetailResponse> voucherList = voucherService.list();

        // Trả về kết quả
        return ApiResponse.<List<VoucherDetailResponse>>builder()
                .code(200)
                .result(voucherList)
                .build();
    }
    @PostMapping("/use")
    public ApiResponse<InvoiceResponse> useVoucher(@RequestParam String invoiceId, @RequestParam String code) {
        // Giả sử rằng `VoucherService` là service bạn dùng để xử lý voucher
        Voucher voucher = voucherService.findVoucherByCode(code);
        var updateVoucher = new InvoiceResponse();
        // Kiểm tra tính hợp lệ của voucher
        // Áp dụng voucher vào đơn hàng
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        if(voucher != null){
            double discountAmount = voucher.getDiscountValue();
            updateVoucher = invoiceService.updateTotalPrice(invoice, discountAmount);
        }

        return ApiResponse.<InvoiceResponse>builder()
                .code(200)
                .result(updateVoucher)
                .build();
    }

}
