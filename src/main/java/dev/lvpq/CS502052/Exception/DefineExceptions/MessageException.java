package dev.lvpq.CS502052.Exception.DefineExceptions;

import dev.lvpq.CS502052.Exception.Error.ForgotPasswordExceptionCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.mail.MessagingException;
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MessageException extends MessagingException {
    ForgotPasswordExceptionCode code;
}
