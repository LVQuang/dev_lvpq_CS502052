package dev.lvpq.CS502052.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.lvpq.CS502052.Service.BrandService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("admin/product/brand")
@Controller
public class BrandController {
    BrandService brandService;

    @GetMapping("")
    public String getBrands(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        var brands = brandService.getBrandListings();
        brands.forEach(brand -> {
            log.info("Brand name: {}", brand.getName());
        });
        model.addAttribute("brands", brands);
        return "/Admin/brand";
    }
}
