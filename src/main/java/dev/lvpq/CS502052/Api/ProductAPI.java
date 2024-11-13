package dev.lvpq.CS502052.Api;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
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


    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getProductById(@PathVariable String id) {
        return ApiResponse.<ProductDetailResponse>builder()
                .code(200)
                .message("Get product successfully")
                .result(productService.getProductById(id))
                .build();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDetailResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductDetailResponse>builder()
                .code(200)
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

    public List<ProductDetailResponse> getLatestProducts() {

        return productService.getLatestProducts();
    }

    @GetMapping("/related")

    public List<ProductDetailResponse> getRelatedProducts() {

        return productService.getRelatedProducts();
    }

    @GetMapping("/coming")

    public List<ProductDetailResponse> getComingProducts() {

        return productService.getComingProducts();
    }

    @GetMapping("/exclusive")

    public List<ProductDetailResponse> getExclusiveProducts() {

        return productService.getExclusiveProducts();
    }
    @GetMapping("/search")
    public ApiResponse<List<ProductDetailResponse>> findProducts(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice){
        List<ProductDetailResponse> products;

        if (query == null || query.isEmpty()) {
            products = productService.getAllProducts();
        } else {
            if(minPrice != null && maxPrice != null){
                products = productService.findProductsByNameAndPrice(query, minPrice, maxPrice);
            }
            else{
                products = productService.findProductsByName(query);
            }
        }
        String message = query == null || query.isEmpty()
                ? "Showing all products"
                : "Search results for query: " + query;
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message(message)
                .result(products)
                .build();
    }
    @GetMapping("/searchByPrice")
    public ApiResponse<List<ProductDetailResponse>> findProductsByPrice(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        if (minPrice == null) minPrice = 0.0; // Giá trị tối thiểu mặc định
        if (maxPrice == null) maxPrice = Double.MAX_VALUE; // Giá trị tối đa mặc định
        List<ProductDetailResponse> products = productService.findProductsByPrice(minPrice, maxPrice);
        String message = "Search results for products with price from " + minPrice + " to " + maxPrice;
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message(message)
                .result(products)
                .build();
    }
    @GetMapping("/sort/name_asc")
    public ApiResponse<List<ProductDetailResponse>> sortByNameAsc(){
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message("Sort Completely")
                .result(productService.sortProductByNameASC())
                .build();
    }
    @GetMapping("/sort/name_desc")
    public ApiResponse<List<ProductDetailResponse>> sortByNameDesc(){
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message("Sort Completely")
                .result(productService.sortProductByNameDESC())
                .build();
    }
    @GetMapping("/sort/price_asc")
    public ApiResponse<List<ProductDetailResponse>> sortByPriceAsc(){
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message("Sort Completely")
                .result(productService.sortProductByPriceASC())
                .build();
    }
    @GetMapping("/sort/price_desc")
    public ApiResponse<List<ProductDetailResponse>> sortByPriceDesc(){
        return ApiResponse.<List<ProductDetailResponse>>builder()
                .code(200)
                .message("Sort Completely")
                .result(productService.sortProductByPriceDESC())
                .build();
    }
}
