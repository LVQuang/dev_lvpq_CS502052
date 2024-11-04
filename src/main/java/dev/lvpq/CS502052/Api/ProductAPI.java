package dev.lvpq.CS502052.Api;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Dto.Response.ProductListResponse;
import dev.lvpq.CS502052.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDetailResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductDetailResponse>builder()
                .code(201)
                .message("Product added successfully")
                .result(productService.addProduct(productRequest))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<ProductDetailResponse> updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductDetailResponse>builder()
                .code(200)
                .message("Product updated successfully")
                .result(productService.updateProduct(id, productRequest))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Product deleted successfully")
                .build();
    }
    @GetMapping("/latest")
    public List<ProductListResponse> getLatestProducts() {
        return productService.getLatestProducts();
    }

    @GetMapping("/related")
    public List<ProductListResponse> getRelatedProducts() {
        return productService.getRelatedProducts();
    }

    @GetMapping("/coming")
    public List<ProductListResponse> getComingProducts() {
        return productService.getComingProducts();
    }

    @GetMapping("/exclusive")
    public List<ProductListResponse> getExclusiveProducts() {
        return productService.getExclusiveProducts();
    }
}
