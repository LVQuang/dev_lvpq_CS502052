package dev.lvpq.CS502052.Controller;

import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("")
@Controller
public class AdminController {

    @Autowired
    private final UserService userService;

    @GetMapping({"/admin"})
    public String admin(Model model, HttpServletRequest request){
        model.addAttribute("request", request);
        return "/admin_layout/index";
    }

    @GetMapping({"product.html", "product"})
    public String manage_product(Model model, HttpServletRequest request){
        model.addAttribute("request", request);
        return "/admin_layout/product";
    }

    @GetMapping({"account.html", "account"})
    public String getUsers(Model model, HttpServletRequest request) {
        // Lấy danh sách người dùng từ dịch vụ
        List<UserDetailResponse> users = userService.getAllCustomer(); // Sử dụng kiểu dữ liệu UserDetailResponse
        model.addAttribute("users", users); //
        model.addAttribute("request", request);
        return "/admin_layout/account";
    }

}
