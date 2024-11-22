package dev.lvpq.CS502052.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SimpleMailRequest {
    String receiver;
    String subject;
    String text;
    String sender;
    @Builder.Default
    boolean html = false;
}
