package dev.lvpq.CS502052.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductView {
    private String name;
    private String price;
    private String originalPrice;
    private String imageUrl;

    // Constructor, getters, and setters
    public ProductView(String name, String price, String originalPrice, String imageUrl) {
        this.name = name;
        this.price = price;
        this.originalPrice = originalPrice;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
}
