package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Request.InvoiceRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Dto.Response.ProductWithQuantityResponse;
import dev.lvpq.CS502052.Entity.InvoiceDetail;
import dev.lvpq.CS502052.Service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceAPI {
    @Autowired
    private InvoiceService invoiceService;
    @PostMapping("/add-to-cart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable String productId) {
        try {
            invoiceService.addProduct(productId);
            return ResponseEntity.ok("Add Product Success");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
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
    @GetMapping("/currentInvoice")
    public String idCurrent(){
        return invoiceService.getInvoiceByStatus();
    }

}
