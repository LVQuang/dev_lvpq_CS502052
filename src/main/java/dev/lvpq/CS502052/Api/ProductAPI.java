package dev.lvpq.CS502052.Api;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Request.QueryProduct;
import dev.lvpq.CS502052.Dto.Request.QueryUser;
import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
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
//    @GetMapping("/search")
//    public ApiResponse<List<ProductResponse>> findProducts(
//            @RequestParam(value = "query", required = false) String query,
//            @RequestParam(value = "minPrice", required = false) Double minPrice,
//            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
//            @RequestParam(value = "sort", required = false) String sort) {
//        List<ProductResponse> products;
//
//   if(query != null && minPrice != null && maxPrice != null){
//       products = productService.findProductsByNameAndPrice(query, minPrice, maxPrice);
//   }
//   else if(query == null && minPrice != null && maxPrice != null){
//       products = productService.findProductsByPrice(minPrice, maxPrice);
//   }
//   else if(query != null && minPrice == null && maxPrice == null){
//       products = productService.findProductsByName(query);
//   }
//   else{
//       products = productService.getAllProducts();
//   }
//        if ("name_asc".equalsIgnoreCase(sort) || sort.isEmpty()) {
//            products.sort(Comparator.comparing(ProductResponse::getName));
//        } else if ("name_desc".equalsIgnoreCase(sort)) {
//            products.sort(Comparator.comparing(ProductResponse::getName).reversed());
//        } else if ("price_asc".equalsIgnoreCase(sort)) {
//            products.sort(Comparator.comparing(ProductResponse::getPrice));
//        } else if ("price_desc".equalsIgnoreCase(sort)) {
//            products.sort(Comparator.comparing(ProductResponse::getPrice).reversed());
//        }
//        String message = query == null || query.isEmpty()
//                ? "Showing all products"
//                : "Search results for query: " + query;
//        return ApiResponse.<List<ProductResponse>>builder()
//            .code(200)
//            .message(message)
//            .result(products)
//                .build();
//    }
    @GetMapping("/search")
    public ApiResponse<Page<ProductResponse>> findProducts(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "minPrice", required = false) Double minPrice,
        @RequestParam(value = "maxPrice", required = false) Double maxPrice,
        @RequestParam(value = "sort", required = false) String sort,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
    QueryProduct queryProduct = new QueryProduct(query, minPrice, maxPrice, sort, page, size);
    Page<ProductResponse> productPage = productService.queryProduct(queryProduct);

    String message = (query == null || query.isEmpty())
            ? "Showing all products"
            : "Search results for query: " + query;

    return ApiResponse.<Page<ProductResponse>>builder()
            .code(200)
            .message(message)
            .result(productPage)
            .build();
}


}

