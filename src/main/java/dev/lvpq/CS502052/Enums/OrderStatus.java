package dev.lvpq.CS502052.Enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum OrderStatus {
    PENDING("Pending", "pending"),
    PROCESSING("Processing", "processing"),
    SHIPPED("Shipped", "shipped"),
    DELIVERED("Delivered", "delivered"),
    CANCELLED("Cancelled", "cancelled"),
    RETURNED("Returned", "returned"),
    REFUNDED("Refunded", "refunded");

    String name;
    String meta;
}
