package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Request.InvoiceRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Dto.Response.ProductWithQuantityResponse;
import dev.lvpq.CS502052.Entity.InvoiceDetail;
import dev.lvpq.CS502052.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@RequestMapping("/api/invoice")
public class InvoiceAPI {
    @Autowired
    private InvoiceService invoiceService;
    @PostMapping("/add-to-cart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable String productId) {
        try {
            InvoiceDetail invoiceDetail = invoiceService.addProductToInvoice(productId);
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        invoiceService.removeProductIfQuantityZero(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Product deleted successfully")
                .build();
    }
    @PutMapping("/update/{id}")
    public ApiResponse<InvoiceResponse> updateInvoice(
            @PathVariable String id,
            @RequestBody InvoiceRequest invoiceRequest) {
        return ApiResponse.<InvoiceResponse>builder()
                .code(200)
                .message("Invoice updated successfully")
                .result(invoiceService.updateInvoice(id, invoiceRequest))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<ProductWithQuantityResponse>> getProductWithQuantity() {
        return ApiResponse.<List<ProductWithQuantityResponse>>builder()
                .code(200)
                .message("Get product successfully")
                .result(invoiceService.getUserCartProducts()) // Gọi phương thức từ Service
                .build();
    }

}
