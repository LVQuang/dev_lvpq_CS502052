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
        return "index";
    }
    private List<ProductView> getLatestProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new ProductView("ProductView 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new ProductView("ProductView 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new ProductView("ProductView 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new ProductView("ProductView 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new ProductView("ProductView 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new ProductView("ProductView 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new ProductView("ProductView 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<ProductView> getComingProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("addidas New Hammer sole for Sports person", "$150.00", "$210.00", "/img/product/p1.jpg"),
                new ProductView("ProductView 2", "$120.00", "$160.00", "/img/product/p2.jpg"),
                new ProductView("ProductView 3", "$180.00", "$250.00", "/img/product/p3.jpg"),
                new ProductView("ProductView 4", "$180.00", "$250.00", "/img/product/p4.jpg"),
                new ProductView("ProductView 5", "$180.00", "$250.00", "/img/product/p5.jpg"),
                new ProductView("ProductView 6", "$180.00", "$250.00", "/img/product/p6.jpg"),
                new ProductView("ProductView 7", "$180.00", "$250.00", "/img/product/p7.jpg"),
                new ProductView("ProductView 8", "$180.00", "$250.00", "/img/product/p8.jpg")
        );
    }
    private List<ProductView> getRelatedProducts() {
        // Giả sử đây là dữ liệu tĩnh, bạn có thể thay bằng việc truy vấn từ cơ sở dữ liệu
        return Arrays.asList(
                new ProductView("ProductView 1", "$120.00", "$160.00", "/img/r1.jpg"),
                new ProductView("ProductView 2", "$120.00", "$160.00", "/img/r2.jpg"),
                new ProductView("ProductView 3", "$120.00", "$160.00", "/img/r3.jpg"),
                new ProductView("ProductView 4", "$120.00", "$160.00", "/img/r4.jpg"),
                new ProductView("ProductView 5", "$120.00", "$160.00", "/img/r5.jpg"),
                new ProductView("ProductView 6", "$150.00", "$210.00", "/img/r6.jpg"),
                new ProductView("ProductView 7", "$120.00", "$160.00", "/img/r7.jpg"),
                new ProductView("ProductView 8", "$120.00", "$160.00", "/img/r8.jpg"),
                new ProductView("ProductView 9", "$150.00", "$210.00", "/img/r9.jpg"),
                new ProductView("ProductView 7", "$120.00", "$160.00", "/img/r10.jpg"),
                new ProductView("ProductView 8", "$120.00", "$160.00", "/img/r11.jpg")
        );
    }
    private List<ProductView>getExclusiveProducts(){
        return Arrays.asList(
                new ProductView("Exclusive ProductView 1", "$150.00", "$210.00", "/img/product/e-p1.png"),
                new ProductView("Exclusive ProductView 2", "$200.00", "$250.00", "/img/product/e-p1.png"),
                new ProductView("Exclusive ProductView 3", "$120.00", "$180.00", "/img/product/e-p1.png")
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
        List<ProductView> related_productViews = getRelatedProducts();
        model.addAttribute("related_productViews", related_productViews);
        model.addAttribute("requestURI", request.getRequestURI());
        return "category";
    }

    @GetMapping({"/cart","/cart.html"})
    public String showCart(HttpServletRequest request, Model model) {
        List<CartItemView> cartItemViews = getCartItems();
        model.addAttribute("cartItemViews", cartItemViews);
        model.addAttribute("requestURI", request.getRequestURI());
        return "cart";
    }

    private List<CartItemView> getCartItems() {
        // Giả sử dữ liệu tĩnh, bạn có thể thay thế bằng truy vấn cơ sở dữ liệu
        return Arrays.asList(
                new CartItemView("/img/cart.jpg", "Minimalistic shop for multipurpose use", 360.00, 1),
                new CartItemView("/img2/cart.jpg", "Minimalistic shop for multipurpose use", 390.00, 1)
//                new CartItemView("/img/cart2.jpg", "Another product", "$200.00", 2, "$400.00")
        );
    }
}
