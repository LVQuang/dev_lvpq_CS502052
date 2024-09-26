package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    User convertUser(RegisterRequest request);
    RegisterResponse convertRegisterResponse(User user);
}
