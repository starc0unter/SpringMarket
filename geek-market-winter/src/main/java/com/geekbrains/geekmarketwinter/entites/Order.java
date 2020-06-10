package com.geekbrains.geekmarketwinter.entites;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

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
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "price")
    @DecimalMin(value = "0.00", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    private double price;

    @Column(name = "delivery_price")
    @DecimalMin(value = "0.00", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    private double deliveryPrice;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @Column(name = "phone_number")
    @Pattern(regexp = "([0-9]+)", message = "недопустимый символ")
    private String phoneNumber;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", status=" + status +
                ", price=" + price +
                ", deliveryPrice=" + deliveryPrice +
                ", deliveryAddress=" + deliveryAddress +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
