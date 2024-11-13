package dev.lvpq.CS502052.Service;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Entity.Product;
import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.ErrorCode;
import dev.lvpq.CS502052.Mapper.ProductMapper;
import dev.lvpq.CS502052.Repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;


    public ProductDetailResponse getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDetailResponse)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    public ProductDetailResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setType(productRequest.getType() != null ? productRequest.getType() : ProductType.LATEST);

        product.setImage(productRequest.getImage());
        product.setStatus(ProductStatus.Available);

        // Thêm các thuộc tính khác nếu cần
        Product savedProduct = productRepository.save(product);
        return productMapper.toDetailResponse(savedProduct);
    }

    public ProductDetailResponse updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImage(productRequest.getImage());
        product.setType(productRequest.getType());
        product.setStatus(productRequest.getStatus());
        // Cập nhật các thuộc tính khác nếu cần
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDetailResponse(updatedProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        productRepository.deleteById(id);
    }
    // Trả về danh sách ProductDetailResponse theo ProductType
    public List<ProductDetailResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    private List<ProductDetailResponse> getProductsByType(ProductType type) {
        return productRepository.findAll().stream()
                .filter(product -> product.getType() == type)
                .map(productMapper::toDetailResponse)

                .collect(Collectors.toList());
    }

    // Các phương thức lấy sản phẩm theo từng loại
    public List<ProductDetailResponse> getLatestProducts() {
        return getProductsByType(ProductType.LATEST);
    }

    public List<ProductDetailResponse> getRelatedProducts() {
        return getProductsByType(ProductType.RELATED);
    }

    public List<ProductDetailResponse> getComingProducts() {
        return getProductsByType(ProductType.COMING);
    }

    public List<ProductDetailResponse> getExclusiveProducts() {

        return getProductsByType(ProductType.EXCLUSIVE);
    }

    public List<ProductDetailResponse> findProductsByName(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }

    public List<ProductDetailResponse> findProductsByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    public List<ProductDetailResponse> findProductsByNameAndPrice(String query, Double minPrice, Double maxPrice) {
        if (query == null  || minPrice == null && maxPrice == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    
    public List<ProductDetailResponse> sortProductByNameASC() {
        return productRepository.findAll().stream()
                .map(productMapper::toDetailResponse)
                .sorted(Comparator.comparing(ProductDetailResponse::getName)) // Sắp xếp theo tên từ A-Z
                .collect(Collectors.toList());
    }
    public List<ProductDetailResponse> sortProductByNameDESC() {
        return productRepository.findAll().stream()
                .map(productMapper::toDetailResponse)
                .sorted(Comparator.comparing(ProductDetailResponse::getName).reversed())
                .collect(Collectors.toList());
    }
    public List<ProductDetailResponse> sortProductByPriceASC() {
        return productRepository.findAll().stream()
                .map(productMapper::toDetailResponse)
                .sorted(Comparator.comparing(ProductDetailResponse::getPrice)) // Sắp xếp theo giá từ rẻ đến đắt
                .collect(Collectors.toList());
    }
    public List<ProductDetailResponse> sortProductByPriceDESC() {
        return productRepository.findAll().stream()
                .map(productMapper::toDetailResponse)
                .sorted(Comparator.comparing(ProductDetailResponse::getPrice).reversed())
                .collect(Collectors.toList());
    }

}
