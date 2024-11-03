package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Response.ApiResponse;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.ErrorCode;
import dev.lvpq.CS502052.Mapper.UserMapper;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public UserDetailResponse getById(String id) {
        var user = userRepository.findById(id);
        return userMapper.toDetailResponse(user
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
//    @PreAuthorize("hasRole('Manager')")
    public ArrayList<UserListResponse> getAll() {
        var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toListResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<UserDetailResponse> getAllCustomer() {
        var users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> "Customer".equals(role.getName()))
                )
                .map(userMapper::toDetailResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public UserDetailResponse getCurrentInformation() {
        var context = SecurityContextHolder.getContext();
        var email =context.getAuthentication().getName();
        var user = userRepository.findByEmail(email).orElseThrow(null);
        return userMapper.toDetailResponse(user);
    }
}
