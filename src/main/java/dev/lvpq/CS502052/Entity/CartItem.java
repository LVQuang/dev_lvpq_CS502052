package dev.lvpq.CS502052.Entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {
    private String imageUrl;
    private String name;
    private String description;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public CartItem(String name, String imageUrl, String description, double unitPrice, int quantity) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        // Tính toán giá tổng ban đầu
        this.totalPrice = calculateTotalPrice();
    }

    // Tính toán tổng giá
    public double calculateTotalPrice() {
        return unitPrice*(quantity);
    }

    // Nếu số lượng thay đổi, cập nhật lại totalPrice
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
    }
    public void increaseQty(){
        quantity++;
    }public void decreaseQty(){
        if(quantity >0){
            quantity--;
        }
    }
}
