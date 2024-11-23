package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Response.ProductResponse;
import dev.lvpq.CS502052.Entity.ActivityLog;
import dev.lvpq.CS502052.Enums.Activity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ViewService {
    ProductService productService;
    ActivityLogService activityLogService;

    public void buildIndexPage(Model model, String uri) {
        List<ProductResponse> latest_products = productService.getLatestProducts();
        List<ProductResponse> coming_products = productService.getComingProducts();
        List<ProductResponse> exclusive_products = productService.getExclusiveProducts();
        model.addAttribute("latest_products", latest_products);
        model.addAttribute("coming_products", coming_products);
        model.addAttribute("exclusive_products", exclusive_products);
        model.addAttribute("requestURI", uri);

        activityLogService.create(Activity.VIEW, null, null);
    }
}
