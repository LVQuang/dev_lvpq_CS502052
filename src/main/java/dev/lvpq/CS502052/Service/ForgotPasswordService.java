package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.ResetPassword;
import dev.lvpq.CS502052.Dto.Request.SimpleMailRequest;
import dev.lvpq.CS502052.Entity.OTP;
import dev.lvpq.CS502052.Exception.DefineExceptions.ForgotPasswordException;
import dev.lvpq.CS502052.Exception.DefineExceptions.OTPException;
import dev.lvpq.CS502052.Exception.DefineExceptions.ResetPasswordException;
import dev.lvpq.CS502052.Exception.Error.ForgotPasswordExceptionCode;
import dev.lvpq.CS502052.Exception.Error.OTPExceptionCode;
import dev.lvpq.CS502052.Exception.Error.ResetPasswordCode;
import dev.lvpq.CS502052.Repository.OTPRepository;
import dev.lvpq.CS502052.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ForgotPasswordService {
    UserRepository userRepository;
    OTPRepository otpRepository;
    MailService mailService;
    PasswordEncoder passwordEncoder;

    public void sendOTP(String email) throws MessagingException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ForgotPasswordException(ForgotPasswordExceptionCode.EMAIL_NOT_EXISTED));

        var otp = OTP.builder()
                .code(String.format("%06d", new Random().nextInt(1_000_000)))
                .user(user)
                .build();

        otpRepository.save(otp);
        var request = SimpleMailRequest.builder()
                .receiver(email)
                .subject("Karma OTP Reset Password")
                .text("Your OTP: " + otp.getCode())
                .sender("phantipo2331@gmail.com")
                .build();
        mailService.sendSimpleMail(request);
    }

    public void validateOTP(String code) {
        var otp = otpRepository.findByCodeAndValid(code, true)
                .orElseThrow(() ->
                        new OTPException(OTPExceptionCode.OTP_NOT_CORRECT));
        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            otp.setValid(false);
            otpRepository.save(otp);
            throw new OTPException(OTPExceptionCode.OTP_EXPIRED);
        }
        otp.setValid(false);
        otpRepository.save(otp);
    }

    public void ResetPassword(ResetPassword resetPassword) {
        var otp = otpRepository.findByCode(resetPassword.getOtp())
                .orElseThrow(() -> ResetPasswordException.builder()
                        .code(ResetPasswordCode.OTP_INVALID)
                        .otp(resetPassword.getOtp())
                        .build());
        var user = otp.getUser();
        user.setPassword(passwordEncoder
                .encode(resetPassword.getPassword()));
        userRepository.save(user);
    }
}
