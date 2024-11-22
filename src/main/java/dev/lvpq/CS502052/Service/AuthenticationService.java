package dev.lvpq.CS502052.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import dev.lvpq.CS502052.Dto.Request.IntrospectRequest;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.LogoutRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.IntrospectResponse;
import dev.lvpq.CS502052.Dto.Response.LoginResponse;
import dev.lvpq.CS502052.Dto.Response.RegisterResponse;
import dev.lvpq.CS502052.Entity.InvalidatedToken;
import dev.lvpq.CS502052.Entity.Role;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.Activity;
import dev.lvpq.CS502052.Enums.RoleFeature;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.DefineExceptions.AuthException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Mapper.AuthenticationMapper;
import dev.lvpq.CS502052.Repository.InvalidatedTokenRepository;
import dev.lvpq.CS502052.Repository.RoleRepository;
import dev.lvpq.CS502052.Repository.UserRepository;
import dev.lvpq.CS502052.Specification.UserSpec;
import dev.lvpq.CS502052.Utils.TokenUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.nimbusds.jose.*;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    UserService userService;
    ActivityLogService activityLogService;
    UserRepository userRepository;
    RoleRepository roleRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    AuthenticationMapper authenticationMapper;
    PasswordEncoder passwordEncoder;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public RegisterResponse register(RegisterRequest request) {
        Specification<User> spec = Specification.where(null);
        spec = spec.and(UserSpec.hasEmail(request.getEmail(),true));

        var users = userRepository.findAll(spec);

        if (!users.isEmpty())
            throw new AuthException(AuthExceptionCode.USER_EXISTED);
        var user = authenticationMapper.converRegistertUser(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var role = roleRepository.findByName(RoleFeature.CUSTOMER.getName()).orElse(null);
        user.addRole(role);
        var currentUser = userService.getCurrentUser();
        activityLogService.create(Activity.REGISTER, null, currentUser);
        var userResponse = userRepository.save(user);
        return authenticationMapper.convertRegisterResponse(userResponse);
    }

    public LoginResponse login(LoginRequest request) {
        var user = softAuthenticate(request);
        activityLogService.create(Activity.LOGIN, null, user);
        if (user == null) return null;
        var roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        roles.forEach(log::info);
        var token = genToken(user);
        return LoginResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .roles(roles)
                    .build();
    }

    public boolean logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = new TokenUtil(invalidatedTokenRepository).verifyToken(request.getToken());

        if (signToken == null) return false;

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        var invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        var currentUser = userService.getCurrentUser();
        activityLogService.create(Activity.LOGOUT, null, currentUser);
        invalidatedTokenRepository.save(invalidatedToken);
        return true;
    }

    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            new TokenUtil(invalidatedTokenRepository).verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    User softAuthenticate(LoginRequest request) {
        log.debug("test 0");
        Specification<User> spec = Specification.where(UserSpec.hasEmail(request.getEmail(), true));

        var users = userRepository.findAll(spec);
        if (users.isEmpty())
            throw new AuthException(AuthExceptionCode.USER_NOT_EXISTED);
        var passwordEncoder = new BCryptPasswordEncoder(10);
        users.forEach(user -> log.debug("User id: {}", user.getId()));
        var user = users.get(0);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AuthException(AuthExceptionCode.PASSWORD_NOT_MATCHES);
        return user;
    }

    String genToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("CS502052.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
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

    private String buildScope(User user) {
        StringJoiner result = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> result.add(role.getName()));
        return result.toString();
    }
}
