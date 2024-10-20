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
        return "index";
    }
    private List<Product> getLatestProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new Product("Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new Product("Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new Product("Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new Product("Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new Product("Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new Product("Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new Product("Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<Product> getComingProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new Product("Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new Product("Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new Product("Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new Product("Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new Product("Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new Product("Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new Product("Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<Product> getRelatedProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new Product("Product 1", "$120.00", "$160.00", "/img/r1.jpg"),
                new Product("Product 2", "$120.00", "$160.00", "/img/r2.jpg"),
                new Product("Product 3", "$120.00", "$160.00", "/img/r3.jpg"),
                new Product("Product 4", "$120.00", "$160.00", "/img/r4.jpg"),
                new Product("Product 5", "$120.00", "$160.00", "/img/r5.jpg"),
                new Product("Product 6", "$150.00", "$210.00", "/img/r6.jpg"),
                new Product("Product 7", "$120.00", "$160.00", "/img/r7.jpg"),
                new Product("Product 8", "$120.00", "$160.00", "/img/r8.jpg"),
                new Product("Product 9", "$150.00", "$210.00", "/img/r9.jpg"),
                new Product("Product 7", "$120.00", "$160.00", "/img/r10.jpg"),
                new Product("Product 8", "$120.00", "$160.00", "/img/r11.jpg")
        );
    }
    private List<Product>getExclusiveProducts(){
        return Arrays.asList(
                new Product("Exclusive Product 1", "$150.00", "$210.00", "/img/product/e-p1.png"),
                new Product("Exclusive Product 2", "$200.00", "$250.00", "/img/product/e-p1.png"),
                new Product("Exclusive Product 3", "$120.00", "$180.00", "/img/product/e-p1.png")
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
        return "blog";
    }
    @GetMapping({"/login","/login.html"})
    String login(HttpServletRequest request, Model model)
    {
        model.addAttribute("requestURI", request.getRequestURI());
        return "login";
    }

    @GetMapping({"/category","/category.html"})
    String shop(HttpServletRequest request, Model model) {
        List<Product> related_products = getRelatedProducts();
        model.addAttribute("related_products", related_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "category";
    }

    @GetMapping({"/cart","/cart.html"})
    public String showCart(HttpServletRequest request, Model model) {
        List<CartItem> cartItems = getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("requestURI", request.getRequestURI());
        return "cart";
    }

    private List<CartItem> getCartItems() {
        // Giả sử dữ liệu tĩnh, bạn có thể thay thế bằng truy vấn cơ sở dữ liệu
        return Arrays.asList(
                new CartItem("/img/cart.jpg", "Minimalistic shop for multipurpose use", 360.00, 1),
                new CartItem("/img2/cart.jpg", "Minimalistic shop for multipurpose use", 390.00, 1)
//                new CartItem("/img/cart2.jpg", "Another product", "$200.00", 2, "$400.00")
        );
    }
}
