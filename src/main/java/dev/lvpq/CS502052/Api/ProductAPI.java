package dev.lvpq.CS502052.Api;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Request.QueryProduct;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;


    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Get product successfully")
                .result(productService.getProductById(id))
                .build();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Product added successfully")
                .result(productService.addProduct(productRequest))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<ProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductResponse>builder()
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

    public List<ProductResponse> getLatestProducts() {

        return productService.getLatestProducts();
    }

    @GetMapping("/related")

    public List<ProductResponse> getRelatedProducts() {

        return productService.getRelatedProducts();
    }

    @GetMapping("/coming")

    public List<ProductResponse> getComingProducts() {

        return productService.getComingProducts();
    }

    @GetMapping("/exclusive")

    public List<ProductResponse> getExclusiveProducts() {

        return productService.getExclusiveProducts();
    }
    @PostMapping("/query")
    public ApiResponse<List<ProductResponse>> findProducts(@RequestBody QueryProduct query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<ProductResponse> productPage = productService.queryProduct(query, pageable);

        // Trả về kết quả
        return ApiResponse.<List<ProductResponse>>builder()
                .code(200)
                .result(productPage)
                .build();
    }
    @GetMapping("/quantity")
    public int quantityOfProduct(){
        return productService.getAllProducts().size();
    }
}

