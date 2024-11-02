package dev.lvpq.CS502052.Enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum RoleFeature {
    MANAGER("Manager", "The most privilege in administrator account", "Manager"),
    WAREHOUSE_MANAGER("WarehouseManager", "Responsible for warehouse service: import and export production", "Warehouse Manager"),
    ACCOUNT_MANAGER("AccountManager", "Responsible for account service: solving problems involve with customer", "Account Manager"),
    SALE_MANAGER("SaleManager", "Responsible for sale service: create voucher to attract customer", "Sale Manager"),
    CUSTOMER("Customer", "Ordinary account, who will consume service in website", "Customer");

    String name;
    String description;
    String meta;
}
