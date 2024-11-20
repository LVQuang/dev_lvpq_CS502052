package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.QueryUser;
import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Exception.DefineExceptions.AuthException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Mapper.UserMapper;
import dev.lvpq.CS502052.Repository.UserRepository;
import dev.lvpq.CS502052.Specification.UserSpec;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new AuthException(AuthExceptionCode.USER_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('Manager')")
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

        Specification<User> spec = Specification.where(null);
        spec.and(UserSpec.hasEmail(email, true));
        var user = userRepository.findOne(spec).orElseThrow(() ->
                new AuthException(AuthExceptionCode.USER_NOT_EXISTED));
        return userMapper.toDetailResponse(user);
    }
    public User getCurrentUser() {
        var context = SecurityContextHolder.getContext();
        var email =context.getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(null);
    }
    public List<UserDetailResponse> findUsersByName(String query) {

        if (query == null || query.trim().isEmpty()){
            return getAllCustomer();
        }
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(query.toLowerCase()))
                .map(userMapper::toDetailResponse)
                .collect(Collectors.toList());

    }

    public List<UserDetailResponse> queryUser(QueryUser query) {
        Specification<User> spec = UserSpec.searchByKeyword(query.getKeyword(), false);
        var page = PageRequest.of(query.getPage(), query.getSize());

        var users = userRepository.findAll(spec, page);
        return users.stream().map(userMapper::toDetailResponse).toList();
    }
}
