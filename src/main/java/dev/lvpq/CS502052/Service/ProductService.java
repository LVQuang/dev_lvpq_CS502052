package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Dto.Response.ProductListResponse;
import dev.lvpq.CS502052.Entity.Product;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    public ProductDetailResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setType(productRequest.getType());
        product.setImage(productRequest.getImage());
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
    private List<ProductListResponse> getProductsByType(ProductType type) {
        return productRepository.findAll().stream()
                .filter(product -> product.getType() == type)
                .map(productMapper::toListResponse)
                .collect(Collectors.toList());
    }

    // Các phương thức lấy sản phẩm theo từng loại
    public List<ProductListResponse> getLatestProducts() {
        return getProductsByType(ProductType.LATEST);
    }

    public List<ProductListResponse> getRelatedProducts() {
        return getProductsByType(ProductType.RELATED);
    }

    public List<ProductListResponse> getComingProducts() {
        return getProductsByType(ProductType.COMING);
    }

    public List<ProductListResponse> getExclusiveProducts() {
        return getProductsByType(ProductType.EXCLUSIVE);
    }
}
