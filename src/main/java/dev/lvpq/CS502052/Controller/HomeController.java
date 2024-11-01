package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Entity.BrandView;
import dev.lvpq.CS502052.Entity.CartItemView;
import dev.lvpq.CS502052.Entity.ProductView;
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
        List<ProductView> latest_productViews = getLatestProducts();
        List<ProductView> coming_productViews = getComingProducts();
        List<ProductView> exclusive_productViews = getExclusiveProducts();
        List<ProductView> related_productViews = getRelatedProducts();

        List<BrandView> brandViews = getBrands();
        model.addAttribute("latest_productViews", latest_productViews);
        model.addAttribute("coming_productViews", coming_productViews);
        model.addAttribute("exclusive_productViews", exclusive_productViews);
        model.addAttribute("brandViews", brandViews);
        model.addAttribute("related_productViews", related_productViews);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/index";
    }
    private List<ProductView> getLatestProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("p1","addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new ProductView("p2","Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new ProductView("p3","Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new ProductView("p4","Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new ProductView("p5","Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new ProductView("p6","Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new ProductView("p7","Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new ProductView("p8","Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<ProductView> getComingProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("p1","addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new ProductView("p2","Product 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new ProductView("p3","Product 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new ProductView("p4","Product 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new ProductView("p5","Product 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new ProductView("p6","Product 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new ProductView("p7","Product 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new ProductView("p8","Product 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<ProductView> getRelatedProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("r1","Product 1", "$120.00", "$160.00", "/img/r1.jpg"),
                new ProductView("r2","Product 2", "$120.00", "$160.00", "/img/r2.jpg"),
                new ProductView("r3","Product 3", "$120.00", "$160.00", "/img/r3.jpg"),
                new ProductView("r4","Product 4", "$120.00", "$160.00", "/img/r4.jpg"),
                new ProductView("r5","Product 5", "$120.00", "$160.00", "/img/r5.jpg"),
                new ProductView("r6","Product 6", "$150.00", "$210.00", "/img/r6.jpg"),
                new ProductView("r7","Product 7", "$120.00", "$160.00", "/img/r7.jpg"),
                new ProductView("r8","Product 8", "$120.00", "$160.00", "/img/r8.jpg"),
                new ProductView("r9","Product 9", "$150.00", "$210.00", "/img/r9.jpg"),
                new ProductView("r10","Product 7", "$120.00", "$160.00", "/img/r10.jpg"),
                new ProductView("r11","Product 8", "$120.00", "$160.00", "/img/r11.jpg")
        );
    }
    private List<ProductView>getExclusiveProducts(){
        return Arrays.asList(
                new ProductView("e1","Exclusive Product 1", "$150.00", "$210.00", "/img/product/e-p1.png"),
                new ProductView("e2","Exclusive Product 2", "$200.00", "$250.00", "/img/product/e-p1.png"),
                new ProductView("e3","Exclusive Product 3", "$120.00", "$180.00", "/img/product/e-p1.png")
        );
    } 
    
    private List<BrandView> getBrands(){
        return Arrays.asList(
                new BrandView("BrandView 1", "/img/brand/1.png", true ),
                new BrandView("BrandView 2", "/img/brand/2.png", true ),
                new BrandView("BrandView 3", "/img/brand/3.png", true ),
                new BrandView("BrandView 4", "/img/brand/4.png", true )
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
        List<ProductView> related_products = getRelatedProducts();
        List<ProductView> latest_products = getLatestProducts();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("related_products", related_products);
        model.addAttribute("requestURI", request.getRequestURI());
        return "/client_layout/category";
    }

    @GetMapping({"/cart","/cart.html"})
    public String showCart(HttpServletRequest request, Model model) {
        List<CartItemView> cartItemViews = getCartItems();
        model.addAttribute("cartItemViews", cartItemViews);
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
