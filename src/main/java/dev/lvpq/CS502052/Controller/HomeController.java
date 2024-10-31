package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Entity.Brand;
import dev.lvpq.CS502052.Entity.CartItem;
import dev.lvpq.CS502052.Entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class HomeController {
    @GetMapping({"/home","/home.html", "/index.html"})
    public String showIndexPage(HttpServletRequest request, Model model) {
        List<Product> latest_products = getLatestProducts();
        List<Product> coming_products = getComingProducts();
        List<Product> exclusive_products = getExclusiveProducts();
        List<Product> related_products = getRelatedProducts();

        List<Brand> brands = getBrands();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("coming_products", coming_products);
        model.addAttribute("exclusive_products", exclusive_products);
        model.addAttribute("brands", brands);
        model.addAttribute("related_products", related_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/index";
    }
    private List<Product> getLatestProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("p1","addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new Product("p2","Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new Product("p3","Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new Product("p4","Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new Product("p5","Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new Product("p6","Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new Product("p7","Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new Product("p8","Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<Product> getComingProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("p1","addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new Product("p2","Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new Product("p3","Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new Product("p4","Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new Product("p5","Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new Product("p6","Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new Product("p7","Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new Product("p8","Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<Product> getRelatedProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("r1","Product 1", "$120.00", "$160.00", "/img/r1.jpg"),
                new Product("r2","Product 2", "$120.00", "$160.00", "/img/r2.jpg"),
                new Product("r3","Product 3", "$120.00", "$160.00", "/img/r3.jpg"),
                new Product("r4","Product 4", "$120.00", "$160.00", "/img/r4.jpg"),
                new Product("r5","Product 5", "$120.00", "$160.00", "/img/r5.jpg"),
                new Product("r6","Product 6", "$150.00", "$210.00", "/img/r6.jpg"),
                new Product("r7","Product 7", "$120.00", "$160.00", "/img/r7.jpg"),
                new Product("r8","Product 8", "$120.00", "$160.00", "/img/r8.jpg"),
                new Product("r9","Product 9", "$150.00", "$210.00", "/img/r9.jpg"),
                new Product("r10","Product 7", "$120.00", "$160.00", "/img/r10.jpg"),
                new Product("r11","Product 8", "$120.00", "$160.00", "/img/r11.jpg")
        );
    }
    private List<Product>getExclusiveProducts(){
        return Arrays.asList(
                new Product("e1","Exclusive Product 1", "$150.00", "$210.00", "/img/product/e-p1.png"),
                new Product("e2","Exclusive Product 2", "$200.00", "$250.00", "/img/product/e-p1.png"),
                new Product("e3","Exclusive Product 3", "$120.00", "$180.00", "/img/product/e-p1.png")
        );
    } 
    
    private List<Brand> getBrands(){
        return Arrays.asList(
                new Brand("Brand 1", "/img/brand/1.png", true ),
                new Brand("Brand 2", "/img/brand/2.png", true ),
                new Brand("Brand 3", "/img/brand/3.png", true ),
                new Brand("Brand 4", "/img/brand/4.png", true )
        );
    }
    @GetMapping({"/blog" ,"/blog.html"})
    String blog(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/blog";
    }
    @GetMapping({"/login","/login.html"})
    String login(HttpServletRequest request, Model model)
    {
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/login";
    }

    @GetMapping({"/category","/category.html"})
    String shop(HttpServletRequest request, Model model) {
        List<Product> related_products = getRelatedProducts();
        List<Product> latest_products = getLatestProducts();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("related_products", related_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/category";
    }

    @GetMapping({"/cart","/cart.html"})
    public String showCart(HttpServletRequest request, Model model) {
        List<CartItem> cartItems = getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/cart";
    }
    @GetMapping({"/single-product", "/single-product.html"})
    public String singleProduct(HttpServletRequest request, Model model) {
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
    private List<CartItem> getCartItems() {
        // Giả sử dữ liệu tĩnh, bạn có thể thay thế bằng truy vấn cơ sở dữ liệu
        return Arrays.asList(
                new CartItem("item1","/img/product/p1.jpg", "Minimalistic shop for multipurpose use", 360.00, 1),
                new CartItem("item2","/img/product/p2.jpg", "Minimalistic shop for multipurpose use", 390.00, 1)
//                new CartItem("/img/cart2.jpg", "Another product", "$200.00", 2, "$400.00")
        );
    }

}
