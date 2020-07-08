package com.geekbrains.geekmarketwinter.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
//    @JsonBackReference
    private Order order;

    public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("quantity", "quantity");
        COLUMN_MAPPINGS.put("item_price", "itemPrice");
        COLUMN_MAPPINGS.put("total_price", "totalPrice");
        COLUMN_MAPPINGS.put("product_id", "product");
        COLUMN_MAPPINGS.put("order_id", "order");
    }
}
