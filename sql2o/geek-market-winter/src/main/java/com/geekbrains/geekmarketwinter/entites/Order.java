package com.geekbrains.geekmarketwinter.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "price")
    private Double price;

    @Column(name = "delivery_price")
    private Double deliveryPrice;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @CreationTimestamp
    private LocalDateTime updateAt;

    @JsonIgnore
    @Transient
    private boolean confirmed;

    public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("user_id", "user");
        COLUMN_MAPPINGS.put("order", "orderItems");
        COLUMN_MAPPINGS.put("status_id", "status");
        COLUMN_MAPPINGS.put("price", "price");
        COLUMN_MAPPINGS.put("delivery_price", "delivery_price");
        COLUMN_MAPPINGS.put("delivery_address_id", "deliveryAddress");
        COLUMN_MAPPINGS.put("phone_number", "phoneNumber");
        COLUMN_MAPPINGS.put("delivery_date", "deliveryDate");
        COLUMN_MAPPINGS.put("create_at", "createAt");
        COLUMN_MAPPINGS.put("update_at", "updateAt");
    }
}