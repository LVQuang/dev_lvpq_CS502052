package dev.lvpq.CS502052.Enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ProductStatus {
    Available("Available", "available"),
    OutOfStock("Out of Stock", "out-of-stock"),
    Discontinued("Discontinued", "discontinued"),
    PreOrder("Pre Order", "pre-order");

    String name;
    String meta;
}
