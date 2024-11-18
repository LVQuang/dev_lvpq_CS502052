package dev.lvpq.CS502052.Enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Activity {
    LOGIN("Login", "user login system"),
    LOGOUT("Logout", "user logout system"),
    REGISTER("Register", "user create a new account"),
    RESET_PASSWORD("Reset Password", "user change password");

    String name;
    String meta;
}
