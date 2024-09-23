package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Response.UserDetailResponse;
import dev.lvpq.CS502052.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDetailResponse toDetailResponse(User user);
}
