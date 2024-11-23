package dev.lvpq.CS502052.Utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Repository.InvalidatedTokenRepository;

import java.text.ParseException;
import java.util.Date;


public class TokenUtil {
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public TokenUtil(InvalidatedTokenRepository invalidatedTokenRepository) {
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {

        String signerKey = "3yrdhh+tGeku/H0Lscu6Dgfq4k+m6a5GEgP5c8SEZeq4GL7evfYjhraygtfxKHU+";
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!verified && expiryTime.after(new Date()))
            throw new AppException(AuthExceptionCode.TOKEN_TIME);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(AuthExceptionCode.TOKEN_CRASH);
        return signedJWT;
    }
}
