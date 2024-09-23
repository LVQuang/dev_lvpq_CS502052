package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Mapper.AuthenticationMapper;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    UserRepository userRepository;
    AuthenticationMapper authenticationMapper;

    public RegisterResponse register(RegisterRequest request) {
        var user = authenticationMapper.convertUser(request);
        var userResponse = userRepository.save(user);
        return authenticationMapper.convertRegisterResponse(userResponse);
    }
}
