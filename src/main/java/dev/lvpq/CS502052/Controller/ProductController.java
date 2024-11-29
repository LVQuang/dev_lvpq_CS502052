package dev.lvpq.CS502052.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("admin/product/products")
@Controller
public class ProductController {
    ProductService productService;

    @GetMapping()
    public String manageProduct(Model model, HttpServletRequest request,@RequestParam(required = false) String query) {
        List<ProductResponse> allProducts = productService.findProductsByName(query);
        model.addAttribute("all_products", allProducts);
        model.addAttribute("request", request); 
        return "/Admin/Product";
    }
}
