package dev.lvpq.CS502052.Controller;
import dev.lvpq.CS502052.Service.ProductService;
import dev.lvpq.CS502052.Service.ViewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class HomeController {
    ProductService productService;
    ViewService viewService;

    @GetMapping()
    public String showIndexPage(HttpServletRequest request, Model model) {
        viewService.buildIndexPage(model, request.getRequestURI());
        return "/Client/index";
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
}
