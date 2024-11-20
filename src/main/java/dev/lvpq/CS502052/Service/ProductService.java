package dev.lvpq.CS502052.Service;
import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Request.QueryProduct;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Entity.Product;
import dev.lvpq.CS502052.Enums.ProductStatus;
import dev.lvpq.CS502052.Enums.ProductType;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Exception.Error.ErrorCode;
import dev.lvpq.CS502052.Mapper.ProductMapper;
import dev.lvpq.CS502052.Repository.ProductRepository;
import dev.lvpq.CS502052.Specification.ProductSpec;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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



    public ProductResponse getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDetailResponse)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    public ProductResponse addProduct(ProductRequest productRequest) {
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

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(AuthExceptionCode.PRODUCT_NOT_EXISTED));
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
            throw new AppException(AuthExceptionCode.PRODUCT_NOT_EXISTED);
        }
        productRepository.deleteById(id);
    }
    // Trả về danh sách ProductResponse theo ProductType
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    private List<ProductResponse> getProductsByType(ProductType type) {
        return productRepository.findAll().stream()
                .filter(product -> product.getType() == type)
                .map(productMapper::toDetailResponse)

                .collect(Collectors.toList());
    }

    // Các phương thức lấy sản phẩm theo từng loại
    public List<ProductResponse> getLatestProducts() {
        return getProductsByType(ProductType.LATEST);
    }

    public List<ProductResponse> getRelatedProducts() {
        return getProductsByType(ProductType.RELATED);
    }

    public List<ProductResponse> getComingProducts() {
        return getProductsByType(ProductType.COMING);
    }

    public List<ProductResponse> getExclusiveProducts() {

        return getProductsByType(ProductType.EXCLUSIVE);
    }

    public List<ProductResponse> findProductsByName(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findProductsByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    public List<ProductResponse> findProductsByNameAndPrice(String query, Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .map(productMapper::toDetailResponse)
                .collect(Collectors.toList());
    }
    public Page<ProductResponse> queryProduct(QueryProduct query) {
        Specification<Product> spec = ProductSpec.searchByKeyword(query.getKeyword(), false);

        // Xây dựng đối tượng Sort dựa trên query
        Sort sort = switch (query.getSort().toLowerCase()) {
            case "name_asc" -> Sort.by(Sort.Direction.ASC, "name");
            case "name_desc" -> Sort.by(Sort.Direction.DESC, "name");
            case "price_asc" -> Sort.by(Sort.Direction.ASC, "price");
            case "price_desc" -> Sort.by(Sort.Direction.DESC, "price");
            default -> Sort.unsorted();
        };

        Pageable page = PageRequest.of(query.getPage(), query.getSize(), sort);

        var productsPage = productRepository.findAll(spec, page);
        return productsPage.map(productMapper::toDetailResponse); // Chuyển đổi sang ProductResponse
    }

}
