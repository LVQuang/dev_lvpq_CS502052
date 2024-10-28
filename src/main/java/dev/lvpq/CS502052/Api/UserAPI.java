package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
}
