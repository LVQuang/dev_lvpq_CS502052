package dev.lvpq.CS502052.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.LoginResponse;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.ErrorCode;
import dev.lvpq.CS502052.Mapper.AuthenticationMapper;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.nimbusds.jose.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    UserRepository userRepository;
    AuthenticationMapper authenticationMapper;
    static String SIGNER_KEY = "3yrdhh+tGeku/H0Lscu6Dgfq4k+m6a5GEgP5c8SEZeq4GL7evfYjhraygtfxKHU+";

    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        var user = authenticationMapper.converRegistertUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var userResponse = userRepository.save(user);
        return authenticationMapper.convertRegisterResponse(userResponse);
    }

    public LoginResponse login(LoginRequest request) {
        if (!softAuthenticate(request)) return null;
        var token = genToken(request.getEmail());
        return LoginResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .build();
    }

    // Defined Function
    boolean softAuthenticate(LoginRequest request) {
        if(!userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);

        var user = userRepository.findByEmail(request.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCHES);
        return true;
    }

    String genToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("CS502052.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "Custom")
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject object = new JWSObject(header, payload);

        try {
            object.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return object.serialize();
        } catch (JOSEException e) {
            log.error("Cannot generate Token");
            throw new RuntimeException(e);
        }
    }
}
