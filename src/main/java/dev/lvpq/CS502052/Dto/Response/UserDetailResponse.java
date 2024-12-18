package dev.lvpq.CS502052.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDetailResponse {
    String id;
    String username;
    String email;
    String phone;
    String address;
    LocalDate dateOfBirth;
    Set<String> roles;
}
