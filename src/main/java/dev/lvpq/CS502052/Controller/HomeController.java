package dev.lvpq.CS502052.Controller;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class HomeController {
    @Autowired
    private final ProductService productService;
    @GetMapping({"/home","/home.html", "/index.html"})
    public String showIndexPage(HttpServletRequest request, Model model) {
        List<ProductResponse> latest_products = productService.getLatestProducts();
        List<ProductResponse> coming_products = productService.getComingProducts();
        List<ProductResponse> exclusive_products = productService.getExclusiveProducts();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("coming_products", coming_products);
        model.addAttribute("exclusive_products", exclusive_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/index";
    }
    @GetMapping({"/blog" ,"/blog.html"})
    String blog(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/blog";
    }

    @GetMapping({"/category","/category.html"})
    String shop(HttpServletRequest request, Model model) {
        var latest_products = productService.getLatestProducts();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/category";
    }

    @GetMapping({"/cart","/cart.html"})
    public String showCart(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/cart";
    }
    @GetMapping({"/single-product/{id}", "/single-product.html"})
    public String singleProduct(HttpServletRequest request,
                                Model model, @PathVariable String id) {
        var product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/single-product";
    }
    @GetMapping({"/checkout", "/checkout.html"})
    public String checkout(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/checkout";
    }
    @GetMapping({"/confirmation", "/confirmation.html"})
    public String confirmation(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/confirmation";
    }
    @GetMapping({"/single-blog", "/single-blog.html"})
    public String singleBlog(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/single-blog";
    }
    @GetMapping({"/tracking", "/tracking.html"})
    public String tracking(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/tracking";
    }
    @GetMapping({"/elements", "/elements.html"})
    public String elements(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/elements";
    }
    @GetMapping({"/contact", "/contact.html"})
    public String contact(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/contact";
    }
}
