package dev.lvpq.CS502052.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisterResponse {
    String name;
    String password;
    String email;
    String phone;
}
