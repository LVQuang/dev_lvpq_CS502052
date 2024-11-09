package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.ProductDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController()
public class UserAPI {
    UserService userService;

    @GetMapping("/get/{id}")
    public ApiResponse<UserDetailResponse> getById(@PathVariable String id) {
        return ApiResponse.<UserDetailResponse>builder()
                .code(200)
                .message("Find User Success")
                .result(userService.getById(id))
                .build();
    }
    @GetMapping()
    public  ApiResponse<ArrayList<UserListResponse>> getAll() {
        return ApiResponse.<ArrayList<UserListResponse>>builder()
                .code(200)
                .message("Find All Users Success")
                .result(userService.getAll())
                .build();
    }
    @GetMapping("/customer")
    public  ApiResponse<ArrayList<UserDetailResponse>> getAllCustomer() {
        return ApiResponse.<ArrayList<UserDetailResponse>>builder()
                .code(200)
                .message("Find All Users Success")
                .result(userService.getAllCustomer())
                .build();
    }
    @GetMapping("/currentUser")
    public  ApiResponse<UserDetailResponse> getCurrentUser() {
        return  ApiResponse.<UserDetailResponse>builder().code(200)
                .message("Find Current User Success")
                .result(userService.getCurrentInformation())
                .build();
    }
    @GetMapping({"/search","/search?query="})
    public ApiResponse<List<UserDetailResponse>> searchUsersByName(@RequestParam String query) {
        List<UserDetailResponse> users = userService.searchUsersByName(query);
        return ApiResponse.<List<UserDetailResponse>>builder()
                .code(200)
                .message("Search results for query: " + query)
                .result(users)
                .build();
    }
}
