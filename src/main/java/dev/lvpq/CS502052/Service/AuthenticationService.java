package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.ErrorCode;
import dev.lvpq.CS502052.Mapper.AuthenticationMapper;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    UserRepository userRepository;
    AuthenticationMapper authenticationMapper;

    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        var user = authenticationMapper.converRegistertUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var userResponse = userRepository.save(user);
        return authenticationMapper.convertRegisterResponse(userResponse);
    }

    public boolean login(LoginRequest request) {
        if(!userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);

        var user = userRepository.findByEmail(request.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCHES);
        return true;
    }
}
