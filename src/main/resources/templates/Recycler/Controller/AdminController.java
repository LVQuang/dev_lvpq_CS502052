package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Service.ProductService;
import dev.lvpq.CS502052.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "/Admin/index";
    }

    @GetMapping({"product.html", "product"})
    public String manageProduct(Model model, HttpServletRequest request,@RequestParam(required = false) String query) {
        List<ProductResponse> allProducts = productService.findProductsByName(query);
        model.addAttribute("all_products", allProducts);
        model.addAttribute("request", request);
        return "/Admin/product";
    }

    @GetMapping({"voucher.html", "voucher"})
    public String manageUsers(Model model, HttpServletRequest request) {
        List<UserDetailResponse> users = userService.getAllCustomer();
        model.addAttribute("users", users);
        model.addAttribute("request", request);  // Truyền HttpServletRequest vào model
        return "/Admin/voucher";
    }

}


