package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Dto.Response.UserListResponse;
import dev.lvpq.CS502052.Entity.Role;
import dev.lvpq.CS502052.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(TransferRole(user.getRoles()))")
    UserDetailResponse toDetailResponse(User user);

    @Mapping(target = "roles", expression = "java(TransferRole(user.getRoles()))")
    UserListResponse toListResponse(User user);

    default Set<String> TransferRole(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
