package dev.lvpq.CS502052.Enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ProductType {
    LATEST("Latest"),
    RELATED("Related"),
    COMING("Coming"),
    EXCLUSIVE("Exclusive");
    String name;
}
