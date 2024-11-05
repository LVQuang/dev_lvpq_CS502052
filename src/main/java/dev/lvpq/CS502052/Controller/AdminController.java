package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Dto.Request.ProductRequest;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Service.ProductService;
import dev.lvpq.CS502052.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class AdminController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final ProductService productService;
    @GetMapping({"/admin"})
    public String admin(Model model, HttpServletRequest request){
        model.addAttribute("request", request);
        return "/admin_layout/index";
    }

    @GetMapping({"product.html", "product"})
    public String manage_product(Model model, HttpServletRequest request){
        List<ProductDetailResponse> all_products = productService.getAllProducts();
        model.addAttribute("all_products", all_products);
        model.addAttribute("request", request);

        return "/admin_layout/product";
    }

    @GetMapping({"account.html", "account"})
    public String getUsers(Model model, HttpServletRequest request) {
        // Lấy danh sách người dùng từ dịch vụ
        List<UserDetailResponse> users = userService.getAllCustomer(); // Sử dụng kiểu dữ liệu UserDetailResponse
        model.addAttribute("users", users); //
        model.addAttribute("request", request);
        return "/admin_layout/account";
    }

    @PostMapping
    public ResponseEntity<ProductDetailResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductDetailResponse response = productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        ProductDetailResponse response = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> getAllProducts() {
        List<ProductDetailResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
