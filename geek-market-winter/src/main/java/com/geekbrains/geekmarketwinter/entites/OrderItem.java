package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Data
@Entity
@Table(name = "orders_Item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "item_price")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    private double itemPrice;

    @Column(name = "total_price")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    private double totalPrice;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
