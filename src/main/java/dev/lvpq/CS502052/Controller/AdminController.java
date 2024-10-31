package dev.lvpq.CS502052.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class AdminController {
    @GetMapping({"/admin"})
    public String admin(){
        return "/admin_layout/index";
    }
    @GetMapping({"components/avatars.html","avatars"})
    public String avatars(){
        return "/admin_layout/components/avatars";
    }
    @GetMapping({"components/buttons.html"})
    public String buttons(){
        return "/admin_layout/components/buttons";
    }

    @GetMapping({"widgets.html", "widgets"})
    public String widgets(){
        return "/admin_layout/widgets";
    }

    @GetMapping({"product.html", "product"})
    public String manage_product(){
        return "/admin_layout/product";
    }
}
