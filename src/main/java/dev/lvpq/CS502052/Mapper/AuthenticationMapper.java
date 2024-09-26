package dev.lvpq.CS502052.Mapper;

import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    User converRegistertUser(RegisterRequest request);
    RegisterResponse convertRegisterResponse(User user);
}
