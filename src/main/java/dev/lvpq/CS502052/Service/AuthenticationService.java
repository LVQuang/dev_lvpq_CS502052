package dev.lvpq.CS502052.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import dev.lvpq.CS502052.Dto.Request.IntrospectRequest;
import dev.lvpq.CS502052.Dto.Request.LoginRequest;
import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Response.IntrospectResponse;
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
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

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

    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
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
